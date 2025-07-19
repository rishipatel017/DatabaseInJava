package in.ac.adit.banksystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ac.adit.banksystem.entity.Account;
import in.ac.adit.banksystem.exception.AccountNotFoundException;
import in.ac.adit.banksystem.exception.InsufficientBalanceException;


public class AccountDAOImpl implements AccountDAO{	
	private final String DRIVER="com.mysql.cj.jdbc.Driver";
	private final String URL="jdbc:mysql://localhost:3306/demo_db";
	private final String USER="root";
	private final String PASSWORD="";
	Connection connection=null;
	public AccountDAOImpl(){
		try {
			Class.forName(DRIVER);
			connection=DriverManager.getConnection(URL,USER,PASSWORD);
			connection.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public double getAmountById(long id) throws SQLException {
		String getbalance="select balance from account_tbl where acc_id=?";
		PreparedStatement pstm=connection.prepareStatement(getbalance);
		pstm.setLong(1, id);
		ResultSet rs=pstm.executeQuery();
		if (rs.next()) {
	        return rs.getDouble("balance");
	    } else {
	        throw new SQLException("Account ID not found: " + id);
	    }
	}
	
	@Override
	//get account by account id 
	public Account getAccountById(long id) throws Exception {
		String sql="select * from account_tbl where acc_id=?";
		PreparedStatement pstm=connection.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs=pstm.executeQuery();
		if(rs.next()) {
			return new Account(rs.getInt(1),rs.getString(2),rs.getDouble(3));
		}
		else {
			throw new AccountNotFoundException("Given Accout id "+id+"is not available please check Account Number");
		}
	}

	@Override
	public void deposit(long id, double amount){
		try {
			if(amount>0) {
				String sql="update account_tbl set balance=balance+? where account_tbl.acc_id=?";
				PreparedStatement pstm=connection.prepareStatement(sql);
				pstm.setDouble(1,amount);
				pstm.setLong(2, id);
				pstm.execute();
				System.out.println(amount+"/- Rs Deposited successfully to account"+id);
				connection.commit();
			}
			else {
				System.out.println("Enter Valid Amount.Amount must in positive");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public void debit(long id, double amount){
		// TODO Auto-generated method stub
		double oldamount;
		try {
			oldamount = getAmountById(id);
			double newamount=oldamount-amount;
			if(newamount>0) {
				String sql="update account_tbl set balance=? where acc_id=?";
				PreparedStatement pstm=connection.prepareStatement(sql);
				pstm.setDouble(1,newamount);
				pstm.setLong(2, id);
				pstm.execute();
				System.out.println(amount+"/- Rs Debited successfully from account"+id);
				connection.commit();
			}
			else {
				throw new InsufficientBalanceException("You have not sufficient balance!!! please deposit money....");
			}	
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		catch(Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void transferFund(long fromid, long toid, double amount) {
		// TODO Auto-generated method stub
		
		try {
			if(amount<=0) {
				throw new InsufficientBalanceException("Amount must be positive.");
			}
			Account sender=getAccountById(fromid);
			if(sender.getBalance()< amount) {
				throw new InsufficientBalanceException("sender has insufficient balance");
			}
			//Debit from sender
			String debitsql="update account_tbl SET balance=balance -? where acc_id=? ";
			PreparedStatement debitstmt=connection.prepareStatement(debitsql);
			debitstmt.setDouble(1, amount);
			debitstmt.setLong(2, fromid);
			debitstmt.execute();
			
			//Credit to receiver
			String creditsql="update account_tbl SET balance=balance+? where acc_id=? ";
			PreparedStatement creditstmt=connection.prepareStatement(creditsql);
			creditstmt.setDouble(1, amount);
			creditstmt.setLong(2, toid);
			creditstmt.execute();
			
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

}

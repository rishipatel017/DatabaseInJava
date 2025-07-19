package in.ac.adit.banksystem.dao;

import java.sql.SQLException;

import in.ac.adit.banksystem.entity.Account;

public interface AccountDAO {
	Account getAccountById(long id) throws Exception;
	void deposit(long id,double amount) throws Exception;
	void debit(long id,double amount) throws Exception;
	void transferFund(long fromid,long toid,double amount) throws Exception;
	double getAmountById(long id) throws SQLException;
	
}

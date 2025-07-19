package in.ac.adit.banksystem.entity;

public class Account {
	private long accountno;
	private String name;
	private double balance;
	
	//Constructor for variable initializing
	public Account(long accountno, String name, double balance) {
		super();
		this.accountno = accountno;
		this.name = name;
		this.balance = balance;
	}
	
	// Getter and Setter Methods for each variable 
	public long getAccountno() {
		return accountno;
	}
	public String getName() {
		return name;
	}
	public double getBalance() {
		return balance;
	}
	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//toString method for data Printing 
	@Override
	public String toString() {
		return "Account [accountno=" + accountno + ", name=" + name + ", balance=" + balance + "]";
	}
}
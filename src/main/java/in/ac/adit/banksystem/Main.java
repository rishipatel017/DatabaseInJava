package in.ac.adit.banksystem;
import java.util.Scanner;

import in.ac.adit.banksystem.dao.*;
import in.ac.adit.banksystem.entity.Account;

public class Main{

	public static void main(String[] args) {
		AccountDAOImpl dao=new AccountDAOImpl();
		Scanner sc=new Scanner(System.in);
		long id=0l;
		double amount=0.0;		
		while(true) {
			System.out.println("\n-------------- Bank Menu (Transaction Demo) -------------");
			System.out.println("1. View Account.");
			System.out.println("2. Deposit Money.");
			System.out.println("3. Withdraw Money.");
			System.out.println("4. Transfer Money to other account.");
			System.out.println("5. Exit.");
			System.out.println();
			
			//reading choice from user
			System.out.println("Choose an option:");
			int choice=sc.nextInt();
			
			try {
				switch(choice) {
					case 1:
						System.out.println("Enter The Account ID:");
						id=sc.nextLong();
						Account acc=dao.getAccountById(id);
						System.out.println(acc);
						break;
					case 2:
						System.out.println("Enter The Account ID:");
						id=sc.nextLong();
						System.out.println("Enter amount to deposit:");
						amount=sc.nextDouble();
						dao.deposit(id, amount);
						break;
					case 3:
						System.out.println("Enter The Account ID:");
						id=sc.nextLong();
						System.out.println("Enter amount to deposit:");
						amount=sc.nextDouble();
						dao.debit(id, amount);
						break;
					case 4:
						System.out.println("Enter The Sender ID:");
						long fromid=sc.nextLong();
						System.out.println("Enter The Receiver ID:");
						long toid=sc.nextLong();
						System.out.println("Enter amount to deposit:");
						amount=sc.nextDouble();
						dao.transferFund(fromid,toid,amount);
						break;
					case 5:
						System.out.println("Program terminated successfully...........");
						return;
					default:
						System.out.println("No valid option!!! Please try Again");
						
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

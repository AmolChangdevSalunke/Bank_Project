package com.bank.Operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bank.model.Account_Transaction;
import com.bank.model.Customer;
import com.bank.model.Customer_Information;
import com.tech.util.HibernateUtil.HibernateUtil;

public class Operation {
	Scanner sc = new Scanner(System.in);
	Customer c = new Customer();
	Customer_Information ci = new Customer_Information();
	Account_Transaction a = new Account_Transaction();
	SessionFactory sf = HibernateUtil.getSessionFactory();
	List<Account_Transaction> l = new ArrayList<>();

	// Method used for account creation
	public void createAccount() {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		System.out.println("Enter account number:");
		int accNo = sc.nextInt();
		while (true) {
			if (accNo >= 1000 && accNo <= 9999) {
				c.setAccountNumber(accNo);
				ci.setAccountNumber(accNo);
				break;
			} else {
				System.out.println("Enter Number Must Be Four Digit");
			}
		}
		System.out.println("Account Holder Name:");
		System.out.println("First Name:");
		String firstName = sc.next();
		System.out.println("Last Name:");
		String lastName = sc.next();
		c.setCustomerName(firstName + " " + lastName);
		ci.setCustomerName(firstName + " " + lastName);
		try {
			System.out.println("Please select account type:\n1.Saving Account \n2.Current Account \n3.Salary Account");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				c.setAccountType("Saving Account");
				ci.setAccountType("Saving Account");
				break;
			case 2:
				c.setAccountType("Current Account");
				ci.setAccountType("Current Account");
				break;
			case 3:
				c.setAccountType("Salary Account");
				ci.setAccountType("Salary Account");
				break;
			default:
				System.out.println("Please select correct choice");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Enter choice should be in Integer");
		}

		while (true) {
			System.out.println("Account Holder Aadhar Number:");
			int aadhar = sc.nextInt();
			if (aadhar >= 100000 && aadhar <= 999999) {
				c.setCustomerAadharNo(aadhar);
				ci.setCustomerAadharNo(aadhar);
				break;
			} else {
				System.out.println("Adhar Number must be 6 digit number ");
			}
		}

		System.out.println("Account Holder Mobile Number:");
		int Mob = sc.nextInt();
		c.setCustomerMoNumber(Mob);
		ci.setCustomerMoNumber(Mob);
		System.out.println("Account Holder Adress:");
		String adrs = sc.next();
		c.setCustomerAddress(adrs);
		ci.setCustomerAddress(adrs);

		while (true) {
			try {
				System.out.println("Deposit Amount:");
				double amt = sc.nextDouble();
				if (amt >= 500) {
					c.setDepositAmount(amt);
					c.setBalance(amt);
					ci.setBalance(amt);
					a.setBalance(amt);
					a.setTransactionType("Deposited");
					a.setTransactionDetail("Amount Deposited At the Time Of Account Creation");
					a.setTransfered_amount(amt);
					break;

				} else {
					System.out.println("Deposit  amount must greter than or equal to 500");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter amount in Integer value");
			}
		}

		// Account creation date and time
		c.setDate(new Date());

		System.out.println("Set Username:");
		String id = sc.next();
		c.setUserId(id);
		ci.setUserId(id);

		System.out.println("Set Password:");
		String pass = sc.next();
		c.setPassword(pass);
		ci.setPassword(pass);

		a.setDate(new Date());
		a.setTransactionType("Credit");
		a.setTransactionDetail("Amount Deposited At the Time Of Account Creation");
		a.setBalance(c.getDepositAmount());
		a.setBalance(c.getBalance());
		a.setCustomer(c);
		// a.setTransfered_amount(ci.get);

		l.add(a);
		c.setList(l);

		session.save(a);
		session.save(c);
		session.save(ci);
		tr.commit();
		System.out.println("Account created successfuly");
		System.out.println("Please verify your account for account activition");

		session.close();
	}

	// Method used to Show Account Detail
	public void showAccount() {

		Session session = sf.openSession();
		while (true) {
			System.out.println("Enter Your Account Number:");
			int acc = sc.nextInt();
			try {
				Customer c1 = session.get(Customer.class, acc);
				if (c1 != null && c.getVerify(true)) {
					System.out.println("Account Number: " + c1.getAccountNumber());
					System.out.println("Account type: " + c1.getAccountType());
					System.out.println("Account Holder name: " + c1.getCustomerName());
					System.out.println("Account Holder Aadhar Number: " + c1.getCustomerAadharNo());
					System.out.println("Account Holder Mobile number: " + c1.getCustomerMoNumber());
					System.out.println("Total Balance: " + c1.getBalance());
					System.out.println("Account userName: " + c1.getUserId());
					System.out.println("Account password: " + c1.getPassword());

					session.close();
					break;

				} else {
					System.out.println("No such account found,please create account first");
				}
			} catch (NullPointerException e) {
				System.out.println("Please verify your Account");
			}
		}
	}

	// Method used to check balance
	public void balanceCheck() {
		Session sn = sf.openSession();
		System.out.println("Enter your Account number:");
		int acc = sc.nextInt();
	try {
			Customer ac1 = sn.get(Customer.class, acc);
			
			if (ac1 != null&& c.getVerify(true) ) {
				System.out.println("Total Balance Amount:");
				System.out.println(ac1.getBalance());
				sn.close();
			} else {
				System.out.println("Yon have not create account");
			}}catch (NullPointerException e) {
				System.out.println("Please verify your account");
			}
		} 	
	

	// Method used to Deposit Money
	public void depositMoney() {
		Session sn = sf.openSession();
		Transaction t = sn.beginTransaction();
		System.out.println("Enter your Account number:");
		int acc = sc.nextInt();
		try {
			Customer ac1 = sn.get(Customer.class, acc);

			if (ac1 != null && c.getVerify(true)) {
				System.out.println("Enter your Deposite Amount:");
				double amount = sc.nextDouble();
				if (amount > 0) {
					double total = ac1.getBalance() + amount;
					Query<Customer> hql = sn
							.createQuery("update Customer set balance=:amount where AccountNumber=:accno");
					hql.setParameter("amount", total);
					hql.setParameter("accno", acc);
					int h = hql.executeUpdate();
					if (h != 0) {
						// transaction saved
						a.setDate(new Date());
						a.setTransfered_amount(amount);
						a.setBalance(total);
						a.setTransactionType("Deposite");
						a.setTransactionDetail("Amount credited in your Account");
						a.setCustomer(ac1);

						sn.save(a);

						t.commit();
						System.out.println(amount + " Amount Deposited successfully");
						sn.close();
					} else {
						System.out.println("Record not found");
					}
				} else {
					System.out.println("please enter valid amount");
				}
			} else {
				System.out.println("Please create account ");
			}
		} catch (NullPointerException e) {
			System.out.println("Please verify your Account");
		}
	}

	// Method used t withdraw money
	public void withdrawMoney() {
		Session sn = sf.openSession();
		Transaction t = sn.beginTransaction();
		System.out.println("Enter your Account number:");
		int acc = sc.nextInt();
		Customer ac1 = sn.get(Customer.class, acc);
		try {
			if (ac1 != null && c.getVerify(true)) {
				System.out.println("Total Balance in your Account: " + ac1.getBalance());
				System.out.println("Enter your withdraw Amount:");
				double amount = sc.nextDouble();
				if (amount > 0) {
					double total = ac1.getBalance() - amount;
					if (total >= 500) {
						Query<Customer> hql = sn
								.createQuery("update Customer set balance=:amount where AccountNumber=:accno");
						hql.setParameter("amount", total);
						hql.setParameter("accno", acc);
						int h = hql.executeUpdate();
						if (h != 0) {
							// transaction saved
							a.setDate(new Date());
							a.setTransfered_amount(amount);
							a.setBalance(total);
							a.setTransactionType("Withdraw");
							a.setTransactionDetail("Amount Debited from Account");
							a.setCustomer(ac1);

							sn.save(a);

							t.commit();
							System.out.println(amount + " Amount withdraw successfully");
							sn.close();
						} else {
							System.out.println("Record not found");
						}

					} else {
						System.out.println("You can't withdraw because your account balance is less than 500");
					}
				} else {
					System.out.println("please enter valid amount");
				}
			} else {
				System.out.println("Please create account ");
			}
		} catch (NullPointerException e) {
			System.out.println("Please verify your Account");
		}

	}

	// Method To Transfer Money
	public void moneyTransfer() {
		Session sn = sf.openSession();
		Transaction t = sn.beginTransaction();
		Account_Transaction tx1 = new Account_Transaction();
		System.out.println("Enter Receivers Account Number:");
		int acc1 = sc.nextInt();
		try {
			Customer ac1 = sn.get(Customer.class, acc1);
			System.out.println("Receivers Name: "+ac1.getCustomerName());
			System.out.println("Enter 1 To Confirm");
			int ch=sc.nextInt();
			switch(ch) {
			case 1: 
			
			if (ac1 != null && c.getVerify(true)) {
				System.out.println("Enter Senders Account number :");
				int acc2 = sc.nextInt();
				Customer ac2 = sn.get(Customer.class, acc2);
				try {
				if (ac2 != null&& c.getVerify(true)) {
					System.out.println("Total Balance in your Account: " + ac2.getBalance());
					System.out.println("Enter Amount for Transfer:");
					double sendMoney = sc.nextDouble();
					if (sendMoney > 0) {
						// for senders Account
						double total = ac2.getBalance() - sendMoney;
						if (total >= 500) {
							Query<Customer> hql1 = sn
									.createQuery("update Customer set balance=:amount where AccountNumber=:accno");
							hql1.setParameter("amount", total);
							hql1.setParameter("accno", acc2);
							hql1.executeUpdate();

							tx1.setDate(new Date());
							tx1.setTransactionDetail("Amount Transfered to " + ac2.getCustomerName());
							tx1.setTransactionType("Debit");
							tx1.setTransfered_amount(sendMoney);
							tx1.setBalance(total);
							tx1.setCustomer(ac2);

							// for receivers account
							double balance = ac1.getBalance() + sendMoney;
							org.hibernate.query.Query<Customer> hql2 = sn
									.createQuery("update Customer set Balance=:amount where AccountNumber=:accno");
							hql2.setParameter("amount", balance);
							hql2.setParameter("accno", acc1);
							hql2.executeUpdate();

							a.setDate(new Date());
							a.setTransactionDetail("Amount Recived From-" + ac1.getCustomerName());
							a.setTransactionType("Credit");
							a.setTransfered_amount(sendMoney);
							a.setBalance(balance);
							a.setCustomer(ac1);
							sn.save(tx1);
							sn.save(a);

							t.commit();
							sn.close();

							System.out.println(sendMoney + " Amount transfer Successful");

						} else {
							System.out
									.println("You can't transfer money because your account balance is less than 500");
						}

					} else {
						System.out.println("Amount should be greater than zero");
					}
				} else {
					System.out.println("You have not created account");
				}

			} catch (NullPointerException e) {
				System.out.println("Please verify your Account");
			}}else {
				System.out.println("You have not created account.");
				}
			break;
		}}
		
		catch (NullPointerException e) {
			System.out.println("Reciverse Account is not verified");
		}
	}

	// for Account Statement
	public void AccountStatement() {

		Session sn = sf.openSession();

		System.out.println("Enter your Account number:");
		int aadhar = sc.nextInt();
		Customer ac1 = sn.get(Customer.class, aadhar);
		try {
			if (ac1 != null && c.getVerify(true)) {
				System.out.println("Aadhar Number: " + ac1.getCustomerAadharNo());
				System.out.println("Transaction Details:");
				System.out.println();
				for (Account_Transaction t : ac1.getList()) {
					System.out.println("Transaction Date:-   " + (t.getDate()));
					System.out.println("Transaction ID:-     " + (t.getTransactionId()));
					System.out.println("Transaction Type :-  " + (t.getTransactionType()));
					System.out.println("Transaction Detail:- " + (t.getTransactionDetail()));
					System.out.println("Transaction Amount:- " + (t.getTransfered_amount()));
					System.out.println("Your Total Balance:- " + (t.getBalance()));
					System.out.println("*****************************************");

				}
				sn.close();
				// sf.close();
			} else {
				System.out.println("You don't have any transaction");
			}
		} catch (NullPointerException e) {
			System.out.println("Please verify your Account");
		}
	}

	// for account verification
	public Boolean verifyAccount() {
		Session sn1 = sf.openSession();

		System.out.println("Enter Aadhar Number:");
		int aadharNo = sc.nextInt();
		Customer_Information ci = sn1.get(Customer_Information.class, aadharNo);
		if (ci != null) {

			System.out.println(ci.getAccountNumber());
			System.out.println(ci.getCustomerAadharNo());
			System.out.println(ci.getCustomerName());
			System.out.println(ci.getCustomerMoNumber());
			System.out.println(ci.getCustomerAddress());
			c.setVerify(true);
			sn1.close();

		} else {
			System.out.println("You not create account, please create account");
		}
		return true;

	}

	// To retrieve Password and UserID
	public void forgetUsernamePasswaord() {
		Session sn1 = sf.openSession();
		System.out.println("Enter Aadhar Number:");
		int aadharNo = sc.nextInt();
		Customer_Information ci = sn1.get(Customer_Information.class, aadharNo);
		if (ci != null) {
			System.out.println("Select Operation:\n1.Forget Username\n2.Forget Password");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:

				System.out.println(ci.getUserId());
				break;

			case 2:
				System.out.println(ci.getPassword());
				break;

			}
			sn1.close();

		} else {
			System.out.println("No Such Account Found");
		}
		
	}

	// to exit
	public void exit() {
		System.out.println("Thanks For Visiting");
		sf.close();
	}

}

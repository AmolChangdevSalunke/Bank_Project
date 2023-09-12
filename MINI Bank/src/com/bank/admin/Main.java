package com.bank.admin;

import java.util.Scanner;

import com.bank.Operation.Operation;
import com.bank.model.Account_Transaction;
import com.bank.model.Customer;

public class Main {
	public static void main(String[] args) {

		Scanner sc1 = new Scanner(System.in);
		Operation op = new Operation();
		Customer c = new Customer();
		Account_Transaction t = new Account_Transaction();

		while (true) {
			System.out.println("Choose option from below");
			System.out.println("Select Operation:\n1.Create Account\n2.Show Account Detail"
					+ "\n3.Balance Check\n4.Deposit Money \n5.Withdraw Money\n6.Tranfer Money\n7.Account Statement\n8.accountVerification\n9.Forget Username_or_Password \n10.Exit");
			int a = sc1.nextInt();
			switch (a) {
			case 1:
				op.createAccount();
				break;

			case 2:
				op.showAccount();
				break;

			case 3:
				op.balanceCheck();
				break;

			case 4:
				op.depositMoney();
				break;

			case 5:
				op.withdrawMoney();
				break;

			case 6:
				op.moneyTransfer();
				break;

			case 7:
				op.AccountStatement();
				break;
			case 8:
				op.verifyAccount();
				break;
			case 9:
				op.forgetUsernamePasswaord();
				break;
			case 10:
				op.exit();
				break;
			default:
				System.out.println("Enter Valid Choice");

			}

		}

	}

}

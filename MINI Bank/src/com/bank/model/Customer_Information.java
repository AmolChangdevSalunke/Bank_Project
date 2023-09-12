package com.bank.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer_Information {
	private String CustomerName;

	private int AccountNumber;
	private String AccountType;

	private int CustomerMoNumber;
	@Id
	private int CustomerAadharNo;
	private String CustomerAddress;
	private Double balance;
	//@Transient
	private String UserId;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	//@Transient
	private String Password;

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public int getCustomerMoNumber() {
		return CustomerMoNumber;
	}

	public void setCustomerMoNumber(int customerMoNumber) {
		CustomerMoNumber = customerMoNumber;
	}

	public int getCustomerAadharNo() {
		return CustomerAadharNo;
	}

	public void setCustomerAadharNo(int customerAadharNo) {
		CustomerAadharNo = customerAadharNo;
	}

	public String getCustomerAddress() {
		return CustomerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		CustomerAddress = customerAddress;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}

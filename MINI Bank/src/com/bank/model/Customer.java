package com.bank.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Comparable<Customer> {

	private String CustomerName;
	@Id
	private int AccountNumber;
	private String AccountType;
	private int CustomerMoNumber;
	private int CustomerAadharNo;
	private String CustomerAddress;
	private Double DepositAmount;
	private Double WithdrawAmoun;
	private Double balance;
	private Date date;

	@OneToMany(mappedBy = "customer")
	private List<Account_Transaction> list;

	public List<Account_Transaction> getList() {
		return list;
	}

	public void setList(List<Account_Transaction> list) {
		this.list = list;
	}

	public Double getWithdrawAmoun() {
		return WithdrawAmoun;
	}

	public void setWithdrawAmoun(Double withdrawAmoun) {
		WithdrawAmoun = withdrawAmoun;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	private String UserId;
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

	public Double getDepositAmount() {
		return DepositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		DepositAmount = depositAmount;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int compareTo(Customer o) {
		// TODO Auto-generated method stub
		return 1;
	}

}

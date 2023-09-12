package com.bank.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account_Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	private String TransactionDetail;
	private String TransactionType;
	private Date date;
	private Double Transfered_amount;

	private Double balance;
	@ManyToOne
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionDetail() {
		return TransactionDetail;
	}

	public void setTransactionDetail(String transactionDetail) {
		TransactionDetail = transactionDetail;
	}

	public String getTransactionType() {
		return TransactionType;
	}

	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTransfered_amount() {
		return Transfered_amount;
	}

	public void setTransfered_amount(Double transfered_amount) {
		Transfered_amount = transfered_amount;
	}

}

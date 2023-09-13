package com.nissan.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankDetails {
	@Id
	private Integer accountNumber;
	private String custName;
	private String accountType;
	private double balance;
	private double minBalance;
	private long mobNum;
	private String email;
	private Integer atmPin;
	private boolean isActive=true;

	public BankDetails() {
		super();
		this.accountNumber = (int) (Math.random() * 900000000 + 100000000);
		this.atmPin = (int) (Math.random() * 9000 + 1000);
	
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	public long getMobNum() {
		return mobNum;
	}

	public void setMobNum(long mobNum) {
		this.mobNum = mobNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAtmPin() {
		return atmPin;
	}


	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}

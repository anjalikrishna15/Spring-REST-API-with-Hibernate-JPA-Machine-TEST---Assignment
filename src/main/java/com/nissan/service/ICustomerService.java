package com.nissan.service;

import com.nissan.common.APIResponse;

public interface ICustomerService {
	public APIResponse deposit(int accNum, int amount);

	public APIResponse withdraw(int accNum, int amount);

	public APIResponse showBalance(int accNum);

	public APIResponse transferMoney(int accNum1, int accNum2, int amount);
}

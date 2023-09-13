package com.nissan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.model.BankDetails;
import com.nissan.model.User;
import com.nissan.repository.ICustomerRepository;

import com.nissan.util.JWTUtil;

@Transactional
@Service
public class CustomerServiceImple implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	@Autowired
	private APIResponse apiResponse;
	@Autowired
	private JWTUtil jwtUtil;

	public APIResponse deposit(int accNum, int amount) {
		List<BankDetails> bank = (List<BankDetails>) customerRepository.findAll();
		BankDetails obj = null;
		for (BankDetails b : bank) {
			if (b.getAccountNumber() == accNum) {
				obj = b;
			}
		}
		if (amount > 50000) {
			apiResponse.setData("ENTER PAN CARD NUMBER");
			obj.setBalance(obj.getBalance() + amount);
			apiResponse.setData("Amount deposited successfully.Balance=" + obj.getBalance());
			apiResponse.setStatus(500);
		}

		else {
			obj.setBalance(obj.getBalance() + amount);
			apiResponse.setData("Amount deposited successfully.Balance=" + obj.getBalance());
			apiResponse.setStatus(500);
		}

		return apiResponse;

	}

	public APIResponse withdraw(int accNum, int amount) {
		List<BankDetails> bank = (List<BankDetails>) customerRepository.findAll();
		for (BankDetails b : bank) {
			if (b.getAccountNumber() == accNum) {
				double availableBalance = b.getBalance() - b.getMinBalance();
				if (amount > availableBalance) {
					apiResponse.setData("Insufficient funds..!!!");
					apiResponse.setStatus(500);
					apiResponse.setError("no sufficent balance ");
					return apiResponse;

				}
				b.setBalance(b.getBalance() - amount);

				apiResponse.setData("Amount withdrawn successfully.Balance=" + b.getBalance());
				apiResponse.setStatus(200);
				break;
			}
		}
		return apiResponse;
	}

	public APIResponse showBalance(int accNum) {
		List<BankDetails> bank = (List<BankDetails>) customerRepository.findAll();
		BankDetails obj = null;
		for (BankDetails b : bank) {
			if (b.getAccountNumber() == accNum) {
				obj = b;
			}
		}
		apiResponse.setData("Balance="+obj.getBalance());
		apiResponse.setStatus(200);
		return apiResponse;
	}

	public APIResponse transferMoney(int accNum1, int accNum2, int amount) {
		List<BankDetails> bank = (List<BankDetails>) customerRepository.findAll();
		BankDetails obj1 = null;
		BankDetails obj2 = null;
		for (BankDetails b : bank) {
			if (b.getAccountNumber() == accNum1) {
				obj1 = b;
			}
		}
		for (BankDetails b : bank) {
			if (b.getAccountNumber() == accNum2) {
				obj2 = b;
			}
		}
		obj2.setBalance(obj2.getBalance()+amount);
		obj1.setBalance(obj1.getBalance()-amount);
		apiResponse.setData("Amount transferred successfully");
		apiResponse.setStatus(200);
		return apiResponse;

	}

}

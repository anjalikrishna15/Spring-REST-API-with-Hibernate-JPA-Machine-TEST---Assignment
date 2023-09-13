package com.nissan.service;

import java.util.List;

import com.nissan.common.APIResponse;
import com.nissan.model.BankDetails;

public interface IAdminService {

	public List<BankDetails> getCustomerDetails();

	public BankDetails saveCustomerDetails(BankDetails bank);

	public BankDetails getCustomerByAccNum(int accNum);

	public APIResponse deleteCustomer(int accNum);
	
	public APIResponse updateCustomerDetails(int accNum,long mobNum,String email);

	
}

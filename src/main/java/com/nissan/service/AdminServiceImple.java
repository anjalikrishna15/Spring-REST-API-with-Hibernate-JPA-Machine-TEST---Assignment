package com.nissan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.common.Validation;
import com.nissan.model.BankDetails;
import com.nissan.repository.IAdminRepository;
@Transactional
@Service
public class AdminServiceImple implements IAdminService {

	@Autowired
	private IAdminRepository adminRepo;
	
	@Autowired
	private Validation validation;
	@Autowired
	private APIResponse apiResponse;
	
	public List<BankDetails> getCustomerDetails() {
		// TODO Auto-generated method stub
		return (List<BankDetails>) adminRepo.findAll();
	}

	public BankDetails saveCustomerDetails(BankDetails bank) {
		if(validation.isNameValid(bank.getCustName()) && validation.isNumberValid(bank.getMobNum())) {
			return adminRepo.save(bank) ;
		}
		return null ;
	}

	
	public BankDetails getCustomerByAccNum(int accNum) {
		// TODO Auto-generated method stub
		return adminRepo.findById(accNum).orElseThrow(()->new RuntimeException("Customer not found for id "+accNum));
	}


	public APIResponse deleteCustomer(int accNum) {
	
		adminRepo.deleteCustomer(accNum);
		apiResponse.setData("customer deleted successfully");
		apiResponse.setStatus(200);
		return apiResponse;
	}

	
	public APIResponse updateCustomerDetails(int accNum, long mobNum, String email) {
		adminRepo.updateCustomerDetails(accNum,mobNum,email);
		apiResponse.setData("customer updated successfully");
		apiResponse.setStatus(200);
		return apiResponse;
		
	}


	
	

}

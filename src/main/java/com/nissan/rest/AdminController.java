package com.nissan.rest;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;

import com.nissan.model.BankDetails;
import com.nissan.service.IAdminService;

//import com.nissan.util.JWTUtil;

@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@Autowired
	private APIResponse apiResponse;

	@Autowired
	// private JWTUtil jwtUtil;

	// list
	@GetMapping("/customers")
	public List<BankDetails> getCustomer() {// @RequestHeader(value="authorization",defaultValue="")String auth) throws
											// AccessDeniedException {

		// jwtUtil.verify(auth);
		return adminService.getCustomerDetails();

	}

	// search by account number
	@GetMapping("/customers/search/{accountNum}")
	public BankDetails getCustomerByAccNum(@PathVariable int accountNum) {// ,@RequestHeader(value="authorization",defaultValue="")String
																			// auth) throws AccessDeniedException{
		// jwtUtil.verify(auth);
		return adminService.getCustomerByAccNum(accountNum);

	}

	// add customer
	@PostMapping("/customers")
	public ResponseEntity<APIResponse> addCustomer(@RequestBody BankDetails bank) {
		if (adminService.saveCustomerDetails(bank) == null) {
			apiResponse.setData("INVALID DETAILS");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID ");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

		apiResponse.setData("customer added successfully");
		apiResponse.setStatus(200);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// update customer
	@PutMapping("/customers/update/{accNum}&{mobNum}&{email}")
	public ResponseEntity<APIResponse> updateCustomer(@PathVariable int accNum, @PathVariable long mobNum, @PathVariable String email) {
		adminService.updateCustomerDetails(accNum, mobNum, email);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// delete customer
	@DeleteMapping("/customers/{accNum}")
	public ResponseEntity<APIResponse> deleteEmployee(@PathVariable int accNum) {
		adminService.deleteCustomer(accNum);
		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}

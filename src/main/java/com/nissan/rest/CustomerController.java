package com.nissan.rest;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.service.ICustomerService;
import com.nissan.util.JWTUtil;

@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private APIResponse apiResponse;
	@Autowired
	private JWTUtil jwtUtil;

	@PutMapping("/users/deposit/{accNum}&{amount}")
	public ResponseEntity<APIResponse> deposit(@PathVariable int accNum, @PathVariable int amount,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {

		jwtUtil.verifyCustomer(auth);
		apiResponse = customerService.deposit(accNum, amount);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	@PutMapping("/users/withdraw/{accNum}&{amount}")
	public ResponseEntity<APIResponse> withdraw(@PathVariable int accNum, @PathVariable int amount,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {

		jwtUtil.verifyCustomer(auth);
		apiResponse = customerService.withdraw(accNum, amount);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	@GetMapping("/users/showbalance/{accNum}")
	public ResponseEntity<APIResponse> showBalance(@PathVariable int accNum,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {

		jwtUtil.verifyCustomer(auth);
		apiResponse = customerService.showBalance(accNum);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	@PutMapping("/users/transfer/{accNum1}&{accNum2}&{amount}")
	public ResponseEntity<APIResponse> transferMoney(@PathVariable int accNum1, @PathVariable int accNum2,
			@PathVariable int amount, @RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws AccessDeniedException {

		jwtUtil.verifyCustomer(auth);
		apiResponse = customerService.transferMoney(accNum1, accNum2, amount);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

}

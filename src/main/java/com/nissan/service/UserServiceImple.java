package com.nissan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.model.User;
import com.nissan.repository.IUserRepository;
import com.nissan.util.JWTUtil;

@Service
public class UserServiceImple implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private APIResponse apiResponse;
	@Autowired
	private JWTUtil jwtUtil;

	
	public APIResponse findUserByNameAndPassword(String userName, String password) {
		// verify user exist or not
		User user = userRepository.findUserByNameAndPassword(userName, password);
		if (user == null) {
			apiResponse.setData("INVALID CREDENTIALS");
			return apiResponse;
		}
		else if(user.getRoleId()==1) {
		// create token for admin (roleID=1)
		String token = jwtUtil.generateJWTAdmin(user);
		// storing more details and token
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("ACCESSTOKEN", token);
		data.put("role", user.getRoleId());
		data.put("UserName", user.getUserName());

		apiResponse.setStatus(200);
		apiResponse.setData(data);
		}
		else {
			// create token for Customer(roleID=2)
			String token = jwtUtil.generateJWTCustomer(user);
			// storing more details and token
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("ACCESSTOKEN", token);
			data.put("role", user.getRoleId());
			data.put("UserName", user.getUserName());

			apiResponse.setStatus(200);
			apiResponse.setData(data);
			
		}

		return apiResponse;
	}
  
}

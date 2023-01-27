package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;
import com.masai.service.CustomerService;
import com.masai.service.LoginService;
import com.masai.service.LoginServiceImpl;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	
	
//     Customer Login 
//	  ----------------

	@PostMapping("/customer/login")
	public ResponseEntity<String> customerLoginHandler(@Valid @RequestBody LoginDTO loginDTO) throws LoginException {
		
		String str = loginService.loginCustomer(loginDTO);
		
		return new ResponseEntity<String>(str,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
//     Customer Logout 
//	  ----------------
	
	@PostMapping("/customer/logout/{uuid}")
	public ResponseEntity<String> customerLogoutHandler(@PathVariable("uuid") String uuid) throws LoginException {
		
		String str = loginService.logOutCustomer(uuid);
		
		return new ResponseEntity<String>(str,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
//     Admin Login 
//	  --------------
	
	@PostMapping("/admin/login")
	public ResponseEntity<String> adminLoginHandler(@Valid @RequestBody LoginDTO loginDTO) throws LoginException {
		
		String str = loginService.loginAdmin(loginDTO);
		
		return new ResponseEntity<String>(str,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
//     Admin Logout
//	  -------------
	
	@PostMapping("/admin/logout/{uuid}")
	public ResponseEntity<String> adminLogoutHandler(@PathVariable("uuid") String uuid) throws LoginException {
		
		String str = loginService.logOutAdmin(uuid);
		
		return new ResponseEntity<String>(str,HttpStatus.ACCEPTED);
	}
	
}

















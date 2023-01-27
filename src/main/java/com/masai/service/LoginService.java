package com.masai.service;


import com.masai.exception.LoginException;
import com.masai.model.LoginDTO;

public interface LoginService {

	public String loginCustomer(LoginDTO loginDTO) throws LoginException;
	
	public String logOutCustomer(String uuid) throws LoginException;
	
	public String loginAdmin(LoginDTO loginDTO) throws LoginException;
	
	public String logOutAdmin(String uuid) throws LoginException;
	
}

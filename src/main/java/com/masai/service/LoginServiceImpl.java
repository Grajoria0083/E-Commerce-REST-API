package com.masai.service;


import java.time.LocalDateTime;
import java.util.Optional;

//import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.Admin;
import com.masai.model.CurentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;
import com.masai.repository.CustomerRepo;
import com.masai.repository.AdminRepo;
import com.masai.repository.CurentAdminSessionRepo;
import com.masai.repository.CurentCustomerSessionRepo;

import net.bytebuddy.utility.RandomString;


@Service
public class LoginServiceImpl implements LoginService{
	
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CurentAdminSessionRepo cAdminSessionRepo;
	
	@Autowired
	private CurentCustomerSessionRepo cCustomerSessionRepo;
	
	
	
	

	@Override
	public String loginCustomer(LoginDTO loginDTO) throws LoginException {
		
		Customer customer = customerRepo.findByMobileNo(loginDTO.getMobileNo());
		
		if (customer==null) {
			throw new LoginException("invalid mobileNo!");
		}
		
		else if (!customer.getPassword().equals(loginDTO.getPassword())) {
			throw new LoginException("invalid password!");
		}
		
		else {
			
			Optional<CurrentUserSession> optional = cCustomerSessionRepo.findById(customer.getCustomerId());
			
			if (optional.isPresent()) {
				throw new LoginException("this customer already Logged in!");
			}
			else {
				String key = RandomString.make(6);
				CurrentUserSession currentUserSession = new CurrentUserSession
						(customer.getCustomerId(), key, LocalDateTime.now());
				cCustomerSessionRepo.save(currentUserSession);
				
				return currentUserSession.toString();
			}
		}

		
		

	}
	
	
	
	
	

	@Override
	public String logOutCustomer(String uuid) throws LoginException {
		
		CurrentUserSession c = cCustomerSessionRepo.findByUuid(uuid);
		
		if (c!=null) {
			cCustomerSessionRepo.delete(c);
			return "Customer logout!";
		}
		
		throw new LoginException("invalid Customer uuid: "+uuid);
	}
	
	
	
	
	


	@Override
	public String loginAdmin(LoginDTO loginDTO) throws LoginException {

		Admin admin = adminRepo.findByMobileNo(loginDTO.getMobileNo());
		
		if (admin==null) {
			throw new LoginException("no admin registered with this mobileNo "+loginDTO.getMobileNo());
		}

		else if (!admin.getPassword().equals( loginDTO.getPassword())) {

			throw new LoginException("invalid password!");
		}
		
		else {
			Optional<CurentAdminSession> cAdminSession = cAdminSessionRepo.findById(admin.getAdminId());
			
			if (cAdminSession.isPresent()) {
				throw new LoginException("this admin already Logged In!");
			}
			else {
				String uuid = RandomString.make(5);
				
				CurentAdminSession curentAdminSession = new CurentAdminSession();
				curentAdminSession.setLocalDateTime(LocalDateTime.now());
				curentAdminSession.setCAdminId(admin.getAdminId());
				curentAdminSession.setUuid(uuid);
				
				cAdminSessionRepo.save(curentAdminSession);
				
				return curentAdminSession.toString();
			}
		}
	}


	@Override
	public String logOutAdmin(String uuid) throws LoginException {
		
		CurentAdminSession cAdminSession = cAdminSessionRepo.findByUuid(uuid);
		if (cAdminSession==null) {
			throw new LoginException("invalid admin uuid: "+uuid);
		}
		cAdminSessionRepo.delete(cAdminSession);
		
		return "Admin logout!";
	}

}

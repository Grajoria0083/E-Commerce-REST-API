package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.ProductException;
import com.masai.model.Admin;
import com.masai.model.CurentAdminSession;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.repository.AdminRepo;
import com.masai.repository.CurentAdminSessionRepo;
import com.masai.repository.CustomerRepo;
import com.masai.repository.ProductRepo;

@Service
public class AdminServiceImpl implements AdminService{
	
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CurentAdminSessionRepo cAdminSessionRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	
	

	@Override
	public Admin registerAdmin(Admin admin) throws AdminException{
		
		Admin optAdmin = adminRepo.findByMobileNo(admin.getMobileNo());
		
		if (optAdmin==null) {
			return adminRepo.save(admin);
		}
		throw new AdminException("Admin already registered by this mobileNo: "+admin.getMobileNo());
		
	}
	
	
	
	
	
	

	@Override
	public Admin updateAdmin(Admin admin, String uuid) throws AdminException{
		
	 	CurentAdminSession cAdminSession = cAdminSessionRepo.findByUuid(uuid);
	 	
	 	if (cAdminSession==null) {
			throw new AdminException("invalid uuid: "+uuid);
		}
		
	 	if (cAdminSession.getCAdminId()==admin.getAdminId()) {
	 		return adminRepo.save(admin);
		}
	 	
	 	throw new AdminException("invalid adminId: "+admin.getAdminId());
	}







	@Override
	public List<Customer> getAllCustomers(String uuid) throws CustomerException, AdminException {

	 	CurentAdminSession cAdminSession = cAdminSessionRepo.findByUuid(uuid);
	 	
	 	if (cAdminSession==null) {
			throw new AdminException("invalid uuid: "+uuid);
		}
		
		List<Customer> list = customerRepo.findAll();
		
		if (list.size()==0) {
			throw new CustomerException("No customer is registerd !"); 
		}
		return list;
	}







	@Override
	public String addListOfProducts(List<Product> pLists, String key) throws ProductException {

		CurentAdminSession cAdminSession = cAdminSessionRepo.findByUuid(key);
		
		if (cAdminSession==null) {
			throw new ProductException("invalid uuid: "+key);
		}
		
		productRepo.saveAll(pLists);
		
		return "All product added !";
		
	
	}







	@Override
	public String addProduct(Product product, String key) throws ProductException {
		
		CurentAdminSession cAdminSession = cAdminSessionRepo.findByUuid(key);
		
		if (cAdminSession==null) {
			throw new ProductException("invalid uuid: "+key);
		}
		
		productRepo.save(product);
		
		return product.getQuantity()+" "+ product.getProductName()+" product "+" added !";
	}







	@Override
	public Product updateProduct(Product product, String key) throws ProductException {
		
		CurentAdminSession cAdminSession = cAdminSessionRepo.findByUuid(key);
		
		if (cAdminSession==null) {
			throw new ProductException("invalid uuid: "+key);
		}
		
		return productRepo.save(product);

	}







	@Override
	public Product deleteProduct(String productName, String key) throws ProductException {

		CurentAdminSession cAdminSession = cAdminSessionRepo.findByUuid(key);
		
		if (cAdminSession==null) {
			throw new ProductException("invalid uuid: "+key);
		}

		Product p = productRepo.findByProductName(productName);

		if (p==null) {
			throw new ProductException(productName+" product is not avalible!");
		}
		
		productRepo.delete(p);
		
		return p;
	}







	@Override
	public List<Product> viewAllproducts() throws ProductException {
		
		List<Product> list = productRepo.findAll();
		
		if (list.size()==0) {
			throw new ProductException("No product is avalible !");
		}
		
		return list;
	}







	@Override
	public Admin getAdmin(String uuid) throws AdminException {
		
		CurentAdminSession curentAdminSession = cAdminSessionRepo.findByUuid(uuid);
	
		if (curentAdminSession==null) {
			throw new AdminException("invalid uuid "+uuid);
		}
		
		 Optional<Admin> optional= adminRepo.findById(curentAdminSession.getCAdminId());
		 
		 return optional.get();
	}







	@Override
	public Admin deleteAdmin(String uuid) throws AdminException {

		CurentAdminSession curentAdminSession = cAdminSessionRepo.findByUuid(uuid);
	
		if (curentAdminSession==null) {
			throw new AdminException("invalid uuid "+uuid);
		}
		
		 Optional<Admin> optional= adminRepo.findById(curentAdminSession.getCAdminId());
		 
		 cAdminSessionRepo.delete(curentAdminSession);
		 adminRepo.delete(optional.get());
		 return optional.get();
	}









}



















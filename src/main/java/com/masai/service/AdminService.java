package com.masai.service;

import java.util.List;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.ProductException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.Product;

public interface AdminService {

	public Admin registerAdmin(Admin admin) throws AdminException;
	
	public Admin updateAdmin(Admin admin, String uuid) throws AdminException;
	
	public Admin getAdmin(String uuid) throws AdminException ;
	
	public Admin deleteAdmin(String uuid) throws AdminException ;
	
	public List<Customer> getAllCustomers(String uuid) throws CustomerException, AdminException;
	
	public String addListOfProducts (List<Product> pLists, String key) throws ProductException;
	
	public String addProduct (Product product, String key) throws ProductException;
	
	public Product updateProduct (Product product, String key) throws ProductException;
	
	public Product deleteProduct (String productName, String key) throws ProductException;
	
	public List<Product> viewAllproducts () throws ProductException ;
	
//	public Integer getNoOfProductByProductName (String productName) throws ProductException;
	
//	public Customer getCustomerById(Integer id) throws CustomerException;
	
//	public Object<> getListofCoustomerIdandName() throws CustomerException;
	
	
	
}

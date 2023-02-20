package com.masai.service;

import java.util.List;

import com.masai.exception.CartException;
import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.ProductException;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.OrderDetails;
import com.masai.model.Product;

public interface CustomerService {

	public Customer registerCustomer(Customer customer) throws CustomerException ;
	
	public Customer updateCustomer(Customer customer, String uuid) throws CustomerException ;
	
	public Customer getCustomer(String uuid) throws CustomerException ;
	
	public Customer deleteCustomer(String uuid) throws CustomerException ;
	
	public List<Product> viewAllproducts () throws ProductException ;
	
	public List<Product> sortProductsByPrice () throws ProductException ;
	
	public String addToCart (Integer productId, Integer quantity, String uuid) throws CustomerException, ProductException, CartException;
	
	public Cart updateCart (Cart cart, String uuid) throws CustomerException, ProductException, CartException;
	
	public String removeProductToCart (Integer productId, String uuid) throws CustomerException, ProductException, CartException;
	
//	public Cart deleteCart (String uuid)  throws CustomerException, ProductException, CartException;
	
	public Cart getCart (String uuid)  throws CustomerException, ProductException, CartException;
	
	public OrderDetails order (Boolean status, String uuid) throws CustomerException, OrderException;

}

package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.Product;


@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{

//	public Cart findByCustomerId(Integer customerId);
	
//	public List<Product> findByProducts(Product product);
	
//	public Cart findByCustomerIdAndProductId(Integer customerId, Integer productId);
	
}

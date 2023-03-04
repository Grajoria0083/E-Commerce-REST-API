package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CartException;
import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.ProductException;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.OrderDetails;
import com.masai.model.Product;
import com.masai.service.CustomerService;


@RequestMapping("/customer")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
	
	

	@Autowired
	private CustomerService customerService;
	
	
	
//								Register Customer
//								-----------------
	
	@PostMapping("/")
	public ResponseEntity<Customer> registerCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException {

		Customer C = customerService.registerCustomer(customer);

		return new ResponseEntity<Customer>(C, HttpStatus.ACCEPTED);
	}
	
	
	
	
	
//								Update Customer
//								-----------------

	@PutMapping("/{key}")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer, @PathVariable("key") String key) throws CustomerException {
		
		Customer C = customerService.updateCustomer(customer, key);
		
		return new ResponseEntity<Customer>(C, HttpStatus.ACCEPTED);
	}
	
	
	
	
	
//								Get Customer
//								-------------
	
	@GetMapping("/{uuid}")
	public ResponseEntity<Customer> getCustomerHandler(@PathVariable("uuid") String uuid) throws CustomerException {
		
		Customer C = customerService.getCustomer(uuid);
		
		return new ResponseEntity<Customer>(C, HttpStatus.OK);
	}
	
	
	
	
	
//								Delete Customer
//								----------------
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Customer> deleteCustomerHandler(@PathVariable("uuid") String uuid) throws CustomerException {
		
		Customer C = customerService.deleteCustomer(uuid);
		
		return new ResponseEntity<Customer>(C, HttpStatus.OK);
	}
	
	
	
	
	
//								Get All Products
//								----------------
	

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProductsHandler() throws ProductException {
		
		List<Product> products = customerService.viewAllproducts();
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	
	
	
	
	
//								Get Product By Id
//								-----------------


	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductByIdHandler(@PathVariable("productId") Integer productId) throws ProductException {

		Product product = customerService.getProductById(productId);

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	
	
	
	
	
	
//								Get Product By Name
//								-------------------


	@GetMapping("/products/{productName}")
	public ResponseEntity<List<Product>> getProductByNameHandler(@PathVariable String productName) throws ProductException {

		List<Product> products = customerService.getProductByName(productName);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	
	
	
	
							//	 Sort Products
							//	----------------


	
	@GetMapping("/sortProducts/{type}")
	public ResponseEntity<List<Product>> getSorttedProductHandler(@PathVariable String type) throws ProductException {

		List<Product> products = customerService.sortProductsByPrice(type);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	
	
	
	
	
//								Add Product to Cart
//								-------------------
	
	@GetMapping("/cart/{productId}/{quantity}/{uuid}")
	public ResponseEntity<String> addToCartHandler(
			@PathVariable("productId") Integer productId,
			@PathVariable("quantity") Integer quantity,
			@PathVariable("uuid") String uuid) throws CustomerException, ProductException, CartException {
		
		String C = customerService.addToCart(productId, quantity, uuid);
		
		return new ResponseEntity<String>(C, HttpStatus.OK);
	}
	
	
	
	
	
	
//								remove Product from Cart
//								------------------------
	
	@DeleteMapping("/cart/{cartItemId}/{uuid}")
	public ResponseEntity<String> removeCartItemHandler(@PathVariable("cartItemId") Integer cartItemId, @PathVariable("uuid") String uuid) throws CustomerException, ProductException, CartException {
		
		String C = customerService.removeProductToCart(cartItemId,uuid);
		
		return new ResponseEntity<String>(C, HttpStatus.OK);
	}

	
	
	
	
	
//								Update Cart
//								-----------
	
	@PutMapping("/Cart/{uuid}")
	public ResponseEntity<Cart> updateCartHandler(@RequestBody Cart cart, @PathVariable("uuid") String uuid) throws CustomerException, ProductException, CartException {
		
		Cart C = customerService.updateCart(cart, uuid);
		
		return new ResponseEntity<Cart>(C, HttpStatus.ACCEPTED);
	}
	
	
	
	
//								Get Cart
//							   ----------
	
	@GetMapping("/cart/{uuid}")
	public ResponseEntity<Cart> getCartHandler(@PathVariable String uuid) throws CustomerException, ProductException, CartException {
		
		Cart C = customerService.getCart(uuid);
		
		return new ResponseEntity<Cart>(C, HttpStatus.OK);
	}
	
	
	
	
//								Order Items
//							   -------------
	
	@GetMapping("/order/{status}/{uuid}")
	public ResponseEntity<OrderDetails> orderHandler(@PathVariable("status") Boolean status, @PathVariable("uuid") String uuid) throws CustomerException, ProductException, CartException, OrderException {
		
		OrderDetails orderDetails = customerService.order(status, uuid);
		
		return new ResponseEntity<OrderDetails>(orderDetails, HttpStatus.OK);
	}
	

 
	
	
	
	
	
	
}

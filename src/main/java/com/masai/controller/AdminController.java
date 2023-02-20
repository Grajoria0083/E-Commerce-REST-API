package com.masai.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.ProductException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.service.AdminService;
//import com.masai.service.CustomerService;



@RequestMapping("/admin")
@RestController
public class AdminController {

	@Autowired
	private AdminService adminServiceImpl;
	
	
//	@Autowired
//	private CustomerService customerServiceImpl;
	
	
	
	
//	Register Admin
//	----------------
	
	@PostMapping("/")
	public ResponseEntity<Admin> registerAdminHandler(@Valid @RequestBody Admin admin) throws AdminException{
		
		Admin adm = adminServiceImpl.registerAdmin(admin);
		
		return new ResponseEntity<Admin>(adm,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
//	Update Admin
//	--------------
	
	@PutMapping("/{uuid}")
	public ResponseEntity<Admin> updateAdminHandler(@Valid @RequestBody Admin admin, @PathVariable("uuid") String uuid) throws AdminException{
		
		Admin adm = adminServiceImpl.updateAdmin(admin, uuid);
		
		return new ResponseEntity<Admin>(adm,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
//	Get Admin
//	--------------
	
	@GetMapping("/{uuid}")
	public ResponseEntity<Admin> getAdminHandler(@PathVariable("uuid") String uuid) throws AdminException{
		
		Admin adm = adminServiceImpl.getAdmin(uuid);
		
		return new ResponseEntity<Admin>(adm,HttpStatus.OK);
	}
	
	
	
	
	
	
//	Delete Admin
//	--------------
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Admin> deleteAdminHandler(@PathVariable("uuid") String uuid) throws AdminException{
		
		Admin adm = adminServiceImpl.deleteAdmin(uuid);
		
		return new ResponseEntity<Admin>(adm,HttpStatus.OK);
	}
	
	
	
	
	
//	View All Customers
//	------------------
	
	@GetMapping("/viewCustomers/{uuid}")
	public ResponseEntity<List<Customer>> getAllCustomerHandler(@PathVariable String uuid) throws AdminException, CustomerException{
		
		List<Customer> list = adminServiceImpl.getAllCustomers(uuid);
		
		return new ResponseEntity<List<Customer>>(list,HttpStatus.OK);
	}
	
	
	
	
	
	
//	Add Product
//	------------
	
	@PostMapping("product/{uuid}")
	public ResponseEntity<String> addProductHandler(@Valid @RequestBody Product product, @PathVariable("uuid") String uuid) throws ProductException{
		
		String p = adminServiceImpl.addProduct(product, uuid);
		
		return new ResponseEntity<String>(p,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
//	Add List Of Product
//	--------------------
	
	@PostMapping("products/{uuid}")
	public ResponseEntity<String> addListOfProductHandler(@Valid @RequestBody List<Product> product, @PathVariable("uuid") String uuid) throws ProductException{
		
		String p = adminServiceImpl.addListOfProducts(product, uuid);
		
		return new ResponseEntity<String>(p,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
//	Get List Of Product
//	--------------------
	
	@GetMapping("Products")
	public ResponseEntity<List<Product>> getAllProductHandler() throws ProductException{
		
		List<Product> list = adminServiceImpl.viewAllproducts();
		
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	
	
	
	
//	Update Product
//	---------------
	
	@PutMapping("product/{uuid}")
	public ResponseEntity<Product> updateProductHandler(@Valid @RequestBody Product product, @PathVariable("uuid") String uuid) throws ProductException{
		
		Product p = adminServiceImpl.updateProduct(product, uuid);
		
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
//	Delete Product
//	---------------
	
	@DeleteMapping("product/{productName}/{uuid}")
	public ResponseEntity<Product> deleteProductHandler(@PathVariable("productName") String productName , @PathVariable("uuid")  String uuid) throws ProductException{
		
		System.out.println("controller"+ productName);
		
		Product p = adminServiceImpl.deleteProduct(productName, uuid);
		
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
}











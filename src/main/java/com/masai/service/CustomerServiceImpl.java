package com.masai.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CartException;
import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.ProductException;
import com.masai.model.Cart;
import com.masai.model.CartItem;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.OrderDetails;
import com.masai.model.Product;
import com.masai.repository.CustomerRepo;
import com.masai.repository.OrderRepo;
import com.masai.repository.ProductRepo;

import ch.qos.logback.core.joran.conditional.IfAction;

import com.masai.repository.CartItemRepo;
import com.masai.repository.CartRepo;
import com.masai.repository.CurentCustomerSessionRepo;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CurentCustomerSessionRepo cCustomerSessionRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CartItemRepo cartItemRep;
	
	@Autowired
	private OrderRepo orderRepo;
	
	
	
	
	
							

	@Override
	public Customer registerCustomer(Customer customer)throws CustomerException {
		
		Customer c = customerRepo.findByMobileNo(customer.getMobileNo());
		
		if (c!=null) {
			throw new CustomerException("customer already register with this mobileNo: "+customer.getMobileNo());
		}
		
		customerRepo.save(customer);
		
		return customer;
	}
	
	
	
	
	

	@Override
	public Customer updateCustomer(Customer customer, String uuid) throws CustomerException {
		
		CurrentUserSession c = cCustomerSessionRepo.findByUuid(uuid);
		
		if (c==null) {
			throw new CustomerException("invalid uuid: "+uuid);
		}
		
		if (customer.getCustomerId()==c.getCCustomerId()) {
			customerRepo.save(customer);
			return customer;
		}
		
		throw new CustomerException("invalid userId: "+customer.getCustomerId());
	}
	
	
	
	
	
	
	@Override
	public Customer getCustomer(String uuid) throws CustomerException {
		
//		if (cCustomerSessionRepo.findAll().size()==0) {
//			throw new CustomerException("Please login first !");
//		}
		
		CurrentUserSession customerSession = cCustomerSessionRepo.findByUuid(uuid);
	
		if (customerSession==null) {
			throw new CustomerException("invalid uuid "+uuid);
		}

		 Optional<Customer> optional = customerRepo.findById(customerSession.getCCustomerId());
		 
		 return optional.get();
		
	}






	@Override
	public Customer deleteCustomer(String uuid) throws CustomerException {
		
		CurrentUserSession customerSession = cCustomerSessionRepo.findByUuid(uuid);
	
		if (customerSession==null) {
			throw new CustomerException("invalid uuid "+uuid);
		}

		 Optional<Customer> optional = customerRepo.findById(customerSession.getCCustomerId());
		 
		 cCustomerSessionRepo.delete(customerSession);
		 customerRepo.delete(optional.get());
		 
		 return optional.get();
	}
	
	
	
	
	
	
	
	
	
	@Override
	public List<Product> viewAllproducts() throws ProductException {
		
		List<Product> list = productRepo.findAll();
		
		if (list.size()==0) {
			throw new ProductException("No product is avalible!");
		}
		
		return list;
	}
	
	
	
	
	
	@Override
	public List<Product> sortProductsByPrice() throws ProductException {
		
		List<Product> list = productRepo.findAll();
	
		if (list.size()==0) {
			throw new ProductException("No product is avalible!");
		}
		else {
			Collections.sort(list, new Comparator<Product>() {
				
				@Override
				public int compare(Product p1, Product p2) {
					
					if (p1.getPrice()>p2.getPrice()) {
						return 1;
					}
					else if(p1.getPrice()<p2.getPrice()){
						return -1;
					}
					else {
						return p1.getProductName().compareTo(p2.getProductName());
					}
				};
			});
		}
		return list;
		
	}


	



	@Override
	public String addToCart(Integer productId, Integer quantity, String uuid) throws CustomerException, ProductException, CartException {
		
		CurrentUserSession currentUserSession = cCustomerSessionRepo.findByUuid(uuid);
		
		if (currentUserSession!=null) {
			Customer customer  = customerRepo.findById(currentUserSession.getCCustomerId()).get();
			Optional<Product> pOptional = productRepo.findById(productId);

			if (pOptional.isPresent()) {
				Product product = pOptional.get();

				if (product.getQuantity()>=quantity) {
					Cart cCart = customer.getCart();
					
					if (cCart!=null) {
						List<CartItem> cartItems = cCart.getCartItems();
						boolean flag = false;

							for (CartItem cItem : cartItems) {
								if (cItem.getProduct().getProductId()==productId) {
									cItem.setProductQuantity(cItem.getProductQuantity()+quantity);
									cartItemRep.save(cItem);
									flag = true;
									break;
								}
							}
							if (!flag) {
								CartItem newCartItem = new CartItem();
								newCartItem.setProductQuantity(quantity);
								newCartItem.setProduct(product);
								cartItemRep.save(newCartItem);
								cCart.getCartItems().add(newCartItem);
								
							}				
					}
					else {
						Cart cart = new Cart();
						CartItem newCartItem = new CartItem();
						newCartItem.setProductQuantity(quantity);
						newCartItem.setProduct(product);
						cartItemRep.save(newCartItem); 
						
						cart.getCartItems().add(newCartItem);
						cartRepo.save(cart);
						
						customer.setCart(cart);
						customerRepo.save(customer);
					}	
					product.setQuantity(product.getQuantity()-quantity);
					productRepo.save(product);
					return "Product "+product.getProductName()+" added to the Cart !";
				}
				
				else throw new ProductException("we have only "+product.getQuantity()+" product!");
			}
			else throw new ProductException("invalid productId: "+productId);
		}
		else throw new CustomerException("invalid uuid: "+uuid);
	}







	@Override
	public Cart updateCart(Cart cart, String uuid) throws CartException, CustomerException {

		CurrentUserSession currentUserSession = cCustomerSessionRepo.findByUuid(uuid);
		
		if (currentUserSession==null) {
			throw new CustomerException("invalid uuid: "+uuid);
		}
		
		Optional<Cart> optional = cartRepo.findById(cart.getCartId());
		
		if (!optional.isPresent()) {
			throw new CartException("invalid cartId: "+cart.getCartId());
		}
		
		return cartRepo.save(cart);
	}


	
	




	@Override
	public String removeProductToCart(Integer cartItemId, String uuid) throws CustomerException, ProductException, CartException {
		
		CurrentUserSession currentUserSession = cCustomerSessionRepo.findByUuid(uuid);
		
		if (currentUserSession!=null) {
			Customer customer = customerRepo.findById(currentUserSession.getCCustomerId()).get();
				
			Cart cCart = cartRepo.findById(customer.getCart().getCartId()).get();

			if (cCart!=null) {
				List<CartItem> cartItems = cCart.getCartItems();

				for (CartItem cItem : cartItems) {
					if (cItem.getCartItemId()==cartItemId) {
						Product product = productRepo.findById(cItem.getProduct().getProductId()).get();
						product.setQuantity(product.getQuantity()+cItem.getProductQuantity());
						productRepo.save(product);
						cartItems.remove(cItem);
						cartRepo.save(cCart);
						cartItemRep.delete(cItem);

						return "Product "+product.getProductName()+" removed from the Cart!";
					}			
				}	
				throw new ProductException("no product added to cart by this cartItemId: "+cartItemId);
			}	
			throw new ProductException("no item added to the cart!");
		}
		throw new CustomerException("invalid uuid: "+uuid);
	}






//	@Override
//	public Cart deleteCart(String uuid) throws CustomerException, ProductException, CartException{
//
//		CurrentUserSession currentUserSession = cCustomerSessionRepo.findByUuid(uuid);
//		
//		if (currentUserSession==null) {
//			throw new CustomerException("invalid uuid: "+uuid);
//		}
//		
//		return null;
//	}
	






	@Override
	public Cart getCart(String uuid) throws CustomerException, ProductException, CartException {
		
		CurrentUserSession currentUserSession = cCustomerSessionRepo.findByUuid(uuid);
		
		if (currentUserSession==null) {
			throw new CustomerException("invalid uuid: "+uuid);
		}
		
		Cart cart = customerRepo.findById(currentUserSession.getCCustomerId()).get().getCart();
	
			if (cart!=null) {
				return cart;
			}
			
			throw new CartException("Cart is empty!");
	}

	
	






	@Override
	public OrderDetails order(Boolean status, String uuid) throws CustomerException, OrderException {

		CurrentUserSession customerSession = cCustomerSessionRepo.findByUuid(uuid);

		if (customerSession!=null) {

			Customer customer = customerRepo.findById(customerSession.getCCustomerId()).get();
			Cart cart = customer.getCart();
			
			if (cart!=null) {
				double totalItemsAmount = 0;
				Integer totalItems = 0;
				List<CartItem> cartItems =  cart.getCartItems();

				for (CartItem cartItem : cartItems) {
					totalItemsAmount += cartItem.getProduct().getPrice();
					totalItems += cartItem.getProductQuantity();
				}
				
				
				OrderDetails orderDetails = new OrderDetails();
				
				if (status) {
					if (customer.getWallet()>=totalItemsAmount) {
						orderDetails.setPaymentStatus("payment done successfully ");
						customer.setWallet(customer.getWallet()- (int) totalItemsAmount);
						customerRepo.save(customer);
					}
					else 
						throw new CustomerException("amount is not sufficient !");
				}
				else {
					orderDetails.setPaymentStatus("cash on delivery ");
				}
				
//				cart.getCartItems().removeAll(cartItems);
//				cartRepo.save(cart);
//				for (CartItem cartItem : cartItems) {
//					cartItemRep.delete(cartItem);
//				}
				
				
				orderDetails.setOrderDateTime(LocalDateTime.now());
				orderDetails.setTotalAmount(totalItemsAmount);
				orderDetails.setTotalItems(totalItems);
				orderDetails.setAddress(customerRepo.findById(customerSession.getCCustomerId()).get().getAddress());
				orderDetails.setCustomer(customerRepo.findById(customerSession.getCCustomerId()).get());
				orderRepo.save(orderDetails);
				return orderDetails;
				

			}
			throw new CustomerException("cart is empty !");
		}
		throw new CustomerException("invalid uuid "+uuid);

	}



}











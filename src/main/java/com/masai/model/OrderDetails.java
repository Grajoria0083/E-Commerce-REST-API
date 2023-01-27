package com.masai.model;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;


@Data
@Entity
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	private LocalDateTime orderDateTime;
	
//	private LocalDateTime orderDeliveryDateTime;
	
	private Integer totalItems;
	
	private Double totalAmount;
	
//	private Integer paidAmount;
	
	private String paymentStatus;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
//	
//	@OneToMany
//	private List<Product> products =  new ArrayList<>();
	
	
	
	
}

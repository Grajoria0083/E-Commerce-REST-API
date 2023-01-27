package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
//@Table(name = "Cart_Data")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartItemId;
	
	private Integer productQuantity;
	
	@JsonIgnoreProperties(value = "quantity")
	@OneToOne
	private Product product;
	
	
	

}

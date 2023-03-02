package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Product {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotBlank(message = "Product name must not be blank!")
	@NotNull(message = "Product name is mandatory!")
	private String productImage;
	
	@NotBlank(message = "Product name must not be blank!")
	@NotNull(message = "Product name is mandatory!")
	private String productName;
	
//	@NotBlank(message = "Price must not be blank!")
//	@NotNull(message = "Price is mandatory!")
	private double price;
	
	
	@NotBlank(message = "Color must not be blank!")
	@NotNull(message = "Color is mandatory!")
	private String Color;
	
	
	@NotBlank(message = "Dimension must not be blank!")
	@NotNull(message = "Dimension is mandatory!")
	private String dimension;
	
	
	@NotBlank(message = "Manufacturer must not be blank!")
	@NotNull(message = "Manufacturer is mandatory!")
	private String manufacturer;
	
	
	@NotBlank(message = "Specification must not be blank!")
	@NotNull(message = "Specification is mandatory!")
	private String specification;
	
	
	@NotNull(message = "quantity is mandatory!")
	@Min(value = 1, message = "Please select atleast 1 product!")
	private Integer quantity;
	
	
	@NotNull(message = "Category is mandatory!")
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;

}

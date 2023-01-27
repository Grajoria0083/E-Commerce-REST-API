package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	
//	@NotBlank
//	@NotNull(message = "HouseNo is mandatory!")
	private String houseNo;
	
//	@NotNull(message = "Country is mandatory!")
	private String country;
	
//	@NotNull(message = "State is mandatory!")
	private String state;
	
//	@NotNull(message = "City is mandatory!")
	private String city;
	
//	@NotNull(message = "Pincode is mandatory!")
	private String pincode;
	
	
}

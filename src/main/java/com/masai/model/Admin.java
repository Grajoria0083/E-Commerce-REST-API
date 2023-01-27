package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Admin {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
	
	@NotNull(message = "name is mandatory!")
	@Pattern(regexp="[a-zA-Z]{2,25}",  message = "Name length must be min 2 and max 25 characters only!")
	private String name;
	

	@NotNull(message = "email is mandatory!")
	@Email(message = "email must be in proper format!")
	private String email;
	
	
	@NotNull(message = "email is mandatory!")
	@Pattern(regexp="[6-9]{1}[0-9]{9}", message = "Mobile must be 10 digits a valid number!")
	private String mobileNo;


	@NotNull(message = "password is mandatory!")
	@Size(min = 6, max = 15, message = "Password length must be between 6 to 15 words!")
	private String password;
	
	
}

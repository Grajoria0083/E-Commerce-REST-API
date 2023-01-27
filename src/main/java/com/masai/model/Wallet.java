package com.masai.model;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

//@Data
//@Entity
public class Wallet {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer walletId;
	
	private Integer wallet;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
}

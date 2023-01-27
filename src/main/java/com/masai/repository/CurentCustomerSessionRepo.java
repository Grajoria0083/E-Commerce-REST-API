package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.OrderDetails;


@Repository
public interface CurentCustomerSessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findByUuid(String uuid);
	
//	public CurrentUserSession findCurentCustomerSessionByUuid(String uuid);
	
}

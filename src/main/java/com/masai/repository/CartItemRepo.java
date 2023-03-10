package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CartItem;


@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer>{

}

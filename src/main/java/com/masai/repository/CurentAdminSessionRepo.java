package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CurentAdminSession;


@Repository
public interface CurentAdminSessionRepo extends JpaRepository<CurentAdminSession, Integer>{

	public CurentAdminSession findByUuid(String uuid);
}

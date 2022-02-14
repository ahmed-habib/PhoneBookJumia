package com.jumia.phone.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumia.phone.book.entities.Customer;

public interface CustomerDAO extends JpaRepository<Customer,Integer>{

	List<Customer> findByPhoneLike(String code);
	
}

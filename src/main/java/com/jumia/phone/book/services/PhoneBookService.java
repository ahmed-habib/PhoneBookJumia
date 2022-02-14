package com.jumia.phone.book.services;

import java.util.List;

import com.jumia.phone.book.dtos.CustomerDTO;

public interface PhoneBookService {
	
	List<CustomerDTO> LoadAllCustomers(String countryCode,String state);
}

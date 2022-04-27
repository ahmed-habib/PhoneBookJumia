package com.jumia.phone.book.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumia.phone.book.dao.CustomerDAO;
import com.jumia.phone.book.dtos.BaseDTO;
import com.jumia.phone.book.dtos.CustomerDTO;
import com.jumia.phone.book.dtos.PhoneState;
import com.jumia.phone.book.entities.BaseEntity;
import com.jumia.phone.book.entities.Customer;
import com.jumia.phone.book.services.BasicServiceImpl;
import com.jumia.phone.book.services.PhoneBookService;

@Service
@Transactional
public class PhoneBookServiceImpl extends BasicServiceImpl<BaseDTO, BaseEntity> implements PhoneBookService {

	@Autowired
	private CustomerDAO customerDAO;

	static Map<String, String[]> countries;
	static {

		countries = new HashMap<>();
		countries.put("+237", new String[] { "Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$" });
		countries.put("+251", new String[] { "Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$" });
		countries.put("+212", new String[] { "Morocco", "\\(212\\)\\ ?[5-9]\\d{8}$" });
		countries.put("+258", new String[] { "Mozambique", "\\(258\\)\\ ?[28]\\d{7,8}$" });
		countries.put("+256", new String[] { "Uganda", "\\(256\\)\\ ?\\d{9}$" });
	}

	@Override
	public List<CustomerDTO> loadAllCustomers(String countryCode, String state) {
		List<CustomerDTO> customersDtos = new ArrayList<>();
		Map<String, String[]> validCountries = getCountryDetails(countryCode);

		List<Customer> customers = new ArrayList<>();
		for (String country : validCountries.keySet()) {

			String key = "("+country.substring(1)+")%";
			customers.addAll(customerDAO.findByPhoneLike(key));
		}

		customersDtos = customers.stream().map(a -> (CustomerDTO) convertToDTO(a, new CustomerDTO()))
				.collect(Collectors.toList());

		for (CustomerDTO customer : customersDtos) {
			String phoneCode = "+" + customer.getPhone().substring(1, 4);
			customer.setCountry(validCountries.get(phoneCode)[0]);
			customer.setCountryCode(phoneCode);
			if (customer.getPhone().matches(validCountries.get(phoneCode)[1])) {
				customer.setState(PhoneState.VALID);
			} else {
				customer.setState(PhoneState.NOTVALID);
			}
		}

		if (state != null && !state.equals(PhoneState.ALL.getValue())) {
			if (state.equals(PhoneState.VALID.getValue())) {
				customersDtos = customersDtos.stream().filter(a -> a.getState().equals(PhoneState.VALID.getValue()))
						.collect(Collectors.toList());
			} else if (state.equals(PhoneState.NOTVALID.getValue())) {
				customersDtos = customersDtos.stream().filter(a -> a.getState().equals(PhoneState.NOTVALID.getValue()))
						.collect(Collectors.toList());
			}
		}

		return customersDtos;

	}

	public Map<String, String[]> getCountryDetails(String countryCode) {

		Map<String, String[]> validCountries = new HashMap<>();
		if (countryCode == null || countryCode == "" || countryCode.equals(PhoneState.ALL.getValue())) {
			validCountries.putAll(countries);
		} else {
			validCountries.put(countryCode, countries.get(countryCode));
		}
		return validCountries;
	}

}

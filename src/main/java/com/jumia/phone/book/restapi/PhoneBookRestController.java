package com.jumia.phone.book.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumia.phone.book.dtos.CustomerDTO;
import com.jumia.phone.book.dtos.ResponseDTO;
import com.jumia.phone.book.dtos.RestProvider;
import com.jumia.phone.book.dtos.StatusCode;
import com.jumia.phone.book.services.PhoneBookService;

@RestController
@RequestMapping("/api/")
public class PhoneBookRestController {

	@Autowired
	RestProvider restProvider;
	@Autowired
	PhoneBookService phoneBookService;

	@GetMapping(value = {"customers","customers/{country_code}/{state}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getAllCustomers(
			@PathVariable(name = "country_code", required = false) String countryCode,
			@PathVariable(name = "state", required = false) String state) {
		// Define Custom Response DTO object with custom status Message and
		// actual data
		ResponseDTO responseDto = null;

		// Get Result CustomerDTO Object
		List<CustomerDTO> resultObj = phoneBookService.loadAllCustomers(countryCode, state);

		if (resultObj != null)
			responseDto = new ResponseDTO(StatusCode.SUCCESSFULL, "Data Retreived Successfully", resultObj);
		else
			responseDto = new ResponseDTO(StatusCode.NOTFOUND, "No Data", null);

		return restProvider.addObj(responseDto);
	}

}

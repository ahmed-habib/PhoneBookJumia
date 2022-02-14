package com.jumia.phone.book.config;

import com.jumia.phone.book.dtos.StatusCode;

public class PhoneBookApplicationException extends RuntimeException {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = 2021607970483973511L;
	
	
	private StatusCode code;
	
	/**
	 * Default Constructor
	 * */
	public PhoneBookApplicationException() {}

	/**
	 * Constructor
	 * @param message that will be displayed in Exception and passed to super Exception class
	 */
	public PhoneBookApplicationException(StatusCode code , String message) {
		super(message);
		
		this.setCode(code);

	}

	/**
	 * @return the code
	 */
	public StatusCode getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(StatusCode code) {
		this.code = code;
	}

	
	
}

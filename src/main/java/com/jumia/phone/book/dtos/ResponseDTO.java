package com.jumia.phone.book.dtos;

import java.io.Serializable;

/**
 * Helper Class That represents Custom Response DTO which will be used in Response for REST APIs
 *
 */
public class ResponseDTO implements Serializable {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = -5428751645851374005L;
	
	
	private ResponseStatusDTO status;
	private Object data;
	
	/**
	 * Constructor
	 * @param customCode Status Code that represent status of Response
	 * @param customMessage Custom Message 
	 * @param data Actual Response Data
	 */
	public ResponseDTO(StatusCode customCode,String customMessage, Object data) {

		this.status = new ResponseStatusDTO(customCode,customMessage);
		this.data = data;
	}
	
	
	public ResponseStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ResponseStatusDTO status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}

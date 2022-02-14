package com.jumia.phone.book.dtos;

public enum PhoneState {
	ALL("all"),VALID("valid"),NOTVALID("not valid");
	
	private final String value;
    
	PhoneState(String value)
    {
    	this.value = value;
    }
    
    public String getValue()
    {
    	return this.value;
    }
}

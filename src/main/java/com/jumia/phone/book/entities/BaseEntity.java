/**
 * 
 */
package com.jumia.phone.book.entities;

import java.io.Serializable;

/**
 * @author Ahmed habib
 *
 */
public abstract class BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract String toString();
	
	public abstract boolean equals(Object obj);
	
	public abstract  int hashCode();

}

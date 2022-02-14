package com.jumia.phone.book.dtos;

import java.io.Serializable;

/**
 * Helper Class That represents Custom Response Error that will be used if exception happend as a Response
 * @author M.Othman
 *
 */
public class ResponseError implements Serializable {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = -1946378664375691245L;

	private int customCode;

	private long timeStamp;

	private String customMessage;

	/**
	 * Constructor
	 * @param customCode Status Code that represent status of Response
	 * @param customMessage Custom Message
	 * @param timeStamp time stamp
	 */
	public ResponseError(StatusCode customCode, String customMessage, long timeStamp) {
		this.customCode = customCode.getValue();
		this.customMessage = customMessage;
		this.timeStamp = timeStamp;
	}

	public int getCustomCode() {
		return customCode;
	}

	public void setCustomCode(int customCode) {
		this.customCode = customCode;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

}

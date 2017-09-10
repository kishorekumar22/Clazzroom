package com.duocorp.myclazz.beans;

import com.duocorp.myclazz.constants.ResponseConstants;

/**
 * The Class ResponseInfo.
 */
public class ResponseInfo {

	/** The code. */
	String code;

	/** The message. */
	String message;

	/**
	 * Instantiates a new response info.
	 *
	 * @param responseInfo
	 *            the response info
	 */
	public ResponseInfo(ResponseConstants responseInfo) {
		this.code = responseInfo.getCode();
		this.message = responseInfo.getMessage();
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}

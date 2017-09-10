package com.duocorp.myclazz.exceptions;

/**
 * The Class StudentDataException.
 * 
 * @author kishorekumar.s
 */
public class StudentDataException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new student data exception.
	 *
	 * @param message
	 *            the message
	 */
	public StudentDataException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new student data exception.
	 */
	public StudentDataException() {
		// To do constructor
	}

}

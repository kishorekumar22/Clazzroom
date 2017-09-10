package com.duocorp.myclazz.beans;

/**
 * The Class MemberType.
 * 
 * @author kishorekumar.s
 */
public class MemberType {

	/** The email id. */
	String emailId;

	/** The name. */
	String name;

	/**
	 * Instantiates a new member type.
	 */
	public MemberType() {
		super();
	}

	/**
	 * Instantiates a new member type.
	 *
	 * @param emailId
	 *            the email id
	 * @param name
	 *            the name
	 */

	public MemberType(String emailId, String name) {
		super();
		this.emailId = emailId;
		this.name = name;
	}

	/**
	 * Instantiates a new member type.
	 *
	 * @param student
	 *            the student
	 */
	public MemberType(Student student) {
		this.emailId = student.getEmailId();
		this.name = student.getName();
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId
	 *            the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberType other = (MemberType) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

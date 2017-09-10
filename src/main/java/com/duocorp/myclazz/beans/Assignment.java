package com.duocorp.myclazz.beans;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class Assignment.
 * 
 * @author kishorekumar.s
 */
public class Assignment {

	/** The name. */
	@NotEmpty
	@NotNull
	@NotBlank
	String name;

	/** The description. */
	String description;

	/** The date of submission. */
	@NotEmpty
	@NotBlank
	@NotNull
	String dateOfSubmission;

	/** The author. */
	String author;

	/** The author mail. */
	String authorEmailId;

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

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the date of submission.
	 *
	 * @return the date of submission
	 */
	public String getDateOfSubmission() {
		return dateOfSubmission;
	}

	/**
	 * Sets the date of submission.
	 *
	 * @param dateOfSubmission
	 *            the new date of submission
	 */
	public void setDateOfSubmission(String dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author
	 *            the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the author email id.
	 *
	 * @return the author email id
	 */
	public String getAuthorEmailId() {
		return authorEmailId;
	}

	/**
	 * Sets the author email id.
	 *
	 * @param authorMail
	 *            the new author email id
	 */
	public void setAuthorEmailId(String authorMail) {
		this.authorEmailId = authorMail;
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
		result = prime * result + ((dateOfSubmission == null) ? 0 : dateOfSubmission.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Assignment other = (Assignment) obj;
		if (dateOfSubmission == null) {
			if (other.dateOfSubmission != null)
				return false;
		} else if (!dateOfSubmission.equals(other.dateOfSubmission))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

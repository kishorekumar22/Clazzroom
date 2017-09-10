package com.duocorp.myclazz.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Student.
 */
@Document(collection = "student")
public class Student {

	/** The id. */
	@Id
	private String id;

	/** The email id. */
	@NotNull
	@NotEmpty
	@Email
	@Indexed(unique = true)
	private String emailId;

	/** The password. */
	private String password;

	/** The name. */
	@NotEmpty
	@NotNull
	@Size(min = 5, message = "Username length should be atleat 5 characters")
	private String name;

	/** The dob. */
	private String dob;

	/** The clazzName . */
	private String clazzName;

	/** The clazz role. */
	private String clazzRole;

	/** The gender. */
	@NotNull
	@NotBlank
	private String gender;

	/** The profile image. */
	private Object profileImage;

	Student() {
	}

	public Student(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Sets the dob.
	 *
	 * @param dob
	 *            the new dob
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * Gets the clazz name.
	 *
	 * @return the clazz name
	 */
	public String getClazzName() {
		return clazzName;
	}

	/**
	 * Sets the clazz name.
	 *
	 * @param clazz
	 *            the new clazz name
	 */
	public void setClazzName(String clazz) {
		this.clazzName = clazz;
	}

	/**
	 * Gets the clazz role.
	 *
	 * @return the clazz role
	 */
	public String getClazzRole() {
		return clazzRole;
	}

	/**
	 * Sets the clazz role.
	 *
	 * @param clazzRole
	 *            the new clazz role
	 */
	public void setClazzRole(String clazzRole) {
		this.clazzRole = clazzRole;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender
	 *            the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Mask password.
	 */
	public void maskPassword() {
		setPassword(null);
	}

	/**
	 * Gets the profile image.
	 *
	 * @return the profile image
	 */
	public Object getProfileImage() {
		return profileImage;
	}

	/**
	 * Sets the profile image.
	 *
	 * @param profileImage
	 *            the new profile image
	 */
	public void setProfileImage(Object profileImage) {
		this.profileImage = profileImage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", emailId=" + emailId + ", password=" + password + ", name=" + name + ", dob="
				+ dob + ", clazzName=" + clazzName + ", clazzRole=" + clazzRole + ", gender=" + gender
				+ ", profileImage=" + profileImage + "]";
	}

}

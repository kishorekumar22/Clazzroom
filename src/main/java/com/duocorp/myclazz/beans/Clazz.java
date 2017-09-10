/*
 * 
 */
package com.duocorp.myclazz.beans;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Clazz.
 * 
 * @author kishorekumar.s
 */
@Document(collection = "clazz")
public class Clazz {

	/** The id. */
	@Id
	private String id;
	/** The clazz name. */
	@NotNull
	@NotEmpty
	@NotBlank
	String clazzName;

	/** The Members. */
	List<MemberType> members;

	/** The admin. */
	MemberType admin;

	/** The assignments. */
	List<Assignment> assignments = new ArrayList<>();

	/**
	 * Instantiates a new clazz.
	 *
	 * @param clazzName
	 *            the clazz name
	 */
	public Clazz(String clazzName) {
		this.clazzName = clazzName;
	}

	/**
	 * Instantiates a new clazz.
	 */
	Clazz() {
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
	 * @param clazzName
	 *            the new clazz name
	 */
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	/**
	 * Gets the admin.
	 *
	 * @return the admin
	 */
	public MemberType getAdmin() {
		return admin;
	}

	/**
	 * Sets the admin.
	 *
	 * @param admin
	 *            the new admin
	 */
	public void setAdmin(MemberType admin) {
		this.admin = admin;
	}

	/**
	 * Gets the members.
	 *
	 * @return the members
	 */
	public List<MemberType> getMembers() {
		return members;
	}

	/**
	 * Sets the members.
	 *
	 * @param members
	 *            the new members
	 */
	public void setMembers(List<MemberType> members) {
		this.members = members;
	}

	/**
	 * Gets the assignments.
	 *
	 * @return the assignments
	 */
	public List<Assignment> getAssignments() {
		return assignments;
	}

	/**
	 * Sets the assignments.
	 *
	 * @param assignments
	 *            the new assignments
	 */
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

}

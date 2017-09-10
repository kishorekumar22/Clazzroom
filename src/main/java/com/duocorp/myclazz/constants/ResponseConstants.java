package com.duocorp.myclazz.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The ResponseConstants.
 * 
 * @author kishorekumar.s
 */
public enum ResponseConstants {

	/** The success. */
	API_SUCCESS("MC_0000", "Operation Success"),
	/** The err not registered. */
	NOT_REGISTERED("MC_0001", "Not a registered Email Id"),
	/** The err invalid credentials. */
	INVALID_CREDENTIALS("MC_0002", "EmailId or Password wrong"),
	/** The email id registered. */
	EMAILID_REGISTERED("MC_0003", "EmailId already registered"),
	/** The registration success. */
	USER_REGISTRATION_SUCCESS("MC_0004", "User registered successfully,Please Login"),
	/** The registration failed. */
	USER_REGISTRATION_FAILED("MC_0005", "Student registration/Signup Failed"),
	/** The invalid email id. */
	INVALID_EMAILID_UPDATE("MC_0006", "Invalid EmailId given for Update"),
	/** The user not found. */
	USER_EMAIL_NOTFOUND("MC_0007", "User/EmailId not found"),
	/** The update student success. */
	UPDATE_STUDENT_SUCCESS("MC_0008", "Student Profile updated successfully"),
	/** The update student failed. */
	UPDATE_STUDENT_FAILED("MC_0009", "Student Profile updation Failed"),
	/** The login attempt failed. */
	LOGIN_ATTEMPT_FAILED("MC_0010", "UserName/Password Wrong!"),
	/** The login attempt success. */
	LOGIN_ATTEMPT_SUCCESS("MC_0011", "Login success"),
	/** The clazz creation success. */
	CLAZZ_CREATION_SUCCESS("MC_0012", "Clazzcreated successfully"),
	/** The clazz creation failed. */
	CLAZZ_CREATION_FAILED("MC_0013", "Clazz creation failed"),
	/** The clazz already exists. */
	CLAZZ_ALREADY_EXISTS("MC_0014", "ClazzName already exists"),
	/** The user already assigned. */
	USER_ALREADY_ASSIGNED("MC_0015", "Given user is already assigned to a clazz"),
	/** The user added to clazz. */
	USER_ADDED_TOCLAZZ("MC_0016", "Given user is added sucessfully to the clazz"),
	/** The user assignment failed. */
	USER_ASSIGNMENT_FAILED("MC_0018", "User assignment to the clazz Failed"),
	/** The clazz user removed. */
	CLAZZ_USER_REMOVED("MC_0019", "Given user removed sucessfully from the clazz"),
	/** The user assignment failed. */
	USER_REMOVAL_FAILED("MC_0020", "User removal from the clazz Failed"),
	/** The clazz not exists. */
	CLAZZ_NOT_EXISTS("MC_0021", "Given class doesnot exists"),
	/** The error fetching clazz. */
	ERROR_FETCHING_CLAZZ("MC_0022", "Error while fetching clazz info"),
	/** The clazz info fetched. */
	STUDENT_DELETED("MC_0023", "Student deleted successfully"),
	/** The clazz info fetched. */
	CLAZZ_INFO_FETCHED("MC_0024", "Clazz info fetched successfully"),
	/** The assignment added. */
	ASSIGNMENT_ADDED("MC_0025", "Assignment added to clazz successfully"),
	/** The assignment added. */
	ASSIGNMENT_ADDITION_FAILED("MC_0026", "Error while creating Assignment"),
	/** The assignment deleted. */
	ASSIGNMENT_DELETED("MC_0027", "Assignment deleted to clazz successfully"),
	/** The assignment deletion failed. */
	ASSIGNMENT_DELETION_FAILED("MC_0028", "Error while deleting Assignment"),
	/** The assignment exists addition. */
	ASSIGNMENT_EXISTS_ADDITION("MC_0029", "Assignment already exists"),
	/** The student fetching error. */
	STUDENT_FETCHING_ERROR("MC_0030", "Profile cannot be viewed"),
	/** The profile fetching error. */
	PROFILE_FETCHING_ERROR("MC_0031", "Error in fetching the Student Profile"),
	/** The document upload failed. */
	DOCUMENT_UPLOAD_FAILED("MC_0032", "Error in uploading the Document"),
	/** The document upload success. */
	DOCUMENT_UPLOAD_SUCCESS("MC_0033", "Document Uploaded Sucesfully"),
	/** The upload document retrieved. */
	UPLOAD_DOCUMENT_RETRIEVED("MC_0034", "Uploads Retrieved Sucesfully"),
	/** The upload document deleted. */
	UPLOAD_DOCUMENT_DELETED("MC_0035", "Uploaded Document Deleted Sucessfully"),
	/** The upload delete failed. */
	UPLOAD_DELETE_FAILED("MC_0036", "Error in deleteing document"),
	/** The download failed. */
	DOWNLOAD_FAILED("MC_0037", "Document download Failed"),
	/** The download success. */
	DOWNLOAD_SUCCESS("MC_0038", "Document download Success"),
	/** The posts retrieved successfully. */
	POSTS_RETRIEVED_SUCCESSFULLY("MC_0039", "Discussions retrieved Successfully"),
	/** The cannot retrieve posts. */
	CANNOT_RETRIEVE_POSTS("MC_0040", "Discussion retrieval failed"),
	/** The post creation failed. */
	POST_CREATION_FAILED("MC_0041", "Discussion creation failed"),
	/** The post delete failed. */
	POST_DELETE_FAILED("MC_0042", "Discussion cannot be deleted"),
	/** The post not found. */
	POST_NOT_FOUND("MC_0043", "Discussion Post not found"),
	/** The add comment failed. */
	ADD_COMMENT_FAILED("MC_0044", "Comment cannot be added"),
	/** The delete comment failed. */
	DELETE_COMMENT_FAILED("MC_0045", "Comment cannot be deleted");

	/** The code. */
	private String code;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new error constants.
	 *
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	ResponseConstants(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	@JsonProperty
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the new code
	 */
	void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	@JsonProperty
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	void setMessage(String message) {
		this.message = message;
	}
}

package com.duocorp.myclazz.beans;

import com.mongodb.DBObject;

/**
 * The Class UploadMetaData.
 */
public class UploadMetaData {

	/** The file name. */
	String fileName;

	/** The file id. */
	String fileId;

	/** The clazz name. */
	String clazzName;

	/** The author. */
	String author;

	/** The author emial id. */
	String authorEmailId;

	/** The file type. */
	String fileType;

	/**
	 * Instantiates a new upload meta data.
	 *
	 * @param dbOject
	 *            the db oject
	 */
	public UploadMetaData(DBObject dbOject) {
		this.author = (String) dbOject.get("author");
		this.authorEmailId = (String) dbOject.get("authorEmailId");
		this.clazzName = (String) dbOject.get("clazzName");
		this.fileName = (String) dbOject.get("fileName");
		this.fileId = (String) dbOject.get("fileId");
		this.fileType = (String) dbOject.get("fileType");
	}

	/**
	 * Instantiates a new upload meta data.
	 */
	public UploadMetaData() {
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName
	 *            the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the file id.
	 *
	 * @return the file id
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * Sets the file id.
	 *
	 * @param fileId
	 *            the new file id
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
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
	 * @param authorEmailId
	 *            the new author email id
	 */
	public void setAuthorEmailId(String authorEmailId) {
		this.authorEmailId = authorEmailId;
	}

	/**
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * Sets the file type.
	 *
	 * @param fileType
	 *            the new file type
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
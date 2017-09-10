package com.duocorp.myclazz.beans;

import java.util.Date;

import com.mongodb.gridfs.GridFSDBFile;

/**
 * The Class Upload.
 */
public class Upload {

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

	/** The upload time. */
	private Date uploadTime;

	/** The db file. */
	private GridFSDBFile dbFile;

	/** The id. */
	private String id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
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
	 * Gets the file id.
	 *
	 * @return the file id
	 */
	public String getFileId() {
		return fileId;
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
	 * Gets the author emial id.
	 *
	 * @return the author emial id
	 */
	public String getAuthorEmailId() {
		return authorEmailId;
	}

	/**
	 * Sets the author emial id.
	 *
	 * @param authorEmialId
	 *            the new author emial id
	 */
	public void setAuthorEmailId(String authorEmialId) {
		this.authorEmailId = authorEmialId;
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
	 * Gets the upload time.
	 *
	 * @return the upload time
	 */
	public Date getUploadTime() {
		return uploadTime;
	}

	/**
	 * Sets the upload time.
	 *
	 * @param uploadTime
	 *            the new upload time
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * Gets the db file.
	 *
	 * @return the db file
	 */
	public GridFSDBFile getDbFile() {
		return dbFile;
	}

	/**
	 * Sets the db file.
	 *
	 * @param dbFile
	 *            the new db file
	 */
	public void setDbFile(GridFSDBFile dbFile) {
		this.dbFile = dbFile;
	}

}

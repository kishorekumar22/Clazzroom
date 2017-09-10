package com.duocorp.myclazz.beans;

/**
 * The Class Document.
 */
public class Document {

	/** The meta data. */
	UploadMetaData metaData;

	/** The file. */
	byte[] file;

	/**
	 * Gets the meta data.
	 *
	 * @return the meta data
	 */
	public UploadMetaData getMetaData() {
		return metaData;
	}

	/**
	 * Sets the meta data.
	 *
	 * @param metaData
	 *            the new meta data
	 */
	public void setMetaData(UploadMetaData metaData) {
		this.metaData = metaData;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file
	 *            the new file
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}
}

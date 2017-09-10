package com.duocorp.myclazz.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Document;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.beans.UploadMetaData;

public interface UploadService {

	/**
	 * Save document.
	 *
	 * @param student
	 *            the student
	 * @param file
	 *            the file
	 * @return the api response
	 */
	ApiResponse<List<UploadMetaData>> saveDocument(Student student, MultipartFile file);

	/**
	 * Gets the upload documents.
	 *
	 * @param clazz
	 *            the clazz
	 * @return the upload documents
	 */
	public ApiResponse<List<UploadMetaData>> getUploadedDocuments(String clazz);

	/**
	 * Download document.
	 *
	 * @param upload
	 *            the upload
	 * @return the api response
	 */
	ApiResponse<Document> downloadDocument(UploadMetaData upload);

	/**
	 * Delete document.
	 *
	 * @param upload the upload
	 * @return the api response
	 */
	ApiResponse<List<UploadMetaData>> deleteDocument(UploadMetaData upload);

}

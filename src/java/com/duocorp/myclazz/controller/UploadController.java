package com.duocorp.myclazz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Document;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.beans.UploadMetaData;
import com.duocorp.myclazz.service.StudentService;
import com.duocorp.myclazz.service.UploadService;

/**
 * The Class UploadController.
 */
@RestController
@RequestMapping("myclazzApi")
public class UploadController {

	/** The upload service. */
	@Autowired
	private UploadService uploadService;

	@Autowired
	private StudentService studentService;

	/**
	 * Upload document.
	 *
	 * @param file
	 *            the file
	 * @param student
	 *            the student
	 * @return the api response
	 */
	@RequestMapping(value = "/upload_document", method = RequestMethod.POST, headers = "content-type=multipart/*")
	private ApiResponse<List<UploadMetaData>> uploadDocument(@RequestPart("document") MultipartFile file,
			@RequestPart(value = "student") @Valid Student student) {
		return uploadService.saveDocument(student, file);
	}

	/**
	 * Gets the student info.
	 *
	 * @param student
	 *            the student
	 * @return the student info
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/get_uploaded_documents", method = RequestMethod.POST)
	private ApiResponse<List<UploadMetaData>> getDocuments(UsernamePasswordAuthenticationToken principal,
			@RequestBody Student student) {
		if (student.getClazzName() != null && student.getClazzName().length() > 0)
			return uploadService.getUploadedDocuments(student.getClazzName());
		else {
			String emailId = "";
			if (principal != null && principal.getName() != null) {
				emailId = principal.getName();
			}
			return uploadService.getUploadedDocuments(studentService.getLoggedInStudentClazz(emailId));
		}
	}

	/**
	 * Gets the student info.
	 *
	 * @param student
	 *            the student
	 * @return the student info
	 */
	@RequestMapping(value = "/download_uploaded_document", method = RequestMethod.POST)
	private ApiResponse<Document> downloadDocument(@RequestBody UploadMetaData upload) {
		return uploadService.downloadDocument(upload);
	}

	/**
	 * Delete document.
	 *
	 * @param upload
	 *            the upload
	 * @return the api response
	 */
	@RequestMapping(value = "/delete_uploaded_document", method = RequestMethod.POST)
	private ApiResponse<List<UploadMetaData>> deleteDocument(@RequestBody UploadMetaData upload) {
		return uploadService.deleteDocument(upload);
	}

}

package com.duocorp.myclazz.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Document;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.beans.UploadMetaData;
import com.duocorp.myclazz.constants.ResponseConstants;
import com.duocorp.myclazz.exceptions.ClazzDataException;
import com.duocorp.myclazz.service.UploadService;
import com.duocorp.myclazz.util.FileUtils;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * The Class UploadServiceImpl.
 */
@Service
public class UploadServiceImpl implements UploadService {

	/** The Constant METADATA_FILE_ID. */
	private static final String METADATA_FILE_ID = "metadata.fileId";

	/** The Constant METADATA_CLAZZ_NAME. */
	private static final String METADATA_CLAZZ_NAME = "metadata.clazzName";

	/** The logger. */
	private static final Log LOGGER = LogFactory.getLog(UploadServiceImpl.class);

	/** The template. */
	@Autowired
	GridFsTemplate template;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.UploadService#saveDocument(com.duocorp.myclazz
	 * .beans.Student, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public ApiResponse<List<UploadMetaData>> saveDocument(Student student, MultipartFile file) {
		UploadMetaData document = new UploadMetaData();
		ApiResponse<List<UploadMetaData>> response = new ApiResponse<>();
		if (student.getEmailId() != null && student.getName() != null) {
			String clazzName = student.getClazzName();
			document.setFileName(file.getOriginalFilename());
			document.setClazzName(clazzName);
			document.setAuthorEmailId(student.getEmailId());
			document.setAuthor(student.getName());
			document.setFileType(file.getContentType());
			document.setFileId(new StringBuilder().append(student.getClazzName()).append("_")
					.append(student.getEmailId()).append("_").append(System.currentTimeMillis()).toString());
			try {
				template.store(FileUtils.multipartFileToInputStream(file), document);
			} catch (IOException ex) {
				LOGGER.error("Document uplopad failed", ex);
				response.setResponse(ResponseConstants.DOCUMENT_UPLOAD_FAILED);
				return response;
			}
			List<UploadMetaData> uploadList = new ArrayList<>();
			List<GridFSDBFile> uploads;
			if (clazzName != null) {
				uploads = template.find(new Query(Criteria.where(METADATA_CLAZZ_NAME).is(clazzName)));
				for (GridFSDBFile gridFsFile : uploads) {
					uploadList.add(new UploadMetaData(gridFsFile.getMetaData()));
				}
			}
			response.setDataAndResponse(uploadList, ResponseConstants.UPLOAD_DOCUMENT_RETRIEVED);
			return response;
		}
		response.setResponse(ResponseConstants.DOCUMENT_UPLOAD_FAILED);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.UploadService#getUploadDocuments(com.duocorp
	 * .myclazz.beans.Clazz)
	 */
	public ApiResponse<List<UploadMetaData>> getUploadedDocuments(String clazzname) {
		ApiResponse<List<UploadMetaData>> response = new ApiResponse<>();
		List<UploadMetaData> uploadList = new ArrayList<>();
		List<GridFSDBFile> uploads;
		if (clazzname != null) {
			uploads = template.find(new Query(Criteria.where(METADATA_CLAZZ_NAME).is(clazzname)));
			for (GridFSDBFile gridFsFile : uploads) {
				uploadList.add(new UploadMetaData(gridFsFile.getMetaData()));
			}
		}
		response.setDataAndResponse(uploadList, ResponseConstants.UPLOAD_DOCUMENT_RETRIEVED);
		return response;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.UploadService#downloadDocument(com.duocorp
	 * .myclazz.beans.UploadMetaData)
	 */
	@Override
	public ApiResponse<Document> downloadDocument(UploadMetaData upload) {
		ApiResponse<Document> response = new ApiResponse<>();
		try {
			if (upload.getFileId() == null) {
				throw new ClazzDataException("FileId not found for download");
			}
			GridFSDBFile file = template.findOne(new Query(Criteria.where(METADATA_FILE_ID).is(upload.getFileId())));
			if (file == null) {
				throw new ClazzDataException("File not found for download");
			}
			return getDownloadableDocument(response, file);
		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.DOWNLOAD_FAILED);
			return response;
		}
	}

	/**
	 * Gets the downloadable document.
	 *
	 * @param response
	 *            the response
	 * @param file
	 *            the file
	 * @return the downloadable document
	 * @throws ClazzDataException
	 *             the clazz data exception
	 */
	private ApiResponse<Document> getDownloadableDocument(ApiResponse<Document> response, GridFSDBFile file)
			throws ClazzDataException {
		Document downloadDocument = new Document();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			file.writeTo(baos);
			downloadDocument.setFile(baos.toByteArray());
			downloadDocument.setMetaData(new UploadMetaData(file.getMetaData()));
			response.setDataAndResponse(downloadDocument, ResponseConstants.DOWNLOAD_SUCCESS);
			return response;
		} catch (IOException ex) {
			LOGGER.error("Error in converting GridFS file to ByteArray for Download", ex);
			throw new ClazzDataException("File download failed while converting to byte array " + ex.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.UploadService#deleteDocument(com.duocorp.
	 * myclazz.beans.UploadMetaData)
	 */
	@Override
	public ApiResponse<List<UploadMetaData>> deleteDocument(UploadMetaData upload) {
		ApiResponse<List<UploadMetaData>> response = new ApiResponse<>();
		String fileId = upload.getFileId();
		String clazzName = upload.getClazzName();
		if (fileId == null) {
			response.setResponse(ResponseConstants.UPLOAD_DELETE_FAILED);
			return response;
		}
		template.delete(new Query(Criteria.where(METADATA_FILE_ID).is(fileId)));
		List<UploadMetaData> uploadList = new ArrayList<>();
		List<GridFSDBFile> uploads;
		if (clazzName != null) {
			uploads = template.find(new Query(Criteria.where(METADATA_CLAZZ_NAME).is(clazzName)));
			for (GridFSDBFile gridFsFile : uploads) {
				uploadList.add(new UploadMetaData(gridFsFile.getMetaData()));
			}
		}
		response.setDataAndResponse(uploadList, ResponseConstants.UPLOAD_DOCUMENT_DELETED);
		return response;
	}
}

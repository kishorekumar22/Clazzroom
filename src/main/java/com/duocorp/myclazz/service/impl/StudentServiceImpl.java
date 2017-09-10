package com.duocorp.myclazz.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.constants.ResponseConstants;
import com.duocorp.myclazz.exceptions.StudentDataException;
import com.duocorp.myclazz.repo.StudentRepository;
import com.duocorp.myclazz.service.ClazzService;
import com.duocorp.myclazz.service.StudentService;

/**
 * The Class StudentServiceImpl.
 */
@Service
public class StudentServiceImpl implements StudentService {

	/** The logger. */
	private static final Log LOGGER = LogFactory.getLog(StudentServiceImpl.class);

	/** The student repository. */
	@Autowired
	private StudentRepository studentRepository;

	/** The Clazz repository. */
	@Autowired
	private ClazzService clazzService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.duocorp.myclazz.service.StudentService#listStudents()
	 */
	@Override
	public ApiResponse<List<Student>> listStudents() {
		ApiResponse<List<Student>> response = new ApiResponse<>();
		response.setData(studentRepository.findAll());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.StudentService#deleteStudent(java.lang.String
	 * )
	 */
	@Override
	public ApiResponse<List<Student>> deleteStudent(String email) {
		ApiResponse<List<Student>> response = new ApiResponse<>();
		if (studentRepository.findByEmailId(email) != null) {
			studentRepository.delete(studentRepository.findByEmailId(email));
			response.setResponse(ResponseConstants.STUDENT_DELETED);
		} else
			response.setResponse(ResponseConstants.USER_EMAIL_NOTFOUND);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.StudentService#getStudentbyEmail(java.lang
	 * .String)
	 */
	@Override
	public ApiResponse<Student> getStudentByEmail(Student student) {
		ApiResponse<Student> response = new ApiResponse<>();
		try {
			if (student.getEmailId() != null) {
				Student studentFromDb = studentRepository.findByRegEmailId(student.getEmailId());
				if (studentFromDb != null) {
					response.setResponse(ResponseConstants.API_SUCCESS);
					response.setData(studentFromDb);
					return response;
				}
			}
			throw new StudentDataException();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.setResponse(ResponseConstants.USER_EMAIL_NOTFOUND);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.StudentService#getStudentByEmailAndPassword
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public ApiResponse<Student> getStudentByEmailAndPassword(Student student) {
		ApiResponse<Student> response = new ApiResponse<>();
		try {
			if (student.getEmailId() != null && student.getPassword() != null) {
				Student studentFromDb = studentRepository.findByRegEmailId(student.getEmailId());
				if (studentFromDb == null) {
					response.setResponse(ResponseConstants.NOT_REGISTERED);
					return response;
				}
				studentFromDb = studentRepository.findByEmailIdAndPassword(student.getEmailId(), student.getPassword());
				if (studentFromDb != null) {

					response.setDataAndResponse(studentFromDb, ResponseConstants.LOGIN_ATTEMPT_SUCCESS);
					return response;
				}
			}
			response.setDataAndResponse(student, ResponseConstants.INVALID_CREDENTIALS);
			return response;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.setResponse(ResponseConstants.LOGIN_ATTEMPT_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.duocorp.myclazz.service.StudentService#createStudent(com.duocorp
	 * .clazzrum.beans.Student)
	 */
	@Override
	public ApiResponse<Student> createStudent(Student student, MultipartFile file) {
		ApiResponse<Student> response = new ApiResponse<>();
		try {
			if (file != null)
				student.setProfileImage(file.getBytes());
		} catch (Exception ex) {
			LOGGER.error("Error in converting image to Byte[]", ex);
			response.setResponse(ResponseConstants.UPDATE_STUDENT_FAILED);
			return response;
		}
		try {
			if (student.getEmailId() != null) {
				if (studentRepository.findByRegEmailId(student.getEmailId()) != null) {
					response.setResponse(ResponseConstants.EMAILID_REGISTERED);
					return response;
				}
				Student registeredStudent = studentRepository.save(student);
				registeredStudent.maskPassword();
				response.setDataAndResponse(registeredStudent, ResponseConstants.USER_REGISTRATION_SUCCESS);
				return response;
			} else {
				throw new StudentDataException();
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.setResponse(ResponseConstants.USER_REGISTRATION_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.StudentService#saveStudent(com.duocorp.myclazz
	 * .beans.Student)
	 */
	@Override
	public ApiResponse<Student> saveStudent(Student student, MultipartFile file) {
		ApiResponse<Student> response = new ApiResponse<>();
		try {
			checkAndSetProfileImage(student, file);
			boolean nameChanged = false;
			if (student.getEmailId() != null) {
				Student studentFromDb = studentRepository.findByEmailId(student.getEmailId());
				if (studentFromDb == null) {
					response.setResponse(ResponseConstants.USER_EMAIL_NOTFOUND);
					return response;
				}
				student.setId(studentFromDb.getId());
				if (studentFromDb.getName() != null && !studentFromDb.getName().equals(student.getName()))
					nameChanged = true;
				Student registeredStudent = studentRepository.save(student);
				registeredStudent.maskPassword();
				if (nameChanged)
					clazzService.updateStudentReference(registeredStudent);
				response.setDataAndResponse(registeredStudent, ResponseConstants.UPDATE_STUDENT_SUCCESS);
				return response;
			} else {
				throw new StudentDataException();
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.setResponse(ResponseConstants.UPDATE_STUDENT_FAILED);
			return response;
		}
	}

	/**
	 * Check and set profile image.
	 *
	 * @param student
	 *            the student
	 * @param file
	 *            the file
	 * @throws StudentDataException
	 *             the student data exception
	 */
	private void checkAndSetProfileImage(Student student, MultipartFile file) throws StudentDataException {
		try {
			if (file != null)
				student.setProfileImage(file.getBytes());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new StudentDataException("Error in converting image to Byte[]");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.StudentService#getStudentInfo(com.duocorp
	 * .myclazz.beans.Student)
	 */
	@Override
	public ApiResponse<Student> getStudentInfo(Student student) {

		ApiResponse<Student> response = new ApiResponse<>();
		try {
			if (student.getEmailId() != null) {
				Student studentFromDb = studentRepository.findByRegEmailId(student.getEmailId());
				if (studentFromDb != null
						&& (studentFromDb.getClazzName() == null || !studentFromDb.getClazzName().equalsIgnoreCase(
								student.getClazzName()))) {
					response.setResponse(ResponseConstants.STUDENT_FETCHING_ERROR);
					return response;
				}
				response.setResponse(ResponseConstants.API_SUCCESS);
				response.setData(studentFromDb);
				return response;
			}
			throw new StudentDataException();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.setResponse(ResponseConstants.PROFILE_FETCHING_ERROR);
			return response;
		}
	}

	@Override
	public ApiResponse<Student> getLoggedInStudent(Student student) {

		ApiResponse<Student> response = new ApiResponse<>();
		try {
			if (student.getEmailId() != null) {
				Student studentFromDb = studentRepository.findByRegEmailId(student.getEmailId());
				if (studentFromDb == null) {
					response.setResponse(ResponseConstants.PROFILE_FETCHING_ERROR);
					return response;
				}
				response.setResponse(ResponseConstants.API_SUCCESS);
				response.setData(studentFromDb);
				return response;
			}
			throw new StudentDataException();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			response.setResponse(ResponseConstants.PROFILE_FETCHING_ERROR);
			return response;
		}

	}

	@Override
	public String getLoggedInStudentClazz(String emailId) {
		Student studentFromDb = studentRepository.findByRegEmailId(emailId);
		if (studentFromDb.getClazzName() != null) {
			return studentFromDb.getClazzName();
		}
		return null;
	}
}

package com.duocorp.myclazz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Assignment;
import com.duocorp.myclazz.beans.Clazz;
import com.duocorp.myclazz.beans.MemberType;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.constants.ResponseConstants;
import com.duocorp.myclazz.exceptions.ClazzDataException;
import com.duocorp.myclazz.repo.ClazzRepository;
import com.duocorp.myclazz.repo.StudentRepository;
import com.duocorp.myclazz.service.ClazzService;

/**
 * The Class ClazzServiceImpl.
 * 
 * @author kishorekumar.s
 */
@Service
public class ClazzServiceImpl implements ClazzService {

	/** The logger. */
	private static final Log LOGGER = LogFactory.getLog(ClazzServiceImpl.class);

	/** The clazz repository. */
	@Autowired
	ClazzRepository clazzRepository;

	/** The student repository. */
	@Autowired
	StudentRepository studentRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.ClazzService#createClazz(java.lang.String)
	 */
	@Override
	public ApiResponse<Student> createClazz(Student student) {
		ApiResponse<Student> response = new ApiResponse<>();
		try {
			if (student.getClazzName() != null && student.getEmailId() != null) {
				Clazz clazzFromDb = clazzRepository.findByClazzName(student.getClazzName());
				Student studentFromDb = studentRepository.findByEmailId(student.getEmailId());
				if (clazzFromDb == null && studentFromDb != null) {
					Clazz newClazz = new Clazz(student.getClazzName());
					List<MemberType> membersList = new ArrayList<>();
					MemberType admin = new MemberType(studentFromDb);
					membersList.add(admin);
					newClazz.setMembers(membersList);
					newClazz.setAdmin(admin);
					newClazz = clazzRepository.save(newClazz);
					studentFromDb.setClazzName(newClazz.getClazzName());
					studentFromDb.setClazzRole("Admin");
					studentFromDb = studentRepository.save(studentFromDb);
					student.maskPassword();
					response.setDataAndResponse(studentFromDb, ResponseConstants.CLAZZ_CREATION_SUCCESS);
					return response;
				}
				response.setResponse(ResponseConstants.CLAZZ_ALREADY_EXISTS);
				return response;
			}
			throw new ClazzDataException();

		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.CLAZZ_CREATION_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.ClazzService#addMember(com.duocorp.myclazz
	 * .beans.Student)
	 */
	@Override
	public ApiResponse<Clazz> addMember(Student student) {
		ApiResponse<Clazz> response = new ApiResponse<>();
		try {
			Student studentFromDb = studentRepository.findByEmailId(student.getEmailId());
			if (studentFromDb == null) {
				response.setResponse(ResponseConstants.USER_EMAIL_NOTFOUND);
				return response;
			}
			if (studentFromDb.getClazzName() != null || studentFromDb.getClazzRole() != null) {
				response.setResponse(ResponseConstants.USER_ALREADY_ASSIGNED);
				return response;
			}
			if (student.getClazzName() != null) {
				Clazz clazzFromDb = clazzRepository.findByClazzName(student.getClazzName());
				if (clazzFromDb != null) {
					List<MemberType> members = clazzFromDb.getMembers();
					members.add(new MemberType(studentFromDb));
					clazzFromDb = clazzRepository.save(clazzFromDb);
					studentFromDb.setClazzName(clazzFromDb.getClazzName());
					studentFromDb.setClazzRole("Member");
					studentRepository.save(studentFromDb);
					response.setDataAndResponse(clazzFromDb, ResponseConstants.USER_ADDED_TOCLAZZ);
					return response;
				} else {
					response.setResponse(ResponseConstants.CLAZZ_NOT_EXISTS);
					return response;
				}
			}
			throw new ClazzDataException();

		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.USER_ADDED_TOCLAZZ);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.ClazzService#deleteMember(com.duocorp.myclazz
	 * .beans.Student)
	 */
	@Override
	public ApiResponse<Clazz> deleteMember(Student student) {
		ApiResponse<Clazz> response = new ApiResponse<>();
		try {
			Student studentFromDb = studentRepository.findByEmailId(student.getEmailId());
			if (studentFromDb == null) {
				response.setResponse(ResponseConstants.USER_EMAIL_NOTFOUND);
				return response;
			}
			Clazz clazzFromDb = clazzRepository.findByClazzName(student.getClazzName());
			if (clazzFromDb == null) {
				response.setResponse(ResponseConstants.CLAZZ_NOT_EXISTS);
				return response;
			}
			if (studentFromDb.getClazzName() != null && studentFromDb.getClazzRole() != null) {
				studentFromDb.setClazzName(null);
				studentFromDb.setClazzRole(null);
				studentRepository.save(studentFromDb);
				List<MemberType> members = clazzFromDb.getMembers();
				members.remove(new MemberType(studentFromDb));
				clazzFromDb.setMembers(members);
				clazzFromDb = clazzRepository.save(clazzFromDb);
				response.setDataAndResponse(clazzFromDb, ResponseConstants.CLAZZ_USER_REMOVED);
				return response;
			}
			throw new ClazzDataException();

		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.USER_REMOVAL_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.ClazzService#getClazzInfo(com.duocorp.myclazz
	 * .beans.Student)
	 */
	@Override
	public ApiResponse<Clazz> getClazzInfo(String className) {
		ApiResponse<Clazz> response = new ApiResponse<>();
		try {
			if (className == null || className.isEmpty()) {
				throw new ClazzDataException("Invalid class given");
			}
			Clazz clazzFromDb = clazzRepository.findByClazzName(className);
			if (clazzFromDb == null) {
				response.setResponse(ResponseConstants.CLAZZ_NOT_EXISTS);
				return response;
			}
			response.setDataAndResponse(clazzFromDb, ResponseConstants.CLAZZ_INFO_FETCHED);
			return response;

		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.ERROR_FETCHING_CLAZZ);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.ClazzService#addAssignment(com.duocorp.myclazz
	 * .beans.Clazz)
	 */
	@Override
	public ApiResponse<Clazz> addAssignment(Clazz clazz) {
		ApiResponse<Clazz> response = new ApiResponse<>();
		try {
			Clazz clazzFromDb = clazzRepository.findByClazzName(clazz.getClazzName());
			if (clazzFromDb == null) {
				throw new ClazzDataException("Given clazz name does not exists");
			}
			if (clazzFromDb.getAssignments() != null && clazz.getAssignments() != null
					&& clazzFromDb.getAssignments().contains(clazz.getAssignments().get(0))) {
				response.setResponse(ResponseConstants.ASSIGNMENT_EXISTS_ADDITION);
				return response;
			}
			if (clazz.getAssignments() != null && !clazz.getAssignments().isEmpty()) {
				clazzFromDb.getAssignments().add(clazz.getAssignments().get(0));
				clazzRepository.save(clazzFromDb);
				response.setData(clazzRepository.findByClazzName(clazzFromDb.getClazzName()));
				response.setResponse(ResponseConstants.ASSIGNMENT_ADDED);
				return response;
			}
			throw new ClazzDataException("Error in Assigment addition");
		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.ASSIGNMENT_ADDITION_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.ClazzService#deleteAssignment(com.duocorp
	 * .myclazz.beans.Clazz)
	 */
	@Override
	public ApiResponse<Clazz> deleteAssignment(Clazz clazz) {

		ApiResponse<Clazz> response = new ApiResponse<>();
		try {
			Clazz clazzFromDb = clazzRepository.findByClazzName(clazz.getClazzName());
			if (clazzFromDb == null) {
				throw new ClazzDataException("Given clazz does not exists");
			}
			if (clazz.getAssignments() != null && !clazz.getAssignments().isEmpty()) {
				clazzFromDb.getAssignments().remove(clazz.getAssignments().get(0));
				clazzRepository.save(clazzFromDb);
				response.setData(clazzRepository.findByClazzName(clazzFromDb.getClazzName()));
				response.setResponse(ResponseConstants.ASSIGNMENT_DELETED);
				return response;
			}
			throw new ClazzDataException("Assigment not available for deletion");
		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.ASSIGNMENT_DELETION_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.ClazzService#updateStudentReference(com.duocorp
	 * .myclazz.beans.Student)
	 */
	@Override
	public void updateStudentReference(Student registeredStudent) {
		String clazzName = registeredStudent.getClazzName();
		if (clazzName != null && !clazzName.isEmpty()) {
			Clazz clazzFromDb = clazzRepository.findByClazzName(clazzName);
			for (MemberType member : clazzFromDb.getMembers()) {
				if (member.getEmailId().equals(registeredStudent.getEmailId())) {
					member.setName(registeredStudent.getName());
				}
			}
			for (Assignment assignment : clazzFromDb.getAssignments()) {
				if (assignment.getAuthorEmailId().equals(registeredStudent.getEmailId())) {
					assignment.setAuthor(registeredStudent.getName());
				}
			}
			if (clazzFromDb.getAdmin().getEmailId() != null
					&& clazzFromDb.getAdmin().getEmailId().equals(registeredStudent.getEmailId())) {
				clazzFromDb.getAdmin().setName(registeredStudent.getName());
			}
			clazzRepository.save(clazzFromDb);
		}
	}
}

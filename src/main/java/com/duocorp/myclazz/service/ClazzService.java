package com.duocorp.myclazz.service;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Clazz;
import com.duocorp.myclazz.beans.Student;

/**
 * The Interface ClazzService.
 */
public interface ClazzService {

	/**
	 * Creates the clazz.
	 *
	 * @param student
	 *            the student
	 * @return the api response
	 */
	ApiResponse<Student> createClazz(Student student);

	/**
	 * Adds the member.
	 *
	 * @param student
	 *            the student
	 * @return the api response
	 */
	ApiResponse<Clazz> addMember(Student student);

	/**
	 * Delete member.
	 *
	 * @param student
	 *            the student
	 * @return the api response
	 */
	ApiResponse<Clazz> deleteMember(Student student);

	/**
	 * Gets the clazz info.
	 *
	 * @param student
	 *            the student
	 * @return the clazz info
	 */
	ApiResponse<Clazz> getClazzInfo(String classname);

	/**
	 * Adds the assignment.
	 *
	 * @param clazz
	 *            the clazz
	 * @return the api response
	 */
	ApiResponse<Clazz> addAssignment(Clazz clazz);

	/**
	 * Delete assignment.
	 *
	 * @param clazz
	 *            the clazz
	 * @return the api response
	 */
	ApiResponse<Clazz> deleteAssignment(Clazz clazz);

	/**
	 * Update student reference.
	 *
	 * @param registeredStudent
	 *            the registered student
	 */
	void updateStudentReference(Student registeredStudent);
}

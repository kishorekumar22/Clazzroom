package com.duocorp.myclazz.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Student;

/**
 * The Interface StudentService.
 * 
 * @author kishorekumar.s
 *
 */
public interface StudentService {

	/**
	 * List students from DB.
	 *
	 * @return the list
	 */
	public ApiResponse<List<Student>> listStudents();

	/**
	 * Gets the student by email.
	 *
	 * @param student
	 *            the email
	 * @return the student by email
	 */
	public ApiResponse<Student> getStudentByEmail(Student student);

	/**
	 * Gets the student by email and password. getStudentByEmailAndPassword
	 * 
	 * @param student
	 *            the email
	 * @return the student by email and password
	 */
	public ApiResponse<Student> getStudentByEmailAndPassword(Student student);

	/**
	 * Creates the student.
	 *
	 * @param student
	 *            the student
	 * @param file
	 * @return the student
	 */
	public ApiResponse<Student> createStudent(Student student, MultipartFile file);

	/**
	 * Save student.
	 *
	 * @param student
	 *            the student
	 * @param file
	 *            the file
	 * @return the api response
	 */
	public ApiResponse<Student> saveStudent(Student student, MultipartFile file);

	/**
	 * Delete student.
	 *
	 * @param email
	 *            the email
	 * @return the api response
	 */
	ApiResponse<List<Student>> deleteStudent(String email);

	/**
	 * Gets the student info.
	 *
	 * @param student
	 *            the student
	 * @return the student info
	 */
	public ApiResponse<Student> getStudentInfo(Student student);

	/**
	 * Gets the logged in student.
	 *
	 * @param student
	 *            the student
	 * @return the logged in student
	 */
	ApiResponse<Student> getLoggedInStudent(Student student);

	/**
	 * Gets the logged in student clazz.
	 *
	 * @param student
	 *            the student
	 * @return the logged in student clazz
	 */

	String getLoggedInStudentClazz(String emailId);
}

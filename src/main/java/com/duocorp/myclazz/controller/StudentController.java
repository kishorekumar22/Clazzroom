package com.duocorp.myclazz.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.service.StudentService;

/**
 * The Class StudentController.
 * 
 * @author kishorekumar.s
 */
@RestController
@RequestMapping("myclazzApi")
public class StudentController {

	/** The logger. */
	static final Logger logger = Logger.getLogger(StudentController.class);

	/** The student service. */
	@Autowired
	StudentService studentService;

	/**
	 * Developer API only for testing - List the students from DB.
	 *
	 * @return the list
	 */

	@RequestMapping("/dev/list_students")
	@ResponseBody
	private ApiResponse<List<Student>> listStudents() {
		return studentService.listStudents();
	}

	/**
	 * Developer API only for testing - Delete student.
	 *
	 * @param emailId
	 *            the email id
	 * @return the api response
	 */
	@RequestMapping("/dev/delete_student/{emailId}/")
	@ResponseBody
	private ApiResponse<List<Student>> deleteStudent(@PathVariable String emailId) {
		return studentService.deleteStudent(emailId);
	}

	/**
	 * To be replaced with Spring security.
	 *
	 * @param authentication
	 *            the authentication
	 * @return the student
	 */
	@RequestMapping(value = "/do_login", method = RequestMethod.GET, consumes = "application/x-www-form-urlencoded")
	private ApiResponse<Student> login(Authentication authentication) {
		String name = authentication.getName();
		return studentService.getStudentInfo(new Student(name));
	}

	/**
	 * Gets the student by email.
	 *
	 * @param student
	 *            the student
	 * @return the student by email
	 */
	@RequestMapping(value = "/get_student", method = RequestMethod.POST)
	private ApiResponse<Student> getStudentByEmail(@RequestBody Student student) {
		return studentService.getStudentByEmail(student);
	}

	/**
	 * Gets the user and session id.
	 *
	 * @param principal
	 *            the principal
	 * @param httpServletRequest
	 *            the http servlet request
	 * @return the user and session id
	 * 
	 */
	@RequestMapping(value = "/get_loggedin_stu", method = RequestMethod.GET)
	public ApiResponse<Student> getUserAndSessionId(UsernamePasswordAuthenticationToken principal) {
		if (principal != null && principal.getName() != null) {
			String name = principal.getName();
			return studentService.getLoggedInStudent(new Student(name));
		}
		return null;

	}

	/**
	 * Create Student.
	 *
	 * @param file
	 *            the file
	 * @param student
	 *            the student
	 * @return the student by email
	 */
	@RequestMapping(value = "/create_student", method = RequestMethod.POST, headers = "content-type=multipart/*")
	private ApiResponse<Student> createStudent(
			@RequestPart(value = "profileImage", required = false) MultipartFile file,
			@RequestPart(value = "student") @Valid Student student) {
		return studentService.createStudent(student, file);
	}

	/**
	 * Update Student.
	 *
	 * @param file
	 *            the file
	 * @param student
	 *            the student
	 * @return the student by email
	 */
	@RequestMapping(value = "/update_student", method = RequestMethod.POST, headers = "content-type=multipart/*")
	private ApiResponse<Student> updateStudent(
			@RequestPart(value = "profileImage", required = false) MultipartFile file,
			@RequestPart(value = "student") @Valid Student student) {
		return studentService.saveStudent(student, file);
	}

	/**
	 * Gets the student by email.
	 *
	 * @param student
	 *            the student
	 * @return the student by email
	 */
	@RequestMapping(value = "/get_student_info", method = RequestMethod.POST)
	private ApiResponse<Student> getStudentInfo(@RequestBody Student student) {
		return studentService.getStudentInfo(student);
	}
}

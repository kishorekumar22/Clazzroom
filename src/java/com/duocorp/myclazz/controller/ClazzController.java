package com.duocorp.myclazz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Clazz;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.service.ClazzService;
import com.duocorp.myclazz.service.StudentService;

/**
 * The Class ClazzController.
 */
@RestController
@RequestMapping("myclazzApi")
public class ClazzController {

	/** The clazz service. */
	@Autowired
	ClazzService clazzService;

	/** The student service. */
	@Autowired
	private StudentService studentService;

	/**
	 * Creates the clazz.
	 *
	 * @param student
	 *            the student
	 * @return the api response
	 */
	@RequestMapping(value = "/create_clazz", method = RequestMethod.POST)
	public ApiResponse<Student> createClazz(@RequestBody Student student) {
		return clazzService.createClazz(student);
	}

	/**
	 * Adds the clazz mamber.
	 *
	 * @param student
	 *            the student
	 * @return the api response
	 */
	@RequestMapping(value = "/add_clazz_member", method = RequestMethod.POST)
	public ApiResponse<Clazz> addClazzMember(@RequestBody Student student) {
		return clazzService.addMember(student);
	}

	/**
	 * Deletes the clazz mamber.
	 *
	 * @param student
	 *            the student
	 * @return the api response
	 */
	@RequestMapping(value = "/remove_clazz_member", method = RequestMethod.POST)
	public ApiResponse<Clazz> deleteClazzMember(@RequestBody Student student) {
		return clazzService.deleteMember(student);
	}

	/**
	 * Deletes the clazz mamber.
	 *
	 * @param clazzname
	 *            the clazzname
	 * @return the api response
	 */
	@RequestMapping(value = "/get_clazz_info", method = RequestMethod.POST)
	public ApiResponse<Clazz> getClazzInfo(UsernamePasswordAuthenticationToken principal, @RequestBody Student clazzname) {
		if (clazzname.getClazzName() != null && clazzname.getClazzName().length() > 0)
			return clazzService.getClazzInfo(clazzname.getClazzName());
		else {
			String emailId = "";
			if (principal != null && principal.getName() != null) {
				emailId = principal.getName();
			}
			return clazzService.getClazzInfo(studentService.getLoggedInStudentClazz(emailId));
		}
	}
}

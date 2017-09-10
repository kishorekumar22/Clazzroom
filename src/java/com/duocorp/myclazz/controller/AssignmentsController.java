package com.duocorp.myclazz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Clazz;
import com.duocorp.myclazz.service.ClazzService;
import com.duocorp.myclazz.service.StudentService;

/**
 * The Class AssignmentsController.
 * 
 * @author kishorekumar.s
 */
@RestController
@RequestMapping("myclazzApi")
public class AssignmentsController {

	/** The class service. */
	@Autowired
	private ClazzService classService;

	/** The student service. */
	@Autowired
	private StudentService studentService;

	/**
	 * Gets the asignments.
	 *
	 * @param principal
	 *            the principal
	 * @param clazz
	 *            the clazz
	 * @return the asignments
	 */
	@RequestMapping(value = "/get_clazz_assignments", method = RequestMethod.POST)
	ApiResponse<Clazz> getAsignments(UsernamePasswordAuthenticationToken principal, @RequestBody Clazz clazz) {
		if (clazz.getClazzName() != null && clazz.getClazzName().length() > 0)
			return classService.getClazzInfo(clazz.getClazzName());
		else {

			String emailId = "";
			if (principal != null && principal.getName() != null) {
				emailId = principal.getName();
			}
			return classService.getClazzInfo(studentService.getLoggedInStudentClazz(emailId));

		}
	}

	/**
	 * Creates the assignment.
	 *
	 * @param clazz
	 *            the clazz
	 * @return the api response
	 */
	@RequestMapping(value = "/add_clazz_assignment", method = RequestMethod.POST)
	ApiResponse<Clazz> createAssignment(@RequestBody @Valid Clazz clazz) {
		return classService.addAssignment(clazz);
	}

	/**
	 * Delete assignment.
	 *
	 * @param clazz
	 *            the clazz
	 * @return the api response
	 */
	@RequestMapping(value = "/remove_clazz_assignment", method = RequestMethod.POST)
	ApiResponse<Clazz> deleteAssignment(@RequestBody Clazz clazz) {
		return classService.deleteAssignment(clazz);
	}

}

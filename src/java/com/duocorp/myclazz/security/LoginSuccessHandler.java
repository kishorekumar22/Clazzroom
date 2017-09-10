package com.duocorp.myclazz.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class SuccessHandler.
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	/** The mapper. */
	private ObjectMapper mapper;

	/** The student service. */
	@Autowired
	private StudentService studentService;

	/**
	 * Instantiates a new success handler.
	 *
	 * @param messageConverter
	 *            the message converter
	 */
	@Autowired
	LoginSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
		this.mapper = messageConverter.getObjectMapper();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * SavedRequestAwareAuthenticationSuccessHandler
	 * #onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.addHeader("Cache-Control", "max-age=60");
		ApiResponse<Student> apiResponse = studentService.getStudentByEmail(new Student(authentication.getName()));
		PrintWriter writer = response.getWriter();
		mapper.writeValue(writer, apiResponse);
		writer.flush();
	}
}
package com.duocorp.myclazz.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.constants.ResponseConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class LoginFailureHandler.
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	/** The mapper. */
	private ObjectMapper mapper;

	/**
	 * Login success handler.
	 *
	 * @param messageConverter
	 *            the message converter
	 */
	@Autowired
	LoginFailureHandler(MappingJackson2HttpMessageConverter messageConverter) {
		this.mapper = messageConverter.getObjectMapper();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationFailureHandler
	 * #onAuthenticationFailure(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);
		ApiResponse<Student> apiResponse = new ApiResponse<>();
		apiResponse.setResponse(ResponseConstants.LOGIN_ATTEMPT_FAILED);
		PrintWriter writer = response.getWriter();
		mapper.writeValue(writer, apiResponse);
		writer.flush();

	}
}
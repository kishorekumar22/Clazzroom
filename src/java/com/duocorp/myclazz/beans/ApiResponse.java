package com.duocorp.myclazz.beans;

import com.duocorp.myclazz.constants.ResponseConstants;

/**
 * The Class ClazzrumApiResponse.
 *
 * @param <T>
 *            the generic type
 */
public class ApiResponse<T> {

	/** The response. */
	ResponseInfo response;

	/** The data. */
	T data;

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public ResponseInfo getResponse() {
		return response;
	}

	/**
	 * Sets the response.
	 *
	 * @param response
	 *            the new response
	 */
	public void setResponse(ResponseConstants response) {
		this.response = new ResponseInfo(response);
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the new data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Sets the data and response.
	 *
	 * @param data
	 *            the data
	 * @param responseInfo
	 *            the response info
	 */
	public void setDataAndResponse(T data, ResponseConstants responseInfo) {
		this.data = data;
		this.response = new ResponseInfo(responseInfo);
	}
}

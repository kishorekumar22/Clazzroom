package com.duocorp.myclazz.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * The Class FileUtils.
 */
public class FileUtils {

	/**
	 * Multipart to file.
	 *
	 * @param multipart
	 *            the multipart
	 * @return the file
	 * @throws IllegalStateException
	 *             the illegal state exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream multipartFileToInputStream(MultipartFile file) throws IOException {
		byte[] byteArr = file.getBytes();
		return new ByteArrayInputStream(byteArr);
	}
}

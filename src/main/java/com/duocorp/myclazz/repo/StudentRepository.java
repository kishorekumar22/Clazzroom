package com.duocorp.myclazz.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.duocorp.myclazz.beans.Student;

/**
 * The Interface StudentRepository.
 */
public interface StudentRepository extends MongoRepository<Student, String> {

	/**
	 * Find by email id.
	 *
	 * @param email
	 *            the email
	 * @return the student
	 */
	Student findByEmailId(String email);

	/**
	 * Find by email id.
	 *
	 * @param email
	 *            the email
	 * @return the student
	 */
	@Query(value = "{'emailId' : ?0 }", fields = "{ _id : 0 , password : 0 }")
	Student findByRegEmailId(String email);

	/**
	 * Find by email id and password.
	 *
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @return the student"
	 */
	@Query(value = "{'emailId' : ?0 ,'password' : ?1 }", fields = "{ _id : 0 , password : 0}")
	Student findByEmailIdAndPassword(String email, String password);

}

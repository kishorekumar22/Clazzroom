package com.duocorp.myclazz.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.duocorp.myclazz.beans.Clazz;

/**
 * The Interface ClazzRepository.
 */
public interface ClazzRepository extends MongoRepository<Clazz, String> {

	/**
	 * Find by clazz name.
	 *
	 * @param name
	 *            the name
	 * @return the clazz
	 */
	Clazz findByClazzName(String name);
}

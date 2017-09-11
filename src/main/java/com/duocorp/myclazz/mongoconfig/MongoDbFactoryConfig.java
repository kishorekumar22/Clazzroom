package com.duocorp.myclazz.mongoconfig;

import org.springframework.data.mongodb.MongoDbFactory;

/**
 * The Interface MongoDbFactoryConfig.
 */
@FunctionalInterface
public interface MongoDbFactoryConfig {

	/**
	 * Mongo db factory.
	 *
	 * @return the mongo db factory
	 * @throws Exception
	 *             the exception
	 */
	public abstract MongoDbFactory mongoDbFactory() throws Exception;
}

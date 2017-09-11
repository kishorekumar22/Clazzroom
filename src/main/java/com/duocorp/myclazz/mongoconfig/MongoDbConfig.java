package com.duocorp.myclazz.mongoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * The Class MongoDbConfig.
 */
@Configuration
public class MongoDbConfig {

	/** The mongo db factory config. */
	@Autowired
	private MongoDbFactoryConfig mongoDbFactoryConfig;

	/**
	 * Mongo template.
	 *
	 * @return the mongo template
	 * @throws Exception
	 *             the exception
	 */
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactoryConfig.mongoDbFactory());
	}

}

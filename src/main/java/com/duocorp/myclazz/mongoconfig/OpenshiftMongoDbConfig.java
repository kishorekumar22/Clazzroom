package com.duocorp.myclazz.mongoconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

/**
 * The Class OpenshiftMongoDbConfig.
 */
@Configuration
public class OpenshiftMongoDbConfig implements MongoDbFactoryConfig {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.mongoconfig.MongoDbFactoryConfig#mongoDbFactory()
	 */
	@Override
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClientURI(System.getenv("CLAZZRUM_MONGODB_URI")));

	}
}

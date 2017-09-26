package com.duocorp.myclazz.mongoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

/**
 * The Class OpenshiftMongoDbConfig.
 */
@Configuration
public class MlabsMongoDbConfig implements MongoDbFactoryConfig {

	@Value("${mlab.mongo.uri}")
	private String mongoURI;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.mongoconfig.MongoDbFactoryConfig#mongoDbFactory()
	 */
	@Override
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClientURI(mongoURI));

	}
}

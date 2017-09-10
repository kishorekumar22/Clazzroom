package com.duocorp.myclazz.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.duocorp.myclazz.beans.Post;

/**
 * The Interface PostsRepository.
 */
public interface PostsRepository extends MongoRepository<Post, String> {

	/**
	 * Find by clazzname.
	 * 
	 * @param clazzName
	 *
	 * @return the list
	 */
	List<Post> findByClazzName(String clazzName);

	/**
	 * Findby clazz name and content and author email id.
	 *
	 * @param clazzName
	 *            the clazz name
	 * @param content
	 *            the content
	 * @param authorEmailId
	 *            the author email id
	 * @return the post
	 */
	Post findByClazzNameAndContentAndAuthorEmailId(String clazzName, String content, String authorEmailId);

}

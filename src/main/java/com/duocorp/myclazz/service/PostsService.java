package com.duocorp.myclazz.service;

import java.util.List;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Comment;
import com.duocorp.myclazz.beans.Post;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostsService.
 */
public interface PostsService {

	/**
	 * Gets the posts.
	 *
	 * @param clazzName
	 *            the clazz name
	 * @param i
	 * @return the posts
	 */
	ApiResponse<List<Post>> getPosts(String clazzName, int index);

	/**
	 * Creates the post.
	 *
	 * @param post
	 *            the post
	 * @return the api response
	 */
	ApiResponse<List<Post>> createPost(Post post);

	/**
	 * Creates the post comment.
	 *
	 * @param postid
	 *            the postid
	 * @param comment
	 *            the comment
	 * @return the api response
	 */
	ApiResponse<List<Post>> createPostComment(String postid, Comment comment);

	/**
	 * Delete post.
	 *
	 * @param post
	 *            the post
	 * @return the api response
	 */
	ApiResponse<List<Post>> deletePost(Post post);

	/**
	 * Delete post comment.
	 *
	 * @param postid
	 *            the postid
	 * @param comment
	 *            the comment
	 * @return the api response
	 */
	ApiResponse<List<Post>> deletePostComment(String postid, Comment comment);

}

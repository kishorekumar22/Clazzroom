package com.duocorp.myclazz.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Comment;
import com.duocorp.myclazz.beans.Post;
import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.service.PostsService;
import com.duocorp.myclazz.service.StudentService;

/**
 * The Class DiscussionController.
 */
@RestController
@RequestMapping("myclazzApi")
public class DiscussionController {

	/** The logger. */
	static final Logger logger = Logger.getLogger(DiscussionController.class);

	/** The student service. */
	@Autowired
	StudentService studentService;

	/** The posts service. */
	@Autowired
	PostsService postsService;

	/**
	 * Gets the clazz posts.
	 *
	 * @param principal
	 *            the principal
	 * @param student
	 *            the student
	 * @return the clazz posts
	 */
	@RequestMapping(value = "/get_clazz_posts/{pageIndex}", method = RequestMethod.POST)
	public ApiResponse<List<Post>> getClazzPosts(UsernamePasswordAuthenticationToken principal,
			@RequestBody Student student, @PathVariable String pageIndex) {
		if (student.getClazzName() != null && student.getClazzName().length() > 0)
			return postsService.getPosts(student.getClazzName(), Integer.parseInt(pageIndex));
		else {
			String emailId = "";
			if (principal != null && principal.getName() != null) {
				emailId = principal.getName();
			}
			return postsService.getPosts(studentService.getLoggedInStudentClazz(emailId), 0);
		}
	}

	/**
	 * Adds the clazz posts.
	 *
	 * @param post
	 *            the post
	 * @return the api response
	 */
	@RequestMapping(value = "/add_post", method = RequestMethod.POST)
	public ApiResponse<List<Post>> addClazzPost(@RequestBody Post post) {
		return postsService.createPost(post);

	}

	/**
	 * Delete clazz post.
	 *
	 * @param post
	 *            the post
	 * @return the api response
	 */
	@RequestMapping(value = "/delete_post", method = RequestMethod.POST)
	public ApiResponse<List<Post>> deleteClazzPost(@RequestBody Post post) {
		return postsService.deletePost(post);

	}

	/**
	 * Adds the comment for posts.
	 *
	 * @param postid
	 *            the postid
	 * @param comment
	 *            the comment
	 * @return the api response
	 */
	@RequestMapping(value = "/add_post_comment/{postid}", method = RequestMethod.POST)
	public ApiResponse<List<Post>> addCommentForPosts(@PathVariable String postid, @RequestBody Comment comment) {
		return postsService.createPostComment(postid, comment);

	}

	/**
	 * Adds the comment for posts.
	 *
	 * @param postid
	 *            the postid
	 * @param comment
	 *            the comment
	 * @return the api response
	 */
	@RequestMapping(value = "/delete_post_comment/{postid}", method = RequestMethod.POST)
	public ApiResponse<List<Post>> deleteComments(@PathVariable String postid, @RequestBody Comment comment) {
		return postsService.deletePostComment(postid, comment);

	}
}

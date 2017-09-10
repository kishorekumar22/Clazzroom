package com.duocorp.myclazz.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duocorp.myclazz.beans.ApiResponse;
import com.duocorp.myclazz.beans.Comment;
import com.duocorp.myclazz.beans.Post;
import com.duocorp.myclazz.constants.ResponseConstants;
import com.duocorp.myclazz.exceptions.ClazzDataException;
import com.duocorp.myclazz.repo.PostsRepository;
import com.duocorp.myclazz.service.PostsService;

/**
 * The Class PostsServiceImpl.
 */
@Service
public class PostsServiceImpl implements PostsService {

	/** The posts repository. */
	@Autowired
	PostsRepository postsRepository;

	/** The logger. */
	static final Logger LOGGER = Logger.getLogger(PostsServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.duocorp.myclazz.service.PostsService#getPosts(java.lang.String,
	 * int)
	 */
	@Override
	public ApiResponse<List<Post>> getPosts(String clazzName, int index) {
		ApiResponse<List<Post>> response = new ApiResponse<>();
		if (clazzName != null && clazzName.length() > 0) {
			response.setDataAndResponse(postsRepository.findByClazzName(clazzName),
					ResponseConstants.POSTS_RETRIEVED_SUCCESSFULLY);
			return response;
		}
		response.setResponse(ResponseConstants.CANNOT_RETRIEVE_POSTS);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.PostsService#createPost(com.duocorp.myclazz
	 * .beans.Post)
	 */
	@Override
	public ApiResponse<List<Post>> createPost(Post post) {
		ApiResponse<List<Post>> response = new ApiResponse<>();
		try {

			if (post != null && post.getContent() != null && post.getClazzName() != null) {
				post.setTime(System.currentTimeMillis());
				postsRepository.save(post);
				response.setDataAndResponse(postsRepository.findAll(), ResponseConstants.POSTS_RETRIEVED_SUCCESSFULLY);
				return response;
			}
			response.setResponse(ResponseConstants.CANNOT_RETRIEVE_POSTS);
			return response;

		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.POST_CREATION_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.PostsService#createPostComment(java.lang.
	 * String, com.duocorp.myclazz.beans.Comment)
	 */
	@Override
	public ApiResponse<List<Post>> createPostComment(String postid, Comment comment) {
		ApiResponse<List<Post>> response = new ApiResponse<>();
		try {
			Post postFromDb = postsRepository.findOne(postid);
			if (postFromDb == null) {
				response.setResponse(ResponseConstants.POST_NOT_FOUND);
				return response;
			}
			comment.setTime(System.currentTimeMillis());
			postFromDb.getComments().add(comment);
			postsRepository.save(postFromDb);
			response.setDataAndResponse(postsRepository.findAll(), ResponseConstants.POSTS_RETRIEVED_SUCCESSFULLY);
			return response;
		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.ADD_COMMENT_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.PostsService#deletePost(com.duocorp.myclazz
	 * .beans.Post)
	 */
	@Override
	public ApiResponse<List<Post>> deletePost(Post post) {
		ApiResponse<List<Post>> response = new ApiResponse<>();
		try {

			if (post != null && post.getContent() != null && post.getClazzName() != null) {
				Post postFromDB = postsRepository.findByClazzNameAndContentAndAuthorEmailId(post.getClazzName(),
						post.getContent(), post.getAuthorEmailId());
				if (!post.getAuthorEmailId().equalsIgnoreCase(postFromDB.getAuthorEmailId())) {
					throw new ClazzDataException();
				}
				postsRepository.delete(postFromDB);
				response.setDataAndResponse(postsRepository.findAll(), ResponseConstants.POSTS_RETRIEVED_SUCCESSFULLY);
				return response;
			}
			response.setResponse(ResponseConstants.CANNOT_RETRIEVE_POSTS);
			return response;

		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.POST_DELETE_FAILED);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.duocorp.myclazz.service.PostsService#deletePostComment(java.lang.
	 * String, com.duocorp.myclazz.beans.Comment)
	 */
	@Override
	public ApiResponse<List<Post>> deletePostComment(String postid, Comment comment) {
		ApiResponse<List<Post>> response = new ApiResponse<>();
		try {
			Post postFromDb = postsRepository.findOne(postid);
			if (postFromDb == null) {
				response.setResponse(ResponseConstants.POST_NOT_FOUND);
				return response;
			}
			Comment commentFromDb = null;
			for (Comment c : postFromDb.getComments()) {
				if (c.equals(comment)){
					commentFromDb = c;
					break;
				}
			}
			if (commentFromDb != null && commentFromDb.getAuthorEmailId() != null && comment.getAuthorEmailId() != null
					&& comment.getAuthorEmailId().equalsIgnoreCase(commentFromDb.getAuthorEmailId())) {
				postFromDb.getComments().remove(comment);
				postsRepository.save(postFromDb);
				response.setDataAndResponse(postsRepository.findAll(), ResponseConstants.POSTS_RETRIEVED_SUCCESSFULLY);
				return response;
			}
			throw new ClazzDataException();
		} catch (Exception ex) {
			LOGGER.error(ex);
			response.setResponse(ResponseConstants.DELETE_COMMENT_FAILED);
			return response;
		}
	}
}

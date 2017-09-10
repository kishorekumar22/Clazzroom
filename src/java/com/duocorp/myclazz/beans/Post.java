package com.duocorp.myclazz.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Post.
 */
@Document(collection = "posts")
public class Post {

	/** The Id. */
	private String id;
	/** The content. */
	private String content;

	/** The author. */
	private String author;

	/** The author emial id. */
	private String authorEmailId;

	/** The time. */
	private long time;

	/** The clazzName . */
	private String clazzName;

	/** The comments. */
	List<Comment> comments = new ArrayList<>();

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content
	 *            the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author
	 *            the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the author email id.
	 *
	 * @return the author email id
	 */
	public String getAuthorEmailId() {
		return authorEmailId;
	}

	/**
	 * Sets the author email id.
	 *
	 * @param authorEmailId
	 *            the new author email id
	 */
	public void setAuthorEmailId(String authorEmailId) {
		this.authorEmailId = authorEmailId;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the time.
	 *
	 * @param l
	 *            the new time
	 */
	public void setTime(long l) {
		this.time = l;
	}

	/**
	 * Gets the clazz name.
	 *
	 * @return the clazz name
	 */
	public String getClazzName() {
		return clazzName;
	}

	/**
	 * Sets the clazz name.
	 *
	 * @param clazzName
	 *            the new clazz name
	 */
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments
	 *            the new comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}

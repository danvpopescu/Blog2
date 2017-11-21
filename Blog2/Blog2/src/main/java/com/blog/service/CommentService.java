package com.blog.service;

import java.util.List;
import com.blog.model.Comment;

public interface CommentService {

	/*
	 * find a comment by CommentId
	 */
	Comment findCommentByCommentId(int commentId);

	/*
	 * find all the comments for the article that has the title parameter as a title
	 */
	List<Comment> findCommentsByTitle(String title);

	/*
	 * find all the comments that contains the content parameter in the content
	 */
	List<Comment> findCommentsByContent(String content);

	/*
	 * find all the comments for the article that has the title parameter as a title
	 * and contains the content parameter in the content
	 */
	List<Comment> findCommentsByContentTitle(String content, String title);

	/*
	 * find all comments
	 */
	List<Comment> findCommentsAll();

	/*
	 * find an enabled comment by CommentId
	 */
	Comment findCommentEnabledByCommentId(int commentId);

	/*
	 * find all the enabled comments for the article that has the title parameter as a title
	 */
	List<Comment> findCommentsEnabledByTitle(String title);

	/*
	 * find all the enabled comments that contains the content parameter in the content
	 */
	List<Comment> findCommentsEnabledByContent(String content);

	/*
	 * find all the enabled comments for the article that has the title parameter as a title
	 * and contains the content parameter in the content
	 */
	List<Comment> findCommentsEnabledByContentTitle(String content, String title);

	/*
	 * find all enabled comments
	 */
	List<Comment> findCommentsEnabledAll();

	/*
	 * add a comment in the database
	 */
	int addComment(Comment comment);

	/*
	 * update a comment in the database
	 */
	int updateComment(Comment comment);

	/*
	 * delete a comment in the database
	 */
	int deleteComment(Comment comment);

}

package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.blog.dao.CommentDao;
import com.blog.model.Article;
import com.blog.model.Comment;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public Comment findCommentByCommentId(int commentId) {
		Comment comment = null;
		try {
			comment = commentDao.findCommentByCommentId(commentId);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comment;
	}

	@Override
	public List<Comment> findCommentsByTitle(String title) {
		List<Comment> comments = null;
		try {
			comments = commentDao.findCommentsByTitle(title);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	@Override
	public List<Comment> findCommentsByContent(String content) {
		List<Comment> comments = null;
		try {
			comments = commentDao.findCommentsByContent(content);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	@Override
	public List<Comment> findCommentsByContentTitle(String content, String title) {
		List<Comment> comments = null;
		try {
			comments = commentDao.findCommentsByContentTitle(content, title);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	@Override
	public List<Comment> findCommentsAll() {
		List<Comment> comments = null;
		try {
			comments = commentDao.findCommentsAll();			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	@Override
	public Comment findCommentEnabledByCommentId(int commentId) {
		Comment comment = null;
		try {
			comment = commentDao.findCommentEnabledByCommentId(commentId);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comment;
	}

	@Override
	public List<Comment> findCommentsEnabledByTitle(String title) {
		List<Comment> comments = null;
		try {
			comments = commentDao.findCommentsEnabledByTitle(title);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	@Override
	public List<Comment> findCommentsEnabledByContent(String content) {
		List<Comment> comments = null;
		try {
			comments = commentDao.findCommentsEnabledByContent(content);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	@Override
	public List<Comment> findCommentsEnabledByContentTitle(String content, String title) {
		List<Comment> comments = null;
		try {
			comments = commentDao.findCommentsEnabledByContentTitle(content, title);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	@Override
	public List<Comment> findCommentsEnabledAll() {
		List<Comment> comments = null;
		try {
			comments = commentDao.findCommentsEnabledAll();			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	@Override
	public int addComment(Comment comment) {
		int ret = 0; 
		try {
			commentDao.addComment(comment);
			if (commentDao.findCommentByCommentId(comment.getCommentId())  != null ) {
				ret = 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return ret;

		
	}

	@Override
	public int updateComment(Comment comment) {
		int ret = 0;
		try {
			ret = commentDao.updateComment(comment);	
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	
		}
		return ret;
	}

	@Override
	public int deleteComment(Comment comment) {
		int ret = 0;
		try {
			ret = commentDao.deleteComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	
		}
		return ret;
	}
}

package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.blog.dao.ArticleDao;
import com.blog.dao.CommentDao;
import com.blog.model.Article;
import com.blog.model.Comment;

@Service
@Transactional(rollbackFor={Exception.class})
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private CommentDao commentDao;

	@Override
	public Article findArticleByTitle(String title) {
		Article article = null;
		try {
			article = articleDao.findArticleByTitle(title);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return article;
	}

	@Override
	public List<Article> findArticlesByTitle(String title) {
		List<Article> articles = null;
		try {
			articles = articleDao.findArticlesByTitle(title);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return articles;
	}

	@Override
	public List<Article> findArticlesByContent(String content) {
		List<Article> articles = null;
		try {
			articles = articleDao.findArticlesByContent(content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return articles;
	}

	@Override
	public List<Article> findArticlesByContentTitle(String content, String title) {
		List<Article> articles = null;
		try {
			articles = articleDao.findArticlesByContentTitle(content, title);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return articles;
	}

	@Override
	public List<Article> findArticlesAll() {
		List<Article> articles = null;
		try {
			articles = articleDao.findArticlesAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return articles;
	}

	@Override
	public Article findArticleEnabledByTitle(String title) {
		Article article = null;
		try {
			article = articleDao.findArticleEnabledByTitle(title);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return article;
	}

	@Override
	public List<Article> findArticlesEnabledByTitle(String title) {
		List<Article> articles = null;
		try {
			articles = articleDao.findArticlesEnabledByTitle(title);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return articles;
	}

	@Override
	public List<Article> findArticlesEnabledByContent(String content) {
		List<Article> articles = null;
		try {
			articles = articleDao.findArticlesEnabledByContent(content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return articles;
	}

	@Override
	public List<Article> findArticlesEnabledByContentTitle(String content, String title) {
		List<Article> articles = null;
		try {
			articles = articleDao.findArticlesEnabledByContentTitle(content, title);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return articles;
	}

	@Override
	public List<Article> findArticlesEnabledAll() {
		List<Article> articles = null;
		try {
			articles = articleDao.findArticlesEnabledAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return articles;
	}

	@Override
	public int addArticle(Article article) {
		int ret = 0; 
		try {
			articleDao.addArticle(article);
			if (articleDao.findArticleByTitle(article.getTitle())  != null ) {
				ret = 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return ret;
	}

	@Override
	public int updateArticle(Article article) {
		int ret = 0;
		try {
			ret = articleDao.updateArticle(article);	
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	
		}
		return ret;
	}

	@Override
	public int deleteArticle(Article article) {
		int ret = 0;
		try {
			List<Comment> comments = commentDao.findCommentsByTitle(article.getTitle());
			for(Comment c:comments) {
				commentDao.deleteComment(c);
			}
			ret = articleDao.deleteArticle(article);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	
		}
		return ret;
	}

}

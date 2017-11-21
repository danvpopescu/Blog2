package com.blog.service;

import java.util.List;
import com.blog.model.Article;

public interface ArticleService {

	/*
	 * find an article by title
	 */
	Article findArticleByTitle(String title);
	
	/*
	 * find all the articles that contains the String parameter in the title
	 */
	List<Article> findArticlesByTitle(String title);
	
	/*
	 * find all the articles that contains the String parameter in the title
	 */
	List<Article> findArticlesByContent(String content);
	
	/*
	 * find all the articles that contains the String "title" parameter in the title or the "content" parameter in the content
	 */
	List<Article> findArticlesByContentTitle(String title, String content);
	
	/*
	 * find all the articles from the database
	 */
	List<Article> findArticlesAll();

	/*
	 * find an enabled article by title
	 */
	Article findArticleEnabledByTitle(String title);
	
	/*
	 * find all the enabled articles that contains the String parameter in the title
	 */
	List<Article> findArticlesEnabledByTitle(String title);
	
	/*
	 * find all the enabled articles that contains the String parameter in the title
	 */
	List<Article> findArticlesEnabledByContent(String content);
	
	/*
	 * find all the enabled articles that contains the String "title" parameter in the title or the "content" parameter in the content
	 */
	List<Article> findArticlesEnabledByContentTitle(String title, String content);
	
	/*
	 * find all the enabled articles from the database
	 */
	List<Article> findArticlesEnabledAll();
	
	/*
	 * add an article in the database
	 */
	int addArticle(Article article);
	
	/*
	 * update an article in the database
	 */
	int updateArticle(Article article);
	
	/*
	 * delete an article
	 */
	int deleteArticle(Article article);
	
}

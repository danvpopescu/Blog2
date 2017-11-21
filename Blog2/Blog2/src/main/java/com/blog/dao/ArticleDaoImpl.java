package com.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.blog.model.Article;

@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	@Override
	public Article findArticleByTitle(String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		Article article = em.find(Article.class, title);
		em.getTransaction().commit();
		em.close();
		return article;
	}

	@Override
	public List<Article> findArticlesByTitle(String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root).where(builder.like(root.<String>get("title"), "%"+ title + "%"));
		criteria.orderBy(builder.asc(root.get("title")));
		Query query = em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Article> articles = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return articles;
	}

	@Override
	public List<Article> findArticlesByContent(String content) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root).where(builder.like(root.<String>get("content"), "%" + content + "%"));
		criteria.orderBy(builder.asc(root.get("title")));
		Query query = em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Article> articles = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return articles;
	}

	@Override
	public List<Article> findArticlesByContentTitle(String content, String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		Predicate p;
		if (content != null && content !="" && title != null && title != "") {
			Predicate p1 = builder.like(root.<String>get("content"), "%"+ content + "%");
			Predicate p2 = builder.like(root.<String>get("title"), "%" + title + "%");
			p = builder.or(p1, p2);
		} else if (title != null && title != "") {
			p = builder.like(root.<String>get("title"), "%" + title + "%");
		} else {
			p = builder.like(root.<String>get("content"), "%" + content + "%");
		}
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("title")));
		Query query = em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Article> articles = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return articles;
	}

	@Override
	public List<Article> findArticlesAll() {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		criteria.orderBy(builder.asc(root.get("title")));
		Query query = em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Article> articles = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return articles;
	}

	@Override
	public Article findArticleEnabledByTitle(String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		Predicate p1 = builder.equal(root.get("title"), title);
		Predicate p2 = builder.equal(root.get("enabled"), 1);
		Predicate p = builder.and(p1, p2);
		criteria.where(p);
		Query query = em.createQuery(criteria);
		Article article = (Article) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return article;
	}

	@Override
	public List<Article> findArticlesEnabledByTitle(String title) {

		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		Predicate p1 = builder.like(root.<String>get("title"), "%" + title + "%");
		Predicate p2 = builder.equal(root.get("enabled"), 1);
		Predicate p = builder.and(p1, p2);
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("title")));
		Query query = em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Article> articles = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return articles;

	}

	@Override
	public List<Article> findArticlesEnabledByContent(String content) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		Predicate p1 = builder.like(root.<String>get("content"), "%" + content + "%");
		Predicate p2 = builder.equal(root.get("enabled"), 1);
		Predicate p = builder.and(p1, p2);
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("title")));
		Query query = em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Article> articles = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return articles;
	}

	@Override
	public List<Article> findArticlesEnabledByContentTitle(String content, String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		Predicate p;
		if (content != null && content !="" && title != null && title != "") {
			Predicate p1 = builder.like(root.<String>get("content"), "%"+ content + "%");
			Predicate p2 = builder.like(root.<String>get("title"), "%" + title + "%");
			p = builder.or(p1, p2);
		} else if (title != null && title != "") {
			p = builder.like(root.<String>get("title"), "%" + title + "%");
		} else {
			p = builder.like(root.<String>get("content"), "%" + content + "%");
		}
		Predicate p3 = builder.equal(root.get("enabled"), 1);
		p = builder.and(p, p3);
		
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("title")));
		Query query = em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Article> articles = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return articles;
	}

	@Override
	public List<Article> findArticlesEnabledAll() {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		Predicate p = builder.equal(root.get("enabled"), 1);
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("title")));
		Query query = em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Article> articles = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return articles;
	}

	@Override
	public void addArticle(Article article) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		em.persist(article);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public int updateArticle(Article article) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<Article> update = builder.createCriteriaUpdate(Article.class);
		Root<Article> root = update.from(Article.class);
		update.set(root.<String>get("content"), article.getContent());
		update.set(root.get("enabled"), article.getEnabled());
		update.set(root.get("postedDate"), article.getPostedDate());
		Predicate condition = builder.equal(root.<String>get("title"), article.getTitle());
		update.where(condition);
		int result = em.createQuery(update).executeUpdate();
		em.getTransaction().commit();
		em.close();
		return result;
	}

	@Override
	public int deleteArticle(Article article) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<Article> delete = builder.createCriteriaDelete(Article.class);
		Root<Article> root = delete.from(Article.class);
		delete.where(builder.equal(root.get("title"), article.getTitle()));
		int result = em.createQuery(delete).executeUpdate();
		em.getTransaction().commit();
		em.close();
		return result;
	}

}

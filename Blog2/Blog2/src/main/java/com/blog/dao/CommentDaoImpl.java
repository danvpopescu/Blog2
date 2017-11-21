package com.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import com.blog.model.Article;
import com.blog.model.Comment;


@Repository("commentDao")
public class CommentDaoImpl implements CommentDao{

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	@Override
	public Comment findCommentByCommentId(int commentId) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		Comment comment = em.find(Comment.class, commentId);
		em.getTransaction().commit();
		em.close();
		return comment;
	}

	@Override
	public List<Comment> findCommentsByTitle(String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root).where(builder.like(root.<String>get("title"), "%" + title + "%"));
		criteria.orderBy(builder.asc(root.get("postedDate")));
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Comment> comments = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return comments;
	}

	@Override
	public List<Comment> findCommentsByContent(String content) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root).where(builder.like(root.<String>get("content"), "%" + content + "%"));
		criteria.orderBy(builder.asc(root.get("postedDate")));
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Comment> comments = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return comments;
	}

	@Override
	public List<Comment> findCommentsByContentTitle(String content, String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);  
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root);
		criteria.where(builder.or(builder.like(root.<String>get("content"), "%" + content + "%"), builder.like(root.<String>get("title"), "%" + title + "%")));
		criteria.orderBy(builder.asc(root.get("postedDate")));
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Comment> comments = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return comments;
	}

	@Override
	public List<Comment> findCommentsAll(){
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class); 
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root);
		criteria.orderBy(builder.asc(root.get("postedDate")));
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Comment> comments = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return comments;
	}

	@Override
	public Comment findCommentEnabledByCommentId(int commentId) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root);
		Predicate p1 = builder.equal(root.get("comment_id"), commentId);
		Predicate p2 = builder.equal(root.get("enabled"), 1);
		Predicate p = builder.and(p1,p2);
		criteria.where(p);
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		Comment comment = (Comment)query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return comment;
	}

	@Override
	public List<Comment> findCommentsEnabledByTitle(String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root);
		Predicate p1 = builder.like(root.<String>get("title"), "%" + title + "%");
		Predicate p2 = builder.equal(root.get("enabled"), 1);
		Predicate p = builder.and(p1,p2);
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("postedDate")));
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Comment> comments = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return comments;
	}

	@Override
	public List<Comment> findCommentsEnabledByContent(String content) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root);
		Predicate p1 = builder.like(root.<String>get("content"), "%" + content + "%");
		Predicate p2 = builder.equal(root.get("enabled"), 1);
		Predicate p = builder.and(p1,p2);
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("postedDate")));
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Comment> comments = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return comments;
	}

	@Override
	public List<Comment> findCommentsEnabledByContentTitle(String content, String title) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);  
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root);
		Predicate p1 = builder.like(root.<String>get("content"), "%" + content + "%");
		Predicate p2 = builder.like(root.<String>get("title"), "%" + title + "%");
		Predicate p3 = builder.equal(root.get("enabled"), 1);
		Predicate p =  builder.and(builder.or(p1,p2), p3);
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("postedDate")));
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Comment> comments = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return comments;
	}

	@Override
	public List<Comment> findCommentsEnabledAll(){
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class); 
		Root<Comment> root = criteria.from(Comment.class);
		criteria.select(root);
		Predicate p = builder.equal(root.get("enabled"), 1);
		criteria.where(p);
		criteria.orderBy(builder.asc(root.get("postedDate")));
		Query query =  em.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Comment> comments = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return comments;
	}

	@Override
	public void addComment(Comment comment) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public int updateComment(Comment comment) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<Comment> update = builder.createCriteriaUpdate(Comment.class);
		Root<Comment> root = update.from(Comment.class);
		update.set(root.<String>get("content"), comment.getContent());
		update.set(root.get("enabled"), comment.getEnabled());
		update.set(root.get("postedDate"), comment.getPostedDate());
		Predicate condition = builder.equal(root.<String>get("commentId"), comment.getCommentId());
		update.where(condition);
		int result = em.createQuery(update).executeUpdate();
		em.getTransaction().commit();
		em.close();
		return result;
	}
	
	@Override
	public int deleteComment(Comment comment) {
		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<Comment> delete = builder.createCriteriaDelete(Comment.class);
		Root<Comment> root = delete.from(Comment.class);
		delete.where(builder.equal(root.get("commentId"), comment.getCommentId()));
		int result = em.createQuery(delete).executeUpdate();
		em.getTransaction().commit();
		em.close();
		return result;
	}
	
}

package com.blog.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author dvpo222
 *
 */
@Entity
@Table(name="articles")
public class Article {

	/*
	 * The title of the article
	 */
	@Id
    @Column(name="title", columnDefinition="VARCHAR(200)", unique=true, nullable=false, length = 200)
    private String title;

	/*
	 * The content of the article
	 */
    @Column(name="content", columnDefinition="TEXT", unique=true, nullable=false, length = 10000)
    @Lob
    private String content;

	/*
	 * If the article is enabled by the admin
	 */
    @Column(name="enabled", columnDefinition="TINYINT", nullable=false, length = 1)
    private int enabled;

	/*
	 * The date when the article is posted
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="posted_date", columnDefinition="DATETIME", nullable=false)
    private Date postedDate;

	/*
	 * The user who posted the article
	 */
    @Column(name="username", columnDefinition="VARCHAR(20)", nullable=false, length = 20)
    private String username;

    public Article() {
	}

    public Article(Article article) {
		this.title = article.title;
		this.content = article.content;
		this.enabled = article.enabled;
		this.postedDate = article.postedDate;
		this.username = article.username;
	}


    public Article(String title, String content, int enabled, Date postedDate, String username) {
		this.title = title;
		this.content = content;
		this.enabled = enabled;
		this.postedDate = postedDate;
		this.username = username;
	}

    
    
    
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}

# Blog2

This document details the Blog application.
The application is conceived for posting and commenting articles.
The database contains 2 security tables(user user_roles) and 2 functional tables(articles and comments).
In the security tables are inserted 2 roles (ADMIN and USER) and 4 users(admin, user1, user2, user3) as following:
User/Pasword: admin/admin - for the ADMIN role
user1/user1 - for the USER role
user2/user2 - for the USER role
user3/user3 - for the USER role

Each user can post an article
Each user can post a comment to an article
Each user with USER profile can view all the articles or comments, once they are validated by the ADMIN profile
Each user with USER profile can search an validated article based on a part of the title or a part of the content 
Each user with USER profile can edit or delete their own articles or comments.
Each user with ADMIN profile can view any article even this one is not validated
Each user with ADMIN profile can search an validated article based on a part of the title or a part of the content 
Each user with ADMIN profile can edit or delete their own articles or comments.
Each user with ADMIN profile can validate articles or comments.


The technologies used for the project are:
Spring Framework, Spring Security, Spring MVC, JPA&Hibernate, the project is a Maven project, configured in java, without any xml file.
For deploying the project you need to:
1. create the database executing the script blog.sql
2. configure your datasource in the AppConfig.java
3. put your build plugin in the pom.xml file and the version of java you need (by default the pom.xml file is configured for java 1.8, for java 7 you have to decomment the version 1.7) 
4. for the Tomcat 7 you have to decomment the plugin for Tomcat 7 and to comment the default plugin
5. update the maven project to bring the necessary libraries
6. generate the War file using the command mvn clean install
7. deploying the war in yout servlet container (Tomcat 9.0.0.M19 has a bug and can not deploy the war using the management interface - you have to decompress the war file and copy it in the WEB-INF folder)

The thechnologies and versions used:
<java-version>1.8</java-version>
<org.springframework-version>5.0.1.RELEASE</org.springframework-version>
			<artifactId>spring-core</artifactId>
		    <artifactId>spring-context</artifactId>
			<artifactId>spring-webmvc</artifactId>
			<artifactId>spring-jdbc</artifactId>
			<artifactId>spring-orm</artifactId>
<org.springframework.security.version>5.0.0.RC1</org.springframework.security.version>
		        <artifactId>spring-security-web</artifactId>
		        <artifactId>spring-security-config</artifactId>
		        <artifactId>spring-security-taglibs</artifactId>
<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.2</version>
<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
<groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.2.12.Final</version>
<groupId>org.hibernate.javax.persistence</groupId>
            <artifactId>hibernate-jpa-2.1-api</artifactId>
            <version>1.0.0.Final</version>
<groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.2.12.Final</version>
<groupId>org.hibernate.javax.persistence</groupId>
            <artifactId>hibernate-jpa-2.1-api</artifactId>
            <version>1.0.0.Final</version>
<groupId>org.hibernate</groupId>
		   <artifactId>hibernate-entitymanager</artifactId>
		   <version>5.2.12.Final</version>
<groupId>org.hibernate</groupId>
			<artifactId>hibernate-c3p0</artifactId>
			<version>5.2.12.Final</version>
<groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>6.0.6</version>

The project is structured in 3 layers: 
- Data layer represented by DAO classes
- Business layer represented by Service classes
- Presentation layer represented by Controller classes
and is using a MySQL database

The views are:
403.jsp
editArticle.jsp
editComment.jsp
listArticles.jsp
listArticleAdmin.jsp
login.jsp
newArticle.jsp
newComment.jsp
searchAnArticle.jsp

Operations:
1.login 			: the login page is shown
2.listArticles		: the list page is charged containing the list of articles and comments based on credentials
3.searchAnArticle	: the search page is shown, the page is empty
4.searchArticle		: the list page is charged containing the result of the search: the list of articles and comments based on credentials
5.newArticle		: the newArticle page is shown, the page is empty
6.editArticle		: the editArticle page is shown, the page charges the fields, the title is read-only 
7.validateArticle	: validate the selected article, the list page is recharged containing the list of articles and comments	
8.addArticle		: add the filled out article, the list page is recharged containing the list of articles and comments based on credentials	
9.saveArticle		: save the edited article, the list page is recharged containing the list of articles and comments based on credentials	
10.deleteArticle	: delete the selected article, the list page is recharged containing the list of articles and comments based on credentials	
11.newComment		: the newComment page is shown, the page is empty
12.editComment		: the editComment page is shown, the page charges the fields, the title of the article is read-only 
13.addComment		: add the filled out comment, the list page is recharged containing the list of articles and comments based on credentials	
14.saveComment		: save the edited comment, the list page is recharged containing the list of articles and comments based on credentials	
15.validateComment	: validate the selected comment, the list page is recharged containing the list of articles and comments	
16.deleteComment	: delete the selected comment, the list page is recharged containing the list of articles and comments based on credentials	
17.logout			: log out the application, the login page is shown
18.403				: the acces denied page is shown

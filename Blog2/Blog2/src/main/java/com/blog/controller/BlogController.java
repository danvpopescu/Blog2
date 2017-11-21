package com.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.blog.model.Article;
import com.blog.model.Comment;
import com.blog.service.ArticleService;
import com.blog.service.CommentService;

@Controller
public class BlogController {
 
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	
	@RequestMapping(value={"/","login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "The username or password is incorect!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}    

	
    @RequestMapping(value = {"/listArticles**" }, method = RequestMethod.GET)
	public ModelAndView listArticles() {

    	ModelAndView model = new ModelAndView();
    	List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;

	}

/*    @RequestMapping(value = {"/listArticlesAdmin**" }, method = RequestMethod.GET)
	public ModelAndView listArticlesAdmin() {
    	System.out.println("**************************************************************listArticlesAdmin");
		ModelAndView model = new ModelAndView();
		
		List<Article> articles = articleService.findArticlesAll();
		model.addObject("articles", articles);

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		
		return model;
	}
*/    
/*    
    @RequestMapping(value = {"/listCommentsPerArticle**" }, method = RequestMethod.GET)
	public ModelAndView listCommentsPerArticle() {

		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		
		return model;

	}  
*/    
    @RequestMapping(value = {"/searchAnArticle**" }, method = RequestMethod.GET)
	public ModelAndView searchAnArticle() {

    	ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	model.setViewName("searchAnArticle");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	model.setViewName("searchAnArticle");
            } else {
            	model.setViewName("403");
            }
        }
		return model;
    }

    @RequestMapping(value = {"/searchArticle**" }, method = RequestMethod.POST)
    // @RequestParam()
	public ModelAndView searchArticle(@RequestParam(name = "title", required=false) String title, @RequestParam(name = "content", required=false) String content ) {

    	ModelAndView model = new ModelAndView();
		List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesByContentTitle(content, title);
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledByContentTitle(content, title);
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;
    }

    @RequestMapping(value = {"/newArticle**" }, method = RequestMethod.GET)
	public ModelAndView newArticle() {

		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	model.setViewName("newArticle");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	model.setViewName("newArticle");
            } else {
            	model.setViewName("403");
            }
        }
		return model;
    }

    @RequestMapping(value = {"/editArticle**" }, method = RequestMethod.GET)
	public ModelAndView editArticle(@RequestParam(name = "title", required=false) String title) {

    	ModelAndView model = new ModelAndView();
		Article article = articleService.findArticleByTitle(title);
		model.addObject("article", article);
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	model.setViewName("editArticle");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	model.setViewName("editArticle");
            } else {
            	model.setViewName("403");
            }
        }
		return model;
    }

    @RequestMapping(value = {"/validateArticle**" }, method = RequestMethod.GET)
	public ModelAndView validateArticle(@RequestParam(name = "title", required=false) String title) {

    	ModelAndView model = new ModelAndView();
		Article article = articleService.findArticleByTitle(title);
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		article.setEnabled(1);
		articleService.updateArticle(article);

		List<Article> articles = articleService.findArticlesAll();
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		for (Article a: articles) {
			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
			artComments.put(a.getTitle(), comments);
		}
		
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;
	} 
    
    
    @RequestMapping(value = {"/addArticle**" }, method = RequestMethod.POST)
	public ModelAndView addArticle(@RequestParam(name = "title", required=false) String title, @RequestParam(name = "content", required=false) String content ) {

		ModelAndView model = new ModelAndView();
		Article article = new Article();
		
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		article.setContent(content);
		article.setEnabled(0);
		article.setPostedDate(new Date());
		article.setTitle(title);
		article.setUsername(auth.getName());
		articleService.addArticle(article);

		List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;
	}  

    @RequestMapping(value = {"/saveArticle**" }, method = RequestMethod.POST)
	public ModelAndView saveArticle(@RequestParam(name = "title", required=false) String title, @RequestParam(name = "content", required=false) String content ) {

    	ModelAndView model = new ModelAndView();
		Article article = articleService.findArticleByTitle(title);
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		article.setContent(content);
		articleService.updateArticle(article);

		List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;

	}      
    
    @RequestMapping(value = {"/deleteArticle**" }, method = RequestMethod.GET)
	public ModelAndView deleteArticle(@RequestParam(name = "title", required=false) String title) {

    	ModelAndView model = new ModelAndView();
		Article article = articleService.findArticleByTitle(title);
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName().equals(article.getUsername())) {
			articleService.deleteArticle(article);	
		}
		List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;
	}  
    
    @RequestMapping(value = {"/newComment**" }, method = RequestMethod.GET)
	public ModelAndView newComment(@RequestParam(name = "title", required=false) String title) {

		ModelAndView model = new ModelAndView();
		Article article = articleService.findArticleByTitle(title);
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	model.setViewName("newComment");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	model.setViewName("newComment");
            } else {
            	model.setViewName("403");
            }
        }
		model.addObject("article", article);
		return model;
    }

    @RequestMapping(value = {"/editComment**" }, method = RequestMethod.GET)
	public ModelAndView editComment(@RequestParam(name = "commentId", required=false) String commentId) {
    	
    	ModelAndView model = new ModelAndView();
		Comment comment = commentService.findCommentByCommentId(Integer.parseInt(commentId));
		model.addObject("comment", comment);
		
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	model.setViewName("editComment");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	model.setViewName("editComment");
            } else {
            	model.setViewName("403");
            }
        }
		return model;
    }
    
    @RequestMapping(value = {"/addComment**" }, method = RequestMethod.POST)
	public ModelAndView addComment(@RequestParam(name = "title", required=false) String title, @RequestParam(name = "content", required=false) String content ) {
    	
    	ModelAndView model = new ModelAndView();
		Comment comment = new Comment();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		comment.setContent(content);
		comment.setEnabled(0);
		comment.setPostedDate(new Date());
		comment.setTitle(title);
		comment.setUsername(auth.getName());
		commentService.addComment(comment);

		List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;
	}  

    @RequestMapping(value = {"/saveComment**" }, method = RequestMethod.POST)
	public ModelAndView saveComment(@RequestParam(name = "commentId", required=false) String commentId, @RequestParam(name = "content", required=false) String content ) {

    	ModelAndView model = new ModelAndView();
		Comment comment = commentService.findCommentByCommentId(Integer.parseInt(commentId));
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		comment.setContent(content);
		commentService.updateComment(comment);

		List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;
	}      

    @RequestMapping(value = {"/validateComment**" }, method = RequestMethod.GET)
	public ModelAndView validateComment(@RequestParam(name = "commentId", required=false) String commentId) {

    	ModelAndView model = new ModelAndView();
		Comment comment = commentService.findCommentByCommentId(Integer.parseInt(commentId));
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		comment.setEnabled(1);
		commentService.updateComment(comment);

		List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();
		
		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}

            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}

            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;
	}      

    @RequestMapping(value = {"/deleteComment**" }, method = RequestMethod.GET)
	public ModelAndView deleteComment(@RequestParam(name = "commentId", required=false) String commentId) {

    	ModelAndView model = new ModelAndView();
		Comment comment = commentService.findCommentByCommentId(Integer.parseInt(commentId));
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName().equals(comment.getUsername())) {
			commentService.deleteComment(comment);	
		}
		
		List<Article> articles = null;
		Map<String, List<Comment>> artComments = new HashMap<String, List<Comment>>();

		for (GrantedAuthority ga : auth.getAuthorities()) {
            if ("ADMIN".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticlesAdmin");
            	break;
            } else if ("USER".equals(ga.getAuthority())) {
            	articles = articleService.findArticlesEnabledAll();
        		for (Article a: articles) {
        			List<Comment> comments = commentService.findCommentsEnabledByTitle(a.getTitle());
        			artComments.put(a.getTitle(), comments);
        		}
            	model.setViewName("listArticles");
            } else {
            	model.setViewName("403");
            }
        }
		model.addObject("articles", articles);
		model.addObject("artComments", artComments);
		return model;
	}      
    
    
    @RequestMapping(value={"/logout"})
    public String logout(){
        return "login";
    }
    
	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}
		model.setViewName("403");
		return model;
	}
    
}


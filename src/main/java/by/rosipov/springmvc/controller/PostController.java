package by.rosipov.springmvc.controller;

import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.rosipov.springmvc.model.Comment;
import by.rosipov.springmvc.model.Post;
import by.rosipov.springmvc.service.Services;

@Controller
@RequestMapping("/")
public class PostController {
	
	@Autowired
	Services service;
	
	/**
	 * 
	 * this method shows post page
	 */
	@RequestMapping(value = {"post-id-{id}"}, method = RequestMethod.GET)
	public String showPost(ModelMap model, @PathVariable int id)
	{
		Post post = service.findById(id);
		model.addAttribute("post", post);
		service.viewup(post);
		List<Comment> comments = service.findAllComments(id);
		model.addAttribute("comments", comments);
		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		return "post";
	}
	
	/**
	 *  adding comments on post page
	 */
	
	@RequestMapping(value = { "/post-id-{id}" }, method = RequestMethod.POST)
	public String addComment(@PathVariable int id, ModelMap model, Comment comm) {
		
		Post post = service.findById(id);
		model.addAttribute("post", post);
		
		java.util.Date commDate = new java.util.Date();

		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		
		comment.setCommDate(commDate);
		comment.setComm(html2text(comm.getComm()));
		comment.setCommID(id);
		service.saveComment(comment);
		service.commentup(post);
		
		return "redirect:/post-id-{id}#bottom";
	}

	public String html2text(String html) {
		return Jsoup.parse(html).text();
	}
	
	/**
	 * Bump post
	 */

	@RequestMapping(value = { "/postup-id-{id}" }, method = RequestMethod.GET)
	public String bumpPost(@PathVariable int id, ModelMap model) {
		Post post = service.findById(id);
		model.addAttribute("post", post);
		service.bump(post);
		return "redirect:/post-id-{id}#up";
	}

	/**
	 * Sage post
	 */

	@RequestMapping(value = { "/postsage-id-{id}" }, method = RequestMethod.GET)
	public String sagePost(@PathVariable int id, ModelMap model) {
		Post post = service.findById(id);
		model.addAttribute("post", post);
		service.sage(post);
		return "redirect:/post-id-{id}#up";
	}

}

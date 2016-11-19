package by.rosipov.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.rosipov.springmvc.model.Post;
import by.rosipov.springmvc.service.Services;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	Services service;
	int postsAmount = 4; 
	
	
	/**
	 * method shows home page with posts
	*/
	
	@RequestMapping(value = {"/home", "/", "/OverBump"}, method = RequestMethod.GET)
	public String ListPosts(ModelMap model) {
		
		List<Post> posts = service.allPosts(postsAmount);
		model.addAttribute("posts", posts);
		model.addAttribute("postsAmount", postsAmount);
		return "home";
	}
	
	/**
	 * 
	 * this method shows more posts on page
	 */
	@RequestMapping(value = {"/home-showmore-{amount}"}, method = RequestMethod.GET)
	public String morePosts(ModelMap model, @PathVariable int amount) {
		
		amount+=4;
		List<Post> posts = service.allPosts(amount);
		model.addAttribute("posts", posts);
		model.addAttribute("amount", amount);
		return "home";
}
	
	/**
	 * This method will list TOP posts.
	 */
	@RequestMapping(value = { "/home-top" }, method = RequestMethod.GET)
	public String topPosts(ModelMap model) {

		List<Post> posts = service.postByBumps();
		model.addAttribute("posts", posts);
		return "home";
	}
	
	/**
	 * this method shows pined posts
	 */
	@RequestMapping(value = {"/home-pin"}, method = RequestMethod.GET)
	public String pinPosts(ModelMap model){
		
		List<Post> posts = service.pinedPosts();
		model.addAttribute("posts", posts);
		return "home";
	}


}













































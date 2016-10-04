package com.websystique.springmvc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.websystique.springmvc.model.FileBucket;
import com.websystique.springmvc.model.Comment;
import com.websystique.springmvc.service.DocumentService;
import com.websystique.springmvc.service.Services;
import com.websystique.springmvc.util.FileValidator;
import com.websystique.springmvc.model.Post;
import com.websystique.springmvc.model.ServerLocation;
import com.websystique.springmvc.model.User;
//import com.websystique.springmvc.model.UserDocument;
import com.websystique.springmvc.model.Message;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	Services service;

	static String r = "";

	int postsAmount = 4;

	@Autowired
	DocumentService documentService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	/**
	 * This method will list all existing posts.
	 */
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String listPosts(ModelMap model) {
		List<Post> posts = service.allPosts(postsAmount);
		model.addAttribute("posts", posts);
		model.addAttribute("amount", postsAmount);
		return "home";
	}

	/**
	 * This method will list all existing posts.
	 */
	@RequestMapping(value = { "/home-showMore-{amount}" }, method = RequestMethod.GET)
	public String morePosts(ModelMap model, @PathVariable int amount) {
		amount += 4;
		List<Post> posts = service.allPosts(amount);
		model.addAttribute("amount", amount);
		model.addAttribute("posts", posts);
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

	@RequestMapping(value = { "/home-pin" }, method = RequestMethod.GET)
	public String pinPosts(ModelMap model) {

		List<Post> posts = service.pinedPosts();
		model.addAttribute("posts", posts);
		return "home";
	}

	/**
	 * This method will show post page
	 */
	@RequestMapping(value = { "/post-id-{id}" }, method = RequestMethod.GET)
	public String showPost(@PathVariable int id, ModelMap model) {

		Post post = service.findById(id);
		model.addAttribute("post", post);
		service.viewup(post);
		List<Comment> comments = service.findAllComments(id);
		model.addAttribute("comments", comments);
		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		return "post";
	}

	/*
	 * @RequestMapping(value = { "/postup-id-{id}"}, method = RequestMethod.GET)
	 * public String upPost(@PathVariable int id, ModelMap model) { Post post =
	 * service.findById(id); model.addAttribute("post", post); int bumps =
	 * post.getBumps() + 1; post.setBumps(bumps); return
	 * "redirect:/post-id-{id}"; }
	 */

	@RequestMapping(value = { "/post-id-{id}" }, method = RequestMethod.POST)
	public String addComment(@PathVariable int id, ModelMap model, Comment comm) {

		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		Post post = service.findById(id);
		model.addAttribute("post", post);
		// * comments
		// model.addAttribute("comment", comment);
		java.util.Date commDate = new java.util.Date();
		comment.setCommDate(commDate);
		comment.setComm(html2text(comm.getComm()));
		comment.setCommID(id);
		service.saveComment(comment);
		service.commentup(post);
		// saveComment(comment, id);
		return "redirect:/post-id-{id}#bottom";
	}

	/**
	 * Upload
	 */

	public static String html2text(String html) {
		return Jsoup.parse(html).text();
	}

	@RequestMapping(value = { "/upload" }, method = RequestMethod.GET)
	public String uploadPage(ModelMap model) throws IOException {
		// Post post = new Post();
		// model.addAttribute("post", post);
		// model.addAttribute("edit", false);
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "upload";
	}

	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String uploadPost(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {

		List<Post> posts = service.findAllPosts();
		model.addAttribute("posts", posts);
		Post post = new Post();
		model.addAttribute("post", post);
		// if (result.hasErrors()) {
		// System.out.println("validation errors");

		// return "upload";
		// }

		if (fileBucket.getDescription() == null || fileBucket.getDescription().isEmpty()) {
			FieldError desError = new FieldError("post", "description", messageSource.getMessage(
					"empty.post.description", new String[] { fileBucket.getDescription() }, Locale.getDefault()));
			result.addError(desError);
			return "upload";
		}

		MultipartFile multipartFile = fileBucket.getFile();
		Boolean check = false;
		int id = 0;

		for (Post p : posts) {
			// System.out.print(Arrays.equals(post.getContent(),
			// post.getContent()));
			// System.out.print(Arrays.equals(p.getContent(),
			// post.getContent()));
			if (Arrays.equals(p.getContent(), multipartFile.getBytes())) {
				check = true;
				id = p.getId();
				break;
			}
		}

		if (check == true) {
			Post existPost = service.findById(id);
			model.addAttribute("existPost", existPost);
			// model.addAttribute("success", "such image has already been
			// uploaded");
			return "cantupload";
		} else {
			saveFile(fileBucket, post, multipartFile);

			String name = post.getName();
			try (OutputStream out = new BufferedOutputStream(new FileOutputStream(
					new File("/etc/apache-tomcat-7.0.67/webapps/Spring4MVCFileUploadDownloadWithHibernate/static/dat/"
							+ name)))) {
				out.write(fileBucket.getFile().getBytes());
			}
			return "redirect:/upload";
		}
	}

	/**
	 * feedback
	 */

	@RequestMapping(value = { "/feedback" }, method = RequestMethod.GET)
	public String feedbackPage(ModelMap model) throws IOException {
		Message message = new Message();
		model.addAttribute("message", message);
		// Post post = new Post();
		// model.addAttribute("post", post);
		// model.addAttribute("edit", false);

		return "feedback";
	}

	@RequestMapping(value = { "/feedback" }, method = RequestMethod.POST)
	public String feedback(@Valid Message message, BindingResult result, ModelMap model) throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");

			return "feedback";
		}

		if (message.getName() == null || message.getName().isEmpty()) {
			FieldError emailError = new FieldError("message", "name", messageSource.getMessage("empty.message.name",
					new String[] { message.getEmail() }, Locale.getDefault()));
			result.addError(emailError);
			return "feedback";
		}

		if (message.getEmail() == null || message.getEmail().isEmpty()) {
			FieldError emailError = new FieldError("message", "email", messageSource.getMessage("empty.message.email",
					new String[] { message.getEmail() }, Locale.getDefault()));
			result.addError(emailError);
			return "feedback";
		}

		if (!isValidEmailAddress(message.getEmail())) {
			FieldError emailError = new FieldError("message", "email", messageSource
					.getMessage("notValid.message.email", new String[] { message.getEmail() }, Locale.getDefault()));
			result.addError(emailError);
			return "feedback";
		}

		// Message message = new Message();
		// model.addAttribute("message", message);
		saveMessage(message);
		return "redirect:/feedback";
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	private void saveMessage(Message message) {
		message.setName(message.getName());
		message.setEmail(message.getEmail());
		message.setPhone(message.getPhone());
		message.setText(message.getText());
		service.saveMessage(message);
	}

	private void saveFile(FileBucket fileBucket, Post post, MultipartFile multipartFile) throws IOException {

		// MultipartFile multipartFile = fileBucket.getFile();
		post.setViews(0);
		post.setComments(0);
		post.setName(multipartFile.getOriginalFilename());
		post.setDescription(html2text(fileBucket.getDescription()));
		post.setType(multipartFile.getContentType());
		post.setContent(multipartFile.getBytes());
		post.setBumps(0);
		post.setSage(0);
		java.util.Date dateValue = new java.util.Date();
		// DateTime dateTime = new DateTime();
		post.setJoiningDate(dateValue);
		LocationController obj = new LocationController();
		ServerLocation location = obj.getLocation("31.148.31.0");
		post.setCity(location.getCity());
		post.setCountry(location.getCountryName());
		service.savePost(post);
	}

	/**
	 * Bump
	 */

	@RequestMapping(value = { "/postup-id-{id}" }, method = RequestMethod.GET)
	public String bumpPost(@PathVariable int id, ModelMap model) {
		Post post = service.findById(id);
		model.addAttribute("post", post);
		service.bump(post);
		return "redirect:/post-id-{id}#up";
	}

	/**
	 * Sage
	 */

	@RequestMapping(value = { "/postsage-id-{id}" }, method = RequestMethod.GET)
	public String sagePost(@PathVariable int id, ModelMap model) {
		Post post = service.findById(id);
		model.addAttribute("post", post);
		service.sage(post);
		return "redirect:/post-id-{id}#up";
	}

	/**
	 * Report
	 */

	@RequestMapping(value = { "/report-id-{id}" }, method = RequestMethod.GET)
	public String reportPost(@PathVariable int id, ModelMap model) {
		Post post = service.findById(id);
		model.addAttribute("post", post);

		return "report";
	}

	/*
	 * @RequestMapping(value = { "/edit-{ssn}-employee" }, method =
	 * RequestMethod.POST) public String updateEmployee(@Valid Employee
	 * employee, BindingResult result, ModelMap model, @PathVariable String ssn)
	 * {
	 * 
	 * if (result.hasErrors()) { return "registration"; }
	 * 
	 * if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
	 * FieldError ssnError =new
	 * FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn",
	 * new String[]{employee.getSsn()}, Locale.getDefault()));
	 * result.addError(ssnError); return "registration"; }
	 * 
	 * service.updateEmployee(employee);
	 * 
	 * model.addAttribute("success", "Employee " + employee.getName() +
	 * " updated successfully"); return "success"; }
	 */
	/*
	 * @RequestMapping(value = { "/add-document-{userId}" }, method =
	 * RequestMethod.GET) public String addDocuments(@PathVariable int userId,
	 * ModelMap model) { /*User user = userService.findById(userId);
	 * model.addAttribute("user", user);
	 * 
	 * FileBucket fileModel = new FileBucket(); model.addAttribute("fileBucket",
	 * fileModel);
	 * 
	 * List<UserDocument> documents =
	 * userDocumentService.findAllByUserId(userId);
	 * model.addAttribute("documents", documents);
	 * 
	 * return "managedocuments"; }
	 * 
	 * 
	 * @RequestMapping(value = { "/download-document-{userId}-{docId}" }, method
	 * = RequestMethod.GET) public String downloadDocument(@PathVariable int
	 * userId, @PathVariable int docId, HttpServletResponse response) throws
	 * IOException { Document document = documentService.findById(docId);
	 * response.setContentType(document.getType());
	 * response.setContentLength(document.getContent().length);
	 * response.setHeader("Content-Disposition","attachment; filename=\"" +
	 * document.getName() +"\"");
	 * 
	 * FileCopyUtils.copy(document.getContent(), response.getOutputStream());
	 * 
	 * return "redirect:/add-document-"+userId; }
	 * 
	 * @RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method =
	 * RequestMethod.GET) public String deleteDocument(@PathVariable int
	 * userId, @PathVariable int docId) { documentService.deleteById(docId);
	 * return "redirect:/add-document-"+userId; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @RequestMapping(value = { "/add-document-{userId}" }, method =
	 * RequestMethod.POST) public String uploadDocument(@Valid FileBucket
	 * fileBucket, BindingResult result, ModelMap model, @PathVariable int
	 * userId) throws IOException{ /* if (result.hasErrors()) {
	 * System.out.println("validation errors"); User user =
	 * userService.findById(userId); model.addAttribute("user", user);
	 * 
	 * List<UserDocument> documents =
	 * userDocumentService.findAllByUserId(userId);
	 * model.addAttribute("documents", documents); return "managedocuments"; /*}
	 * else {
	 * 
	 * System.out.println("Fetching file");
	 * 
	 * User user = userService.findById(userId); model.addAttribute("user",
	 * user);
	 * 
	 * saveDocument(fileBucket, user);
	 * 
	 * return "redirect:/add-document-"+userId; } }
	 */

}
package by.rosipov.springmvc.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import by.rosipov.springmvc.controller.ImageProcessing;
import by.rosipov.springmvc.controller.LocationController;

import by.rosipov.springmvc.model.FileBucket;
import by.rosipov.springmvc.model.Post;
import by.rosipov.springmvc.model.ServerLocation;
import by.rosipov.springmvc.service.DocumentService;
import by.rosipov.springmvc.service.Services;
import by.rosipov.springmvc.util.FileValidator;


@Controller
@RequestMapping ("/")
public class UploadController {

	@Autowired
	Services service;
	
	@Autowired
	DocumentService documentService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;
	
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

	
	// add comparing by bitChain to avoid duplicates
	
	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String uploadPost(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {

		List<Post> posts = service.findAllPosts();
		model.addAttribute("posts", posts);
		Post post = new Post();
		model.addAttribute("post", post);
		
		
		
		//String bitChain = post.getBitChain();
		
		
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
		//int number = 0;
		int avrColor;
		String bitChain;
		//List<Integer> numbers= new ArrayList<Integer>();
		
		InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
		BufferedImage bImage = ImageIO.read(in);
		ImageProcessing.scale(bImage, 8, 8);
		avrColor = ImageProcessing.averageColor(bImage);
		bitChain = ImageProcessing.bitChain(bImage, avrColor);
		List<Post> samePosts = new ArrayList<Post>();

		for (Post p : posts) {
			// System.out.print(Arrays.equals(post.getContent(),
			// post.getContent()));
			// System.out.print(Arrays.equals(p.getContent(),
			// post.getContent()));
			
			if(ImageProcessing.getHammingDistance(p.getBitChain(), bitChain) <= 10)
			{
				check = true;
				//numbers.add(p.getId());
				samePosts.add(p);
			}
				
			
			/*if (Arrays.equals(p.getContent(), multipartFile.getBytes())) {
				check = true;
				number = p.getId();
				break;
			}*/
		}

		if (check == true) {
			
			//for (int i = 0; i <= numbers.size(); i++)
			//{
			//	samePosts.add(service.findById(numbers.get(i)));
			//}
			model.addAttribute("samePosts", samePosts);
			
			//post = service.findById(number);
			//model.addAttribute("post", post);
			// model.addAttribute("success", "such image has already been
			// uploaded");
			return "cantupload";
		} else {
			saveFile(fileBucket, post, multipartFile);
			
			
			service.setBitChain(post);
			
			// imageProcessing adding bitChain after file saving using services update Post.bitChain
			
			//String name = post.getName();
			//try (OutputStream out = new BufferedOutputStream(new FileOutputStream(
			//		new File("/etc/apache-tomcat-7.0.67/webapps/Overbump/static/dat/"
			//				+ name)))) {
			//	out.write(fileBucket.getFile().getBytes());
			//}
			return "redirect:/upload";
		}
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
		LocationController loc = new LocationController();
		ServerLocation location = loc.getLocation("31.148.31.0");
		post.setCity(location.getCity());
		post.setCountry(location.getCountryName());
		post.setBitChain("");
		service.savePost(post);
	}

	
}

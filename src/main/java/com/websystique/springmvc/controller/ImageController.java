package com.websystique.springmvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.websystique.springmvc.model.Post;
import com.websystique.springmvc.service.Services;

@Controller
@RequestMapping("/imageController")
public class ImageController {
	
	@Autowired
	Services service;

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
		public void showImage(@RequestParam("id") Integer id, HttpServletResponse response,HttpServletRequest request)
				throws ServletException, IOException{
		
		  	Post post = service.findById(id);        
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		    response.getOutputStream().write(post.getContent());


		    response.getOutputStream().close();
	}
	
}

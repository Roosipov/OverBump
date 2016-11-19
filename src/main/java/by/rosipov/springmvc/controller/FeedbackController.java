package by.rosipov.springmvc.controller;

import java.io.IOException;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.rosipov.springmvc.model.Message;
import by.rosipov.springmvc.service.Services;


@Controller
@RequestMapping("/")
public class FeedbackController {
	
	@Autowired
	Services service;
	
	@Autowired
	MessageSource messageSource;

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
	
}

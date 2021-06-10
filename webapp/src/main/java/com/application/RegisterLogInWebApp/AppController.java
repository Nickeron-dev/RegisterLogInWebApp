package com.application.RegisterLogInWebApp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/home")
	public String home() {
		return "index"; // home page
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerSubmit(@Valid @ModelAttribute User newUser,
			BindingResult result, Model model) {
		// checking all possible errors
		
		// here I check if an email has already been used
		List<User> users = repository.findAll();
		
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(newUser.getEmail()) ) {
				result.addError(new FieldError("user", "email", "This email has already been used")); // shows HTML paragraph that has error message
				return "register"; // return page with showed errors
			}
		}
		
		// here I check for any other validation errors
		if (result.hasErrors()) {
			return "register";
		}
		
		model.addAttribute("user", newUser);
		
		repository.save(newUser); // adding new user to database
		
		return "registerresult"; // show successful page
	}
	
	@GetMapping("/error")
	public String error() {
		return "error"; // for any errors(404)
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginSubmit(@Valid @ModelAttribute User user,
			BindingResult result, Model model) {
		// if user with same data exists - return successful page with the entire database
		List<User> users = repository.findAll();
		
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(user.getEmail()) && // checking email
					users.get(i).getPassword().equals(user.getPassword()) ) {	// checking password
				model.addAttribute("users", users);
				return "loginsuccess";
			}
		}
		
		// if user with same data wasn't found - add error that will make HTML 
		// paragraph with message visible
		result.addError(new FieldError("user", "password", "This account doesn't exist!"));
		model.addAttribute("user", user);
		
		return "login";
	}
	
}

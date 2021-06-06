package com.application.RegisterLogInWebApp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repository;
	
	private boolean isLoggedIn = false;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerSubmit(@Valid @ModelAttribute User newUser, BindingResult result, Model model) {
		
		// checking all possible issues
		List<User> users = repository.findAll();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(newUser.getEmail()) ) {
				return "registererror";	// if an email has already been used
			}
		}
		
		if (result.hasErrors()) {
			return "register";
		}
		
		model.addAttribute("user", newUser);
		
		System.out.println("Email: " + newUser.getEmail().length() + " F name: " + newUser.getFirstName().length() + " L name: " + newUser.getLastName().length() + " Pass: " + newUser.getPassword().length());
		
		repository.save(newUser);
		
		
		return "registerresult";
		
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginSubmit(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		List<User> users = repository.findAll();
		System.out.println("Received: " + user.getEmail() + " " + user.getPassword());
		for (int i = 0; i < users.size(); i++) {
			System.out.println("Comparing: " + users.get(i).getEmail() + " " + users.get(i).getPassword());
			if (users.get(i).getEmail().equals(user.getEmail()) && users.get(i).getPassword().equals(user.getPassword()) ) {
				isLoggedIn = true;
				model.addAttribute("users", users);
				return "loginsuccess";
			}
		}
		
		if (result.hasErrors()) {
			return "login";
		}
		
		model.addAttribute("user", user);
		
		
		
		return "loginerror";
	}
}

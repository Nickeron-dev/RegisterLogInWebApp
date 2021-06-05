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
	public String registerSubmit(@ModelAttribute User newUser, Model model) {
		model.addAttribute("user", newUser);
		
		System.out.println("Email: " + newUser.getEmail().length() + " F name: " + newUser.getFirstName().length() + " L name: " + newUser.getLastName().length() + " Pass: " + newUser.getPassword().length());
		
		
		// checking all possible issues
		List<User> users = repository.findAll();
		
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(newUser.getEmail()) ) {
				return "erroranother";	// if an email has already been used
			}
		}
		
		if (newUser.getEmail().length() < 13) {
			return "erroranother";
		}
		
		if (newUser.getFirstName().length() < 2 || newUser.getFirstName().length() > 20) {
			return "erroranother";
		}
		
		if (newUser.getLastName().length() < 2 || newUser.getLastName().length() > 20) {
			return "erroranother";
		}
		
		if (newUser.getPassword().length() < 5 || newUser.getPassword().length() > 64) {
			return "erroranother";
		}
		
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
}

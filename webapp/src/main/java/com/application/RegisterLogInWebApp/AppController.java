package com.application.RegisterLogInWebApp;

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
		newUser.setId(newUser.getId() + 1);
		repository.save(newUser);

		
		return "registerresult";
		
	}
	
	@GetMapping("/error")
	public String error() {
		User user = new User();
		System.out.println("sdjakljkfdls" + user.getId());
		return "error";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
}

package com.hyjiangd.webstore.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
	
	@GetMapping("/")
	public String showHome(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "index";
	}
	
	@GetMapping("/userarea")
	public String showUserArea(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "userarea";
	}
	
	@GetMapping("/userarea/updatedetail")
	public String showUpdateUserDetailFormPage(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "updatedetail";
	}
	
	@GetMapping("/userarea/updatepassword")
	public String showUpdateUserPasswordPage(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "updatepassword";
	}
	
	@GetMapping("/search")
	public String showSearchResult() {
		return "search";
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}
}

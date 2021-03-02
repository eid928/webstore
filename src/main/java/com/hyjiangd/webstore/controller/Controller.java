package com.hyjiangd.webstore.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/userarea")
	public String showUserArea() {
		return "userarea";
	}
	
	@GetMapping("/search")
	public String showSearch() {
		return "search";
	}
}

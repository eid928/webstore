package com.hyjiangd.webstore.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.joran.spi.ElementSelector;

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
	public String showSearchResult(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "search";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/cart")
	public String addItemToCart(HttpServletRequest request) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		request.setAttribute("loginUsername", loginUsername);
		
		HttpSession session = request.getSession();
		String referer = request.getHeader("Referer");
		System.out.println("Referer = " + referer);
		List<Map<String, Integer>> cart;
		
		if (session.getAttribute("cart") == null) {
			cart = new ArrayList<Map<String,Integer>>();
			session.setAttribute("cart", cart);
		} else {
			cart = (List<Map<String, Integer>>) session.getAttribute("cart");
		}
		
		int goodsId = Integer.parseInt(request.getParameter("id"));
		boolean isAlreadyInCart = false;
		for (Map<String, Integer> item : cart) {
			if (item.get("goodsId") == goodsId) {
				isAlreadyInCart = true;
				item.put("quantity", item.get("quantity") + 1) ;
			}
		}
		if (!isAlreadyInCart) {
			Map<String, Integer> item = new HashMap<>();
			item.put("goodsId", goodsId);
			item.put("quantity", 1);
			cart.add(item);
		}
		
		System.out.println("session購物車內有: " + request.getSession().getAttribute("cart"));
		
		return "redirect:" + referer;
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

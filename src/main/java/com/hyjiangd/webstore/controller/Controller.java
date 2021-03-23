package com.hyjiangd.webstore.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.service.GoodsService;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired
	private GoodsService goodsService;
	
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
	
	@SuppressWarnings("unchecked")
	@GetMapping("/showcart")
	public String showCart(HttpServletRequest request, Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		HttpSession session = request.getSession();
		List<Map<String, Object>> cart = new ArrayList<>();
		List<Map<String, Integer>> cartInSession = (List<Map<String, Integer>>) session.getAttribute("cart");
		int total = 0;
		
		if (cartInSession != null) {
			
			for (Map<String, Integer> item : cartInSession) {
				Goods goods = goodsService.findById(item.get("goodsId"));
				int quantity = item.get("quantity");
				Map<String, Object> itemDetail = new HashMap<>();
				itemDetail.put("goods", goods);
				itemDetail.put("quantity", quantity);
				cart.add(itemDetail);
				
				total += goods.getPrice() * quantity;
			}
		}
		model.addAttribute("cart", cart);
		model.addAttribute("total", total);
		System.out.println("詳細購物車內有: " + cart);
		
		return "showcart";
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

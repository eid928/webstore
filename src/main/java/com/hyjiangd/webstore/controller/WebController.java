package com.hyjiangd.webstore.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.service.GoodsService;
import com.hyjiangd.webstore.upload.FileUtils;

@Controller
public class WebController {
	
	@Autowired
	private GoodsService goodsService;
	
//	private final ResourceLoader resourceLoader;
//	
//	@Autowired
//	public WebController(ResourceLoader resourceLoader) {
//		this.resourceLoader = resourceLoader;
//	}
	
	@Value("${web.upload-path}")
	private String path;
	
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
	
	@GetMapping("/goodsdetail")
	public String showGoodsDetailPage(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "goodsdetail";
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
	
	@SuppressWarnings("unchecked")
	@GetMapping("/remove")
	public String removeItemFromCart(HttpServletRequest request) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		request.setAttribute("loginUsername", loginUsername);
		
		int goodsId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		
		List<Map<String, Integer>> cart = (List<Map<String, Integer>>) session.getAttribute("cart");
		
		for (int i = 0; i < cart.size(); i ++) {
			if (cart.get(i).get("goodsId") == goodsId) {
				cart.remove(i);
			}
		}
		
		System.out.println("session購物車內有: " + request.getSession().getAttribute("cart"));
		
		return "redirect:/showcart";
	}
	
	@GetMapping("/ordersubmit")
	public String showOrderSubmissionResult(HttpServletRequest request) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		request.setAttribute("loginUsername", loginUsername);
		
		request.getSession().removeAttribute("cart");
		
		return "showorderofbuyer";
	}
	
	@GetMapping("/sellgoods")
	public String showGoodsOfSeller(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "sellgoods";
	}
	
	@GetMapping("/postgoods")
	public String showGoodsPostForm(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "postgoods";
	}
	
	@PostMapping("/fileUpload")
	public String upload(@RequestParam("fileName") MultipartFile file, Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		String newFileName = FileUtils.upload(file, path, file.getOriginalFilename());
		model.addAttribute("newFileName", newFileName);
		
		return "postgoods";
	}
	
	@GetMapping("/updategoods")
	public String showGoodsUpdateForm(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "updategoods";
	}
	
	@PostMapping("/fileUpdate")
	public String updateImage(@RequestParam("fileName") MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		
		String newFileName = FileUtils.upload(file, path, file.getOriginalFilename());
		
		String id = request.getParameter("id");
		
		redirectAttributes.addAttribute("id", id);
		redirectAttributes.addFlashAttribute("newFileName", newFileName);
		
		return "redirect:/updategoods";
	}
	
	@GetMapping("/showorderofbuyer")
	public String showOrderOfBuyer(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "showorderofbuyer";
	}
	
	@GetMapping("/showorderofseller")
	public String showOrderOfSeller(Model model) {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		
		return "showorderofseller";
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

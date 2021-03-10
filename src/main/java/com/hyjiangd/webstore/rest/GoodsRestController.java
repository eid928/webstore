package com.hyjiangd.webstore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.service.GoodsService;

@RestController
public class GoodsRestController {
	
	@Autowired
	private GoodsService goodsService;
	
	@GetMapping("/goods")
	public List<Goods> showGoodsList() {
		
		return goodsService.findAll();
	}
}

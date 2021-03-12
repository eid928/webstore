package com.hyjiangd.webstore.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.service.GoodsService;

@RestController
public class GoodsRestController {
	
	@Autowired
	private GoodsService goodsService;
	
	@GetMapping("/searchgoods")
	public List<Goods> showGoodsList(@RequestParam String goodsKeyword, 
									 @RequestParam String order, 
									 @RequestParam boolean asc, 
									 @RequestParam int page) {
		
		return goodsService.searchByGoodsName(goodsKeyword, order, asc, page);
	}
	
	@PostMapping("/goods")
	public String postGoods(@RequestBody @Valid Goods goods) {
		
		goodsService.postGoods(goods);
		
		return "已新增商品";
	}
	
	@PutMapping("/goods")
	public String updateGoods(@RequestBody @Valid Goods goods) {
		
		goodsService.updateGoods(goods);
		
		return "已更新商品";
	}
}

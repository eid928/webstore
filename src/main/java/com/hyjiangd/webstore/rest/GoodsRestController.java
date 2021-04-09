package com.hyjiangd.webstore.rest;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.message.CrudMsg;
import com.hyjiangd.webstore.message.SearchMsg;
import com.hyjiangd.webstore.service.GoodsService;

@RestController
public class GoodsRestController {
	
	@Autowired
	private GoodsService goodsService;
	
	@GetMapping("/findgoods/{goodsId}")
	public Goods showGoodsById(@PathVariable int goodsId) {
		
		System.out.println("In goodsRestController");
		Goods goods = goodsService.findById(goodsId);
		System.out.println(goods.getLastUpdateTime().getClass());
		
		return goods;
	}
	
	@GetMapping("/search/goodsname")
	public SearchMsg<Goods> showGoodsListByGoodsName(@RequestParam String goodsKeyword, 
									 			@RequestParam String order, 
									 			@RequestParam boolean asc, 
									 			@RequestParam int page) {
		
		return goodsService.searchByGoodsName(goodsKeyword, order, asc, page);
	}
	
	@GetMapping("/search/goodsseller")
	public SearchMsg<Goods> showGoodsListByGoodsSeller(@RequestParam String sellerKeyword, 
												  @RequestParam String order, 
												  @RequestParam boolean asc, 
												  @RequestParam int page) {
		
		return goodsService.searchByGoodsSeller(sellerKeyword, order, asc, page);
	}
	
	@PostMapping("/goods")
	public CrudMsg postGoods(@RequestBody @Valid Goods goods) {
		
		goodsService.postGoods(goods);
		String msg = "已成功新增商品";
		
		return new CrudMsg(msg, new Date());
	}
	
	@PutMapping("/goods")
	public CrudMsg updateGoods(@RequestBody @Valid Goods goods) {
		
		goodsService.updateGoods(goods);
		String msg = "已成功更新商品";
		CrudMsg crudMsg = new CrudMsg(msg, new Date());
		
		return crudMsg;
	}
	
	@DeleteMapping("/goods/{id}")
	public CrudMsg deleteGoods(@PathVariable int id) {
		
		goodsService.deleteGoods(id);
		String msg = "已成功下架商品";
		
		return new CrudMsg(msg, new Date());
	}
}

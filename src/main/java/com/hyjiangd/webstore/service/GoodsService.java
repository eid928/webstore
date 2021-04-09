package com.hyjiangd.webstore.service;


import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.message.SearchMsg;

public interface GoodsService {
	
	public Goods findById(int id);
	public SearchMsg<Goods> searchByGoodsName(String goodsKeyword, String order, boolean asc, int page);
	public SearchMsg<Goods> searchByGoodsSeller(String sellerKeyword, String order, boolean asc, int page);
	public void postGoods(Goods goods);
	public Goods updateGoods(Goods goods);
	public void deleteGoods(int id);
}

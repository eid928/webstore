package com.hyjiangd.webstore.service;

import java.util.List;

import com.hyjiangd.webstore.entity.Goods;

public interface GoodsService {
	
	public List<Goods> searchByGoodsName(String goodsKeyword, String order, boolean asc, int page);
	public List<Goods> searchByGoodsSeller(String sellerKeyword, String order, boolean asc, int page);
	public void postGoods(Goods goods);
	public void updateGoods(Goods goods);
}

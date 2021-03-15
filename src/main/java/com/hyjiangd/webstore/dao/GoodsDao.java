package com.hyjiangd.webstore.dao;

import java.util.List;

import com.hyjiangd.webstore.entity.Goods;

public interface GoodsDao {
	
	public Goods findById(int id);
	public List<Goods> searchGoodsByGoodsName(String goodsKeyword, String order, boolean asc, int elementInPage, int startElement);
	public List<Goods> searchGoodsByGoodsSeller(String sellerKeyword, String order, boolean asc, int elementInPage, int startElement);
	public void save(String usernameOfLogin, Goods goods);
	public void update(String usernameOfLogin, Goods goods);
}

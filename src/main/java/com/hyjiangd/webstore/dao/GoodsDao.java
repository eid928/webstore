package com.hyjiangd.webstore.dao;


import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.message.SearchMsg;

public interface GoodsDao {
	
	public Goods findById(int id);
	public SearchMsg<Goods> searchGoodsByGoodsName(String goodsKeyword, String order, boolean asc, int elementInPage, int startElement);
	public SearchMsg<Goods> searchGoodsByGoodsSeller(String sellerKeyword, String order, boolean asc, int elementInPage, int startElement);
	public void save(String usernameOfLogin, Goods goods);
	public void update(String usernameOfLogin, Goods goods);
	public void delete(String usernameOfLogin, int id);
}

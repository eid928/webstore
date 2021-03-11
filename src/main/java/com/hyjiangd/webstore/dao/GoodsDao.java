package com.hyjiangd.webstore.dao;

import java.util.List;

import com.hyjiangd.webstore.entity.Goods;

public interface GoodsDao {
	
	public List<Goods> findAll();
	public List<Goods> findByUsername(String username);
	public void save(String usernameOfLogin, Goods goods);
	public void update(String usernameOfLogin, Goods goods);
}

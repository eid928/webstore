package com.hyjiangd.webstore.service;

import java.util.List;

import com.hyjiangd.webstore.entity.Goods;

public interface GoodsService {
	
	public List<Goods> findAll();
	public List<Goods> findByUsername(String username);
	public void save(String username, Goods goods);
}

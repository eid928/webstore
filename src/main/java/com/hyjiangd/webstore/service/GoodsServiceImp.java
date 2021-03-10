package com.hyjiangd.webstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyjiangd.webstore.dao.GoodsDao;
import com.hyjiangd.webstore.entity.Goods;

@Service
public class GoodsServiceImp implements GoodsService{
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public List<Goods> findAll() {
		
		return goodsDao.findAll();
	}

	@Override
	public List<Goods> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(String username, Goods goods) {
		// TODO Auto-generated method stub
		
	}
}

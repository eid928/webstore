package com.hyjiangd.webstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hyjiangd.webstore.dao.GoodsDao;
import com.hyjiangd.webstore.entity.Goods;

@Service
public class GoodsServiceImp implements GoodsService{
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	@Transactional
	public List<Goods> searchByGoodsName(String goodsKeyword, String order, boolean asc, int page) {
		
		int elementInPage = 6;
		int startElement = (page - 1) * elementInPage;
		
		return goodsDao.searchGoodsByGoodsName(goodsKeyword, order, asc, elementInPage, startElement);
	}

	@Override
	@Transactional
	public List<Goods> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void postGoods(Goods goods) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.setId(0);
		goodsDao.save(username, goods);
	}

	@Override
	@Transactional
	public void updateGoods(Goods goods) {
		
		String usernameOfLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		goodsDao.update(usernameOfLogin, goods);
	}
}

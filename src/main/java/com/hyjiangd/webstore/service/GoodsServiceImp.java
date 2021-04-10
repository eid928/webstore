package com.hyjiangd.webstore.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hyjiangd.webstore.dao.GoodsDao;
import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.message.SearchMsg;

@Service
public class GoodsServiceImp implements GoodsService{
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	@Transactional
	//@Cacheable(value = "goods", key = "#id")
	public Goods findById(int id) {
		
		System.out.println("在goodsService中");
		
		return goodsDao.findById(id);
	}
	
	@Override
	@Transactional
	public SearchMsg<Goods> searchByGoodsName(String goodsKeyword, String order, boolean asc, int page) {
		
		int elementInPage = 6;
		int startElement = (page - 1) * elementInPage;
		
		return goodsDao.searchGoodsByGoodsName(goodsKeyword, order, asc, elementInPage, startElement);
	}
	
	@Override
	@Transactional
	public SearchMsg<Goods> searchByGoodsSeller(String sellerKeyword, String order, boolean asc, int page) {
		
		int elementInPage = 6;
		int startElement = (page - 1) * elementInPage;
		
		return goodsDao.searchGoodsByGoodsSeller(sellerKeyword, order, asc, elementInPage, startElement);
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
	//@CachePut(value = "goods", key = "#result.id")
	public Goods updateGoods(Goods goods) {
		
		String usernameOfLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		goodsDao.update(usernameOfLogin, goods);
		
		return goodsDao.findById(goods.getId());
	}

	@Override
	@Transactional
	//@CacheEvict(value = "goods", key = "#id")
	public void deleteGoods(int id) {
		
		String usernameOfLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		goodsDao.delete(usernameOfLogin, id);
	}
}

package com.hyjiangd.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyjiangd.webstore.entity.Goods;

@Repository
public class GoodsDaoImp implements GoodsDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Goods> findAll() {
		
		Session session = entityManager.unwrap(Session.class);
		Query<Goods> query = session.createQuery("from Goods", Goods.class);
		List<Goods> goodsList = query.getResultList();
		
		return goodsList;
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

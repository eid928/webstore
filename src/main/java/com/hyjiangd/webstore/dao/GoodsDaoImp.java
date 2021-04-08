package com.hyjiangd.webstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.exception.NotFoundException;
import com.hyjiangd.webstore.message.SearchMsg;

@Repository
public class GoodsDaoImp implements GoodsDao{
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private SearchMsg<Goods> searchMsg;

	@Override
	public Goods findById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		Goods goods = session.get(Goods.class, id);
		System.out.println("在goodsdao中");
		
		return goods;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public SearchMsg<Goods> searchGoodsByGoodsName(String goodsKeyword, String order, boolean asc, int elementInPage, int startElement) {
		
		Session session = entityManager.unwrap(Session.class);

		long totalSearchResult = 0;
		List<Goods> goodsList = null;
		try {
			totalSearchResult = (Long) session.createCriteria(Goods.class)
									.add(Restrictions.like("name", "%" + goodsKeyword + "%"))
									.setProjection(Projections.rowCount())
									.uniqueResult();
			
			goodsList = session.createCriteria(Goods.class)
									.add(Restrictions.like("name", "%" + goodsKeyword + "%"))
									.addOrder(asc? Order.asc(order) : Order.desc(order))
									.setMaxResults(elementInPage)
									.setFirstResult(startElement)
									.list();
			
			searchMsg.setTotalSearchResult(totalSearchResult);
			searchMsg.setSearchResult(goodsList);
		} catch (HibernateException e) {
			throw new RuntimeException("此搜尋條件查無結果");
		}
		return searchMsg;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public SearchMsg<Goods> searchGoodsByGoodsSeller(String sellerKeyword, String order, boolean asc, int elementInPage, int startElement) {
		
		Session session = entityManager.unwrap(Session.class);
		
		long totalSearchResult = 0;
		List<Goods> goodsList = null;
		try {
			totalSearchResult = (Long) session.createCriteria(Goods.class)
									.add(Restrictions.like("user.username", "%" + sellerKeyword + "%"))
									.setProjection(Projections.rowCount())
									.uniqueResult();
			
			goodsList = session.createCriteria(Goods.class)
									.add(Restrictions.like("user.username", "%" + sellerKeyword + "%"))
									.addOrder(asc? Order.asc(order) : Order.desc(order))
									.setMaxResults(elementInPage)
									.setFirstResult(startElement)
									.list();
			
			searchMsg.setTotalSearchResult(totalSearchResult);
			searchMsg.setSearchResult(goodsList);
		} catch (HibernateException e) {
			throw new RuntimeException("此搜尋條件查無結果");
		}
		return searchMsg;
	}

	@Override
	public void save(String usernameOfLogin, Goods goods) {
		
		Session session = entityManager.unwrap(Session.class);
		User user = session.get(User.class, usernameOfLogin);
		goods.setUser(user);
		
		Date now = new Date();
		
		goods.setLastUpdateTime(now);
		System.out.println("即將存進hibernate session中的: " + goods.getLastUpdateTime().getClass());
		System.out.println("即將存進hibernate session中的: " + goods.getLastUpdateTime());
		session.saveOrUpdate(goods);
	}

	@Override
	public void update(String usernameOfLogin, Goods goods) {
		
		Session session = entityManager.unwrap(Session.class);
		int id = goods.getId();
		Goods dbGoods = session.get(Goods.class, id);
		
		String usernameOfDbGoods = dbGoods.getUser().getUsername();
		
		if (!usernameOfLogin.equals(usernameOfDbGoods)) {
			throw new NotFoundException("此商品不屬於您的上架商品");
		}
		// ensure that the user can only update their own goods
		
		dbGoods.setName(goods.getName());
		dbGoods.setPrice(goods.getPrice());
		dbGoods.setDescription(goods.getDescription());
		dbGoods.setInventories(goods.getInventories());
		dbGoods.setImage(goods.getImage());
		
		Date now = new Date();
		dbGoods.setLastUpdateTime(now);

		System.out.println(dbGoods.getLastUpdateTime());
		
		session.save(dbGoods);
	}

	@Override
	public void delete(String usernameOfLogin, int id) {
		
		Session session = entityManager.unwrap(Session.class);
		Goods dbGoods = session.get(Goods.class, id);
		
		String usernameOfDbGoods = dbGoods.getUser().getUsername();
		
		if (!usernameOfLogin.equals(usernameOfDbGoods)) {
			throw new NotFoundException("此商品不屬於您的上架商品");
		}
		// ensure that the user can only update their own goods
		
		session.delete(dbGoods);
	}
}

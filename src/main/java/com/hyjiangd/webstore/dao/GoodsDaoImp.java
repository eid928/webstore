package com.hyjiangd.webstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.exception.NotFoundException;

@Repository
public class GoodsDaoImp implements GoodsDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Goods findById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(Goods.class, id);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Goods> searchGoodsByGoodsName(String goodsKeyword, String order, boolean asc, int elementInPage, int startElement) {
		
		Session session = entityManager.unwrap(Session.class);
		List<Goods> goodsList = null;
		try {
			goodsList = session.createCriteria(Goods.class)
									.add(Restrictions.like("name", "%" + goodsKeyword + "%"))
									.addOrder(asc? Order.asc(order) : Order.desc(order))
									.setMaxResults(elementInPage)
									.setFirstResult(startElement)
									.list();
		} catch (HibernateException e) {
			throw new RuntimeException("此搜尋條件查無結果");
		}
		return goodsList;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Goods> searchGoodsByGoodsSeller(String sellerKeyword, String order, boolean asc, int elementInPage, int startElement) {
		
		Session session = entityManager.unwrap(Session.class);
		List<Goods> goodsList = session.createCriteria(Goods.class)
								.add(Restrictions.like("user.username", "%" + sellerKeyword + "%"))
								.addOrder(asc? Order.asc(order) : Order.desc(order))
								.setMaxResults(elementInPage)
								.setFirstResult(startElement)
								.list();
		return goodsList;
	}

	@Override
	public void save(String usernameOfLogin, Goods goods) {
		
		Session session = entityManager.unwrap(Session.class);
		User user = session.get(User.class, usernameOfLogin);
		goods.setUser(user);
		
		Date now = new Date();
		
		goods.setLastUpdateTime(now);
		System.out.println(goods.getLastUpdateTime());
		session.saveOrUpdate(goods);
	}

	@Override
	public void update(String usernameOfLogin, Goods goods) {
		
		Session session = entityManager.unwrap(Session.class);
		int id = goods.getId();
		Goods dbGoods = session.get(Goods.class, id);
		
		String usernameOfDbGoods = dbGoods.getUser().getUsername();
		
		if (!usernameOfLogin.equals(usernameOfDbGoods)) {
			throw new NotFoundException("沒有在您的上架清單中找到此商品");
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
}

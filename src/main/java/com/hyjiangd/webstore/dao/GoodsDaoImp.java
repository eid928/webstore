package com.hyjiangd.webstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

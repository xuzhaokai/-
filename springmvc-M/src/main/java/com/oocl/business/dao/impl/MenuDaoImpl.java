package com.oocl.business.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.oocl.business.model.Store;
import com.oocl.business.util.CalendarUtil;
import org.springframework.stereotype.Repository;

import com.oocl.business.dao.MenuDao;
import com.oocl.business.model.Menu;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao {
	@PersistenceContext(name = "em")
	private EntityManager em;

	public List<Menu> findStoreAll(String storeId) {

		Query query = em.createQuery("SELECT b FROM Menu b where b.status != 2 and b.store.id =:storeId");
		query.setParameter("storeId",storeId);
		List<Menu> list = query.getResultList();

		return list;
	}

	public Menu find(String id) {
		// TODO Auto-generated method stub
		Menu menu = em.find(Menu.class, id);
		return menu;
	}

	public Menu add(Menu menu,String storeId) {
		// TODO Auto-generated method stub
		Store store = em.find(Store.class,storeId);
		menu.setStore(store);
		menu.setCreatedAt(CalendarUtil.getCurrentDateTime());
		menu.setUpdatedAt(CalendarUtil.getCurrentDateTime());
		em.persist(menu);
		return menu;
	}

	public Menu delete(String id) {
		// TODO Auto-generated method stub
		Menu menu = em.find(Menu.class, id);
		menu.setStatus(2);
		menu.setUpdatedAt(CalendarUtil.getCurrentDateTime());
		em.persist(menu);
		return menu;
	}

	public Menu update(Menu menu,String id) {
		// TODO Auto-generated method stub
		Menu m = em.find(Menu.class, id);
		m.setMenu(menu);
		m.setUpdatedAt(CalendarUtil.getCurrentDateTime());
		em.persist(m);
		return m;
	}
}

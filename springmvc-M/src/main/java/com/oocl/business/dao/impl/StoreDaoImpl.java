package com.oocl.business.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.oocl.business.util.CalendarUtil;
import org.springframework.stereotype.Repository;

import com.oocl.business.dao.StoreDao;
import com.oocl.business.model.Business;
import com.oocl.business.model.Store;

import java.util.List;

@Repository("storeDao")
public class StoreDaoImpl implements StoreDao {
	
	@PersistenceContext(name = "em")
	private EntityManager em;

	public Store add(Store store) {
		em.persist(store);
		return store;
	}

	public Store find(String storeNumber) {
		Query query = em.createQuery("SELECT b FROM Store b where b.merchantStoreNumber =:merchantStoreNumber");
		query.setParameter("merchantStoreNumber",storeNumber);
		List<Store> list = query.getResultList();
		if(!list.isEmpty()) {
			return list.get(0);
		}else {
			return null;
		}
	}

	public Store update(Store store) {
		// TODO Auto-generated method stub
		Store store2 = em.find(Store.class, store.getId());
		store2.setStore(store);
		store2.setUpdatedAt(CalendarUtil.getCurrentDateTime());
		em.persist(store2);
		return store2;
	}

	public Store listOne(String id) {
		Store store = em.find(Store.class, id);
		System.out.println(store);
		return store;
	}
}

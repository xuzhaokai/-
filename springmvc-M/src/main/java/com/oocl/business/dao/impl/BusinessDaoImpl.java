package com.oocl.business.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.business.dao.BusinessDao;
import com.oocl.business.model.Business;
import com.oocl.business.util.CalendarUtil;

@Repository("businessDao")
public class BusinessDaoImpl implements BusinessDao {
	
	 @PersistenceContext(name = "em")
	    private EntityManager em;

	    public List<Business> findAll() {
	        Query query = em.createQuery("SELECT b FROM Business b");
	        List<Business> result = query.getResultList();
	        return result;
	    }

	    public Business persist(Business business) {

	        em.persist(business);
	        if(business.getId()!=""){
	            return business;
	        }else{
	            return null;
	        }

	    }

	    public int getStatusByAccount(String account) {
	        return 0;
	    }

	    public Business updateStatus(Business business) {
	        Business businessIn = findByAccount(business.getAccount());
	        businessIn.setStatus(business.getStatus());

	        em.persist(businessIn);

	        return businessIn;

	    }

	    public boolean updateLastLoginTime(String account) {
	        Business business = findByAccount(account);
	        business.setLastLoginAt(CalendarUtil.getCurrentDateTime());
			em.persist(business);
	        return true;

	    }

	    public Business findByAccount(String account) {
	        Query query = em.createQuery("SELECT b FROM Business b where b.account =:account");
	        query.setParameter("account",account);
	        List<Business> list = query.getResultList();
	        if(!list.isEmpty()) {
	        	//Business result = (Business)query.getSingleResult();
		        return list.get(0);
	        }else {
	        	return null;
	        }
	        
	    }
}

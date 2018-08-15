package com.oocl.business.dao;

import com.oocl.business.model.Business;

public interface BusinessDao {

//	int getBussinessStatus(String account);
//
//	boolean updateStatus(Business business);
	 Business persist(Business business);
	 int  getStatusByAccount(String account);
	 Business updateStatus(Business business);
	 boolean updateLastLoginTime(String account);
	 Business findByAccount(String account);

}

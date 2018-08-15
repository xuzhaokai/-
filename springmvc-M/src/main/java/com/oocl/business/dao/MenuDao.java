package com.oocl.business.dao;

import java.util.List;

import com.oocl.business.model.Menu;



public interface MenuDao {
	public List<Menu> findStoreAll(String storeId);
	public Menu find(String id);
	public Menu add(Menu menu,String storeId);
	public Menu delete(String id);
	public Menu update(Menu menu,String id);

}

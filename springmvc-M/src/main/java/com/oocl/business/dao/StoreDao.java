package com.oocl.business.dao;

import com.oocl.business.model.Business;
import com.oocl.business.model.Store;

public interface StoreDao {

	Store add(Store store);

	Store find(String storeNumber);
	
	Store update(Store store);
	
	Store listOne(String id);

}

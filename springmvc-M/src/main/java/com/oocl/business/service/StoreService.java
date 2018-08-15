package com.oocl.business.service;

import com.oocl.business.model.view.MerchantAuditView;
import com.oocl.business.model.RespondResult;
import com.oocl.business.model.Store;


public interface StoreService {

	
	/**
	*@Description 提交注册店铺和更新提交共用同个方法
	*@param
	*@return  
	*/
	RespondResult registerStore(MerchantAuditView regMsg);


	/**
	*@Description  查找商店
	*@param
	*@return
	*/
	Store find(Store store);

	/**
	 *@Description  更新商店
	 *@param
	 *@return
	 */
	RespondResult update(Store store);

	/**
	 *@Description  查找某个商店
	 *@param
	 *@return
	 */
	RespondResult listOne(String id);

}

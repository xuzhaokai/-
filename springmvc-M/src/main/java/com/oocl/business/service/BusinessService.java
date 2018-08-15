package com.oocl.business.service;

import com.oocl.business.model.Business;
import com.oocl.business.model.RespondResult;

public interface BusinessService {

	/**
	*@Description 发送JMS请求到
	*@param
	*@return
	*/
	 RespondResult sendToAdminAndCreatStore(String account);


	 /**
	 *@Description 注册商家用户
	 *@param
	 *@return
	 */
	 RespondResult register(Business business);
	 
	 
	 /**
	 *@Description  登录
	 *@param
	 *@return  
	 */
	 RespondResult  login(Business business);


	 /**
	 *@Description  查找投诉信息
	 *@param
	 *@return
	 */
	 RespondResult findComplaintFormA(String storeId);
}

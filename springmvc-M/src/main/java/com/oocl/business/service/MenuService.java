package com.oocl.business.service;

import com.oocl.business.model.Menu;
import com.oocl.business.model.RespondResult;



public interface MenuService {
	
	/**
	*@Description 添加菜单
	*@param
	*@return  
	*/
	public RespondResult add(Menu menu,String storeId);

	/**
	*@Description 删除订单
	*@param
	*@return
	*/
	public RespondResult delete(String id);

	/**
	*@Description 根据storeId查找所有菜单
	*@param
	*@return
	*/
	public RespondResult findAll(String storeId);

	/**
	*@Description 查找某个菜单
	*@param
	*@return
	*/
	public RespondResult find(String id);
	
	/**
	*@Description 更新菜单
	*@param
	*@return  
	*/
	public RespondResult update(Menu menu,String id);

}

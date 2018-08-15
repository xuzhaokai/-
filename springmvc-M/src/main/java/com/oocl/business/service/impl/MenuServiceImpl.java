package com.oocl.business.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oocl.business.dao.MenuDao;
import com.oocl.business.model.Menu;
import com.oocl.business.model.RespondResult;
import com.oocl.business.service.MenuService;
import org.springframework.transaction.annotation.Transactional;

@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {
	
	@Resource(name = "menuDao")
	private MenuDao menuDao;

	public RespondResult add(Menu menu,String storeId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		Object m = menuDao.add(menu,storeId);
		map.put("menu", m);
		RespondResult respondResult = new RespondResult(200,"",map);

		return respondResult;
	}

	public RespondResult findAll(String storeId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		Object m = menuDao.findStoreAll(storeId);
		map.put("menus", m);
		RespondResult respondResult = new RespondResult(200,"",map);
		return respondResult;
	}

	public RespondResult update(Menu menu,String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		Object m = menuDao.update(menu,id);
		map.put("menu", m);
		RespondResult respondResult = new RespondResult(200,"",map);
		return respondResult;
	}

	public RespondResult delete(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		Object m = menuDao.delete(id);
		map.put("menu", m);
		RespondResult respondResult = new RespondResult(200,"",map);
		return respondResult;
	}

	public RespondResult find(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		Object m = menuDao.find(id);
		map.put("menu", m);
		RespondResult respondResult = new RespondResult(200,"",map);
		return respondResult;
	}
}

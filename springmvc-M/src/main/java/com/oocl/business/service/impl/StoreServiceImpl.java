package com.oocl.business.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Queue;

import com.oocl.business.enums.JmsTypeEnum;
import com.oocl.business.enums.ResultStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oocl.business.dao.BusinessDao;
import com.oocl.business.dao.StoreDao;
import com.oocl.business.enums.StatusEnum;
import com.oocl.business.model.Business;
import com.oocl.business.model.view.MerchantAuditView;
import com.oocl.business.model.RespondResult;
import com.oocl.business.model.Store;
import com.oocl.business.service.StoreService;
import com.oocl.business.util.JmsUtil;
import com.oocl.business.util.JsonUtil;
import com.oocl.business.util.PropertiesUtil;


@Service("storeService")
@Transactional
public class StoreServiceImpl implements StoreService {

	@Resource(name = "storeDao")
	private StoreDao storeDao;
	
	@Resource(name = "businessDao")
	private BusinessDao businessDao;

	@Autowired
	private JmsUtil jmsUtil;

	public RespondResult registerStore(MerchantAuditView regMsg) {

		RespondResult resp = new RespondResult();

		String json = JsonUtil.toJSon(regMsg);

		// 3调用JMS 发送json
		jmsUtil.send(json, JmsTypeEnum.AUDIT.getType());

		// 发送成功后 进行修改用户表的状态->已提交注册店铺
		Business business = new Business();
		business.setAccount(regMsg.getAccount());
		business.setStatus(StatusEnum.BUSINESS_STATUS_SUCCESS.getValue());
		businessDao.updateStatus(business);
		resp.setMsg(ResultStatusEnum.OK.getMsg());
		resp.setStatusCode(ResultStatusEnum.OK.getCode());

		return resp;

	}

	public Store find(Store store) {
		// TODO Auto-generated method stub
		return null;
	}

	public RespondResult update(Store store) {
		// TODO Auto-generated method stub
		RespondResult respondResult = new RespondResult();
		Map<String, Object> map = new HashMap();
		Store s = storeDao.update(store);
		map.put("store", s);
		respondResult.setDatas(map);

//		RespondResult respondResult = new RespondResult(200,"",map);
		return respondResult;

	}

	public RespondResult listOne(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		Object s = storeDao.listOne(id);
		map.put("store", s);
		RespondResult respondResult = new RespondResult(200,"",map);
		return respondResult;
	}


}

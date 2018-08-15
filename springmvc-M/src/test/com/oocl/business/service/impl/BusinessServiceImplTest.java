package com.oocl.business.service.impl;

import static org.junit.Assert.*;

import com.oocl.business.model.Business;
import com.oocl.business.service.BusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BusinessServiceImplTest {

	@Resource(name="businessService")
	private BusinessService service;
	@Test
	public void testRegister() {
		Business business = new Business();
		business.setAccount("manti1");
		business.setPassword("123456");
		assertNotNull(service.register(business));
	}

	@Test
	public void testLogin() {
		Business business = new Business();
		business.setAccount("manti");
		business.setPassword("123456");
		Map<String,Object> map= service.login(business).getDatas();
		System.out.println(map);
 		assertNotNull(map.get("business"));
		
	}

}

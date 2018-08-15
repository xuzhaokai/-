package com.oocl.business.util;

import com.oocl.business.compontment.JerseyPoolingClientFactoryBean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


public class HttpUtil {


	public static String sendHttp(JerseyPoolingClientFactoryBean jerseyBean,String url){
		Response resp = null;
		try {
			Client client =jerseyBean.getObject();
			WebTarget target = client.target(url);
			resp = target.request().get();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.readEntity(String.class);
	}


}

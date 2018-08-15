package com.oocl.business.compontment;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Component("jerseyClient")
public class JerseyClient {

    @Resource(name="jerseyPoolingClient")
    private Client client;

    public String sendHttp(String url){
        Response resp = null;
        try {
            WebTarget target = client.target(url);
            resp = target.request().get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp.readEntity(String.class);
    }

}

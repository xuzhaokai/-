package com.oocl.business.handler;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BusinessSocket extends TextWebSocketHandler {
    private static final HashMap<String, WebSocketSession> users;//这个会出现性能问题，最好用Map来存储，key用userid
    private static final ArrayList<MyMap> reflect;//这个会出现性能问题，最好用Map来存储，key用userid
    private static Logger logger = Logger.getLogger(BusinessSocket.class);

    class MyMap {
        String userId;
        String sessionId;
    }

    static {
        users = new HashMap<String, WebSocketSession>();
        reflect = new ArrayList<MyMap>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("open...");
        System.out.println("put ：" + (String) session.getAttributes().get("sessionId"));
        users.put((String) session.getAttributes().get("sessionId"), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Closed");

        String sessionId = (String) session.getAttributes().get("sessionId");
        Iterator<MyMap> iterator = reflect.iterator();
        while(iterator.hasNext()){
            MyMap map = iterator.next();
            if(map.sessionId.equals(sessionId)){
                System.out.println("delete ：" + map.userId);
                iterator.remove();
            }
        }
//
//        for(MyMap m:reflect){
//            if(m.sessionId.equals(sessionId)){
//                System.out.println("delete ：" + m.userId);
//                reflect.remove(m);
//            }
//        }
        System.out.println("remove ：" + sessionId);
        users.remove(sessionId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("onMessage：" + session.getId());
        System.out.println("onMessage：" + message.getPayload());
        String msg = message.getPayload();
        String[] msgList = msg.split("-");
        if (msgList.length == 2) {
            if (msgList[0].equals("add")) {
                System.out.println("add ：" + msgList[1]);
                MyMap map = new MyMap();
                map.sessionId = (String) session.getAttributes().get("HTTP.SESSION.ID");
                map.userId = msgList[1];
                reflect.add(map);
            }
//            if (msgList[0].equals("del")) {
//                System.out.println("del ：" + msgList[1]);
//                MyMap map = new MyMap();
//                map.sessionId = (String) session.getAttributes().get("HTTP.SESSION.ID");
//                map.userId = msgList[1];
//
//            }
        }
    }

    public static void sendMessageToUsers(String msg, String userId) {
        TextMessage message = new TextMessage(msg.getBytes());
        String sessionId = "";
        Iterator<MyMap> iterator = reflect.iterator();
        while(iterator.hasNext()){
            MyMap map = iterator.next();
            if(map.userId.equals(userId)){
                sessionId = map.sessionId;
            }
        }
        WebSocketSession user = users.get(sessionId);
        try {
            if (user.isOpen()) {
                user.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
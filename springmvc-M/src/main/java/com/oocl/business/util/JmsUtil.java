package com.oocl.business.util;

import javax.annotation.Resource;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JmsUtil {

	@Resource(name = "jmsTemplate")
	private  JmsTemplate jmsTemplate;

	@Resource(name = "queueDestination")
	private  Queue queueDestination;

	 public  void send(final String msg,final String type) {
		jmsTemplate.send(queueDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(msg);
				message.setJMSType(type);
				return message;
			}
		});
	}

}

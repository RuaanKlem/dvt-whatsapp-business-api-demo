package com.dvt.service;

import com.dvt.model.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${dvt.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${dvt.rabbitmq.routingkey}")
	private String routingkey;	

	public RabbitMQSender(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public void send(Message message) {
		this.amqpTemplate.convertAndSend(exchange, routingkey, message);
	}
}
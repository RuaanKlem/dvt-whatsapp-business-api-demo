package com.dvt.controller;

import com.dvt.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvt.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/dvt-rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message,@RequestParam("code") String code) {

		Message mes = new Message();
		mes.setMessage(message);
		mes.setCode(code);

		rabbitMQSender.send(mes);

		return "Message sent to the RabbitMQ DVT Successfully";
	}

}


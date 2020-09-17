package com.dvt.service;

import com.dvt.model.ContactList;
import com.dvt.model.Message;
import com.dvt.model.MessageList;
import com.dvt.model.UserLists;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${dvt.rabbitmq.queue}")
    public void receivedMessage(String message) {
        String messageCode = sendMessage(message);

        System.out.println("Received Message From RabbitMQ: " + message);
        System.out.println("Sent message to AutoChat with response: " + messageCode);
    }

    private final String baseUrl = "https://wbapi.autochat.io/e5bbf3f9-90f0-4d81-aa52-e654803d48aa/v1";

    public String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("YWRtaW46cGFzc3dvcmQ=");
        String input = "{\"new_password\":\"password\"}";

        HttpEntity<String> entity = new HttpEntity<>(input, headers);

        String token = restTemplate.postForObject(baseUrl + "/users/login", entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        UserLists users = null;
        try {
            users = mapper.readValue(token, UserLists.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert users != null;
        return users.getUsers().get(0).getToken();
    }

    public String getWaId() {
        RestTemplate restTemplate = new RestTemplate();
        String token = getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        String input = "{\"blocking\": \"wait\", \"contacts\": [ \"0737224748\" ]}";

        HttpEntity<String> entity = new HttpEntity<>(input, headers);

        String contacts = restTemplate.postForObject(baseUrl + "/contacts", entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        ContactList contactList = null;
        try {
            contactList = mapper.readValue(contacts, ContactList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert contactList != null;
        return contactList.getContacts().get(0).getWa_id();
    }

    public String sendMessage(String message) {
        Gson g = new Gson();
        Message message1 = g.fromJson(message, Message.class);

        RestTemplate restTemplate = new RestTemplate();
        String waId = getWaId();
        String token = getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        String input = "{\"recipient_type\": \"individual\", \"to\": \"" + waId + "\", \"text\": { \"body\": \""+message1.getMessage()+"\" } }";

        HttpEntity<String> entity = new HttpEntity<>(input, headers);

        String messages = restTemplate.postForObject(baseUrl + "/messages", entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        MessageList messageList = null;
        try {
            messageList = mapper.readValue(messages, MessageList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert messageList != null;
        return messageList.getMessages().get(0).getId();
    }

}
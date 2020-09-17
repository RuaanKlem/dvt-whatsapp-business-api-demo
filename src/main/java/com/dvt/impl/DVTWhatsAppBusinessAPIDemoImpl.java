package com.dvt.impl;

import com.dvt.model.Contacts;
import com.dvt.model.Messages;
import com.dvt.model.Users;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DVTWhatsAppBusinessAPIDemoImpl {
    private final String baseUrl = "https://wbapi.autochat.io/e5bbf3f9-90f0-4d81-aa52-e654803d48aa/v1";

    public String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("YWRtaW46cGFzc3dvcmQ=");
        String input = "{\"new_password\":\"password\"}";

        HttpEntity<String> entity = new HttpEntity<>(input, headers);

        Users users = restTemplate.postForObject(baseUrl + "/users/login", entity, Users.class);

        assert users != null;
        return users.getToken();
    }

    public String getWaId() {
        RestTemplate restTemplate = new RestTemplate();
        String token = getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        String input = "{\"blocking\": \"wait\", \"contacts\": [ \"0737224748\" ]}";

        HttpEntity<String> entity = new HttpEntity<>(input, headers);

        ResponseEntity<Contacts> contactsResponseEntity = restTemplate.exchange(baseUrl + "/contacts", HttpMethod.POST, entity, Contacts.class);
        Contacts body = contactsResponseEntity.getBody();

        assert body != null;
        return body.getWa_id();
    }

    public String getMessageId(String message) {
        RestTemplate restTemplate = new RestTemplate();
        String waId = getWaId();
        String token = getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        String input = "{\"recipient_type\": \"individual\", \"to\": \"" + waId + "\", \"text\": { \"body\": \""+message+"\" } }";

        HttpEntity<String> entity = new HttpEntity<>(input, headers);

        ResponseEntity<Messages> messagesResponseEntity = restTemplate.exchange(baseUrl + "/messages", HttpMethod.POST, entity, Messages.class);
        Messages body = messagesResponseEntity.getBody();

        assert body != null;
        return body.getId();
    }
}

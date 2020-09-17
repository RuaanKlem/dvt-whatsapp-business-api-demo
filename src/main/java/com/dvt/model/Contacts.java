package com.dvt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contacts {
    private String input;
    private String status;
    private String wa_id;

    public Contacts() {
    }

    public Contacts(String input, String status, String wa_id) {
        this.input = input;
        this.status = status;
        this.wa_id = wa_id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }
}

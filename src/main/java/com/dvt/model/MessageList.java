package com.dvt.model;

import java.util.List;

public class MessageList {
    private List<Messages> messages;

    public MessageList() {
    }

    public MessageList(List<Messages> messages) {
        this.messages = messages;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }
}

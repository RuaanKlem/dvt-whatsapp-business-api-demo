package com.dvt.model;

import java.util.List;

public class MessageList {
    private List<MessageResponse> messages;

    public MessageList() {
    }

    public MessageList(List<MessageResponse> messages) {
        this.messages = messages;
    }

    public List<MessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResponse> messages) {
        this.messages = messages;
    }
}

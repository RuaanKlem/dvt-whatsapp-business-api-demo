package com.dvt.model;

import java.util.List;

public class ContactList {
    private List<Contact> contacts;

    public ContactList() {
    }

    public ContactList(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}

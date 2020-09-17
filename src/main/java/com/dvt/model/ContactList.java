package com.dvt.model;

import java.util.List;

public class ContactList {
    private List<Contacts> contacts;

    public ContactList() {
    }

    public ContactList(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }
}

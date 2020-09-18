package com.dvt.model;

import java.util.List;

public class UserLists {
    private List<User> users;

    public UserLists() {
    }

    public UserLists(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

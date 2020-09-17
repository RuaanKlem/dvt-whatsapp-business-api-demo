package com.dvt.model;

import java.util.List;

public class UserLists {
    private List<Users> users;

    public UserLists() {
    }

    public UserLists(List<Users> users) {
        this.users = users;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}

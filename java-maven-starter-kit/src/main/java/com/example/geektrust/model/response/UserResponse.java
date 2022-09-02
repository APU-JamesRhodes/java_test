package com.example.geektrust.model.response;

import com.example.geektrust.model.User;

public class UserResponse implements Response {
    private User user;

    public UserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

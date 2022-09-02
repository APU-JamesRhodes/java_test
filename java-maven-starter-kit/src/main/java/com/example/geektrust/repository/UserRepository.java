package com.example.geektrust.repository;

import com.example.geektrust.model.User;
import com.example.geektrust.model.response.ErrorResponse;
import com.example.geektrust.model.response.Response;
import com.example.geektrust.model.response.UserResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private HashMap<User, Integer> map = new HashMap<>();

    public Response getByUserName(String name) {
        for (Map.Entry<User, Integer> user : map.entrySet()) {
            if (user.getKey().getUserName().equals(name)) {
                return new UserResponse(user.getKey());
            }
        }
        return new ErrorResponse("FAILURE");
    }

    public Response save(User user) {
        if (map.size() > 3) {
            return new ErrorResponse("FAILURE");
        }
        this.map.put(user, 1);
        return new UserResponse(user);
    }

    public int getSize() {
        return map.size();
    }

    public boolean find(String name) {
        for (Map.Entry<User, Integer> user : map.entrySet()) {
            if (user.getKey().getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();
        for (Map.Entry<User, Integer> user : map.entrySet()) {
            users.add(user.getKey());
        }
        return users;
    }
}

package dev.akursekova.app.repository;

import dev.akursekova.app.subjects.User;

import java.util.HashMap;

public class UserRepository {
    HashMap<String, User> users = new HashMap<>();

    public void add(User user) {
        if (user.getName().equals("")){
            throw new IllegalArgumentException("user name cannot be empty");
        }
        users.put(user.getName(), user);
    }

    public boolean exists(String userName){
        return users.containsKey(userName);
    }

}

package dev.akursekova.app.userService;

import java.util.HashMap;

public class UserRepository {
    HashMap<String, User> users = new HashMap<>();

    public void add(User user) {
        users.put(user.getName(), user);
    }

    public boolean exists(String userName){
        return users.containsKey(userName);
    }

}

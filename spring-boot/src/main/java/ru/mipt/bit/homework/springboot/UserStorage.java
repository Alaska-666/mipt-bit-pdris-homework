package ru.mipt.bit.homework.springboot;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserStorage {
    private final Map<String, User> users;

    public UserStorage() {
        users = new HashMap<>();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void add(User user) {
        users.put(user.getUsername(), user);
    }

    public void add(String username, String password) {
        users.put(username, new User(username, password));
    }

    public boolean isUserExist(User user) {
        return users.containsKey(user.getUsername());
    }

    public boolean isUserExist(String username) {
        return users.containsKey(username);
    }

    public boolean isPasswordCorrect(String username, String password) {
        if (!isUserExist(username)) {
            return false;
        }
        return users.get(username).getPassword().equals(password);
    }

    public boolean isPasswordCorrect(User user) {
        if (!isUserExist(user)) {
            return false;
        }
        return users.get(user.getUsername()).equals(user);
    }
}

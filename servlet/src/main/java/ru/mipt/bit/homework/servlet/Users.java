package ru.mipt.bit.homework.servlet;

import java.util.HashMap;
import java.util.Map;

public class Users {
    private static Map<String, String> users = new HashMap<String, String>();;

    public static void add(String username, String password) {
        users.put(username, password);
    }

    public static boolean isUserExist(String username) {
        return users.containsKey(username);
    }

    public static boolean usernameIsValid(String username) {
        return (username != null) && username.matches("[A-Za-z0-9_]+");
    }

    public static boolean isPasswordCorrect(String username, String password) {
        if (!isUserExist(username)) {
            return false;
        }
        return users.get(username).equals(password);
    }
}

package ru.mipt.bit.homework.springboot;

import java.util.ArrayList;
import java.util.List;

public class LogStorage {
    private List<String> logs;

    public LogStorage() {
        logs = new ArrayList<>();
    }

    public void add(User user, String action, String status) {
        logs.add(String.format("Username: %s, action: %s, status: %s", user.getUsername(), action, status));
    }

    public List<String> getLogs() {
        return logs;
    }
}

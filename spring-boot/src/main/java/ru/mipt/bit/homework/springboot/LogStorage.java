package ru.mipt.bit.homework.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LogStorage {
    private List<String> logs;

    public LogStorage() {
        logs = new ArrayList<>();
    }

    public void add(User user, String action, String status) {
        logs.add(String.format("Username: %s, action: %s, status: %s", user.getUsername(), action, status));
    }

    @Bean
    public List<String> getLogs() {
        return logs;
    }
}

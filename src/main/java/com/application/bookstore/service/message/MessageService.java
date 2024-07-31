package com.application.bookstore.service.message;

public interface MessageService {
    void sendMessage(String message);

    void handleMessage(String message);
}


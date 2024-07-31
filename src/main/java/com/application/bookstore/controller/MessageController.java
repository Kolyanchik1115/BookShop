package com.application.bookstore.controller;

import com.application.bookstore.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send")
public class MessageController {
    private final MessageService messageService;

    @PostMapping()
    public void sendMessage(@RequestParam String message) {
        messageService.sendMessage(message);
    }
}

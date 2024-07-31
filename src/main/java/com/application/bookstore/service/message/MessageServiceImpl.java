package com.application.bookstore.service.message;

import com.application.bookstore.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY, message);
        System.out.println("Message sent: " + message);
    }

    @Override
    public void handleMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}

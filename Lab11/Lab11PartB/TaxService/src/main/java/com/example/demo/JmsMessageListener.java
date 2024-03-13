package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JmsMessageListener {

    @JmsListener(destination = "testQueue")
    public void receiveMessage(final String message) {
        System.out.println("JMS receiver received message:" + message);
     }
}


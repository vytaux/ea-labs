package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class StopWatch {

    private long startTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public long getElapsedTimeMs() {
        return (System.nanoTime() - startTime) / 1000000;
    }
}
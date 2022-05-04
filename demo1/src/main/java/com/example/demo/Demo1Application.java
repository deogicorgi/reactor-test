package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) throws InterruptedException {

        FluxTest fluxTest = new FluxTest();
        fluxTest.subscribe();

        SpringApplication.run(Demo1Application.class, args);
    }



}

package com.karma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServiceMain {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServiceMain.class, args);
        System.out.println("Contains: Service -> " + context.containsBeanDefinition("karmaService"));
        System.out.println("Contains: Controller -> " + context.containsBeanDefinition("karmaController"));
        System.out.println("Contains: Repository -> " + context.containsBeanDefinition("karmaRepository"));
    }

}
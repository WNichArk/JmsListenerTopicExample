package com.Will;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.jms.Topic;


@Slf4j
@SpringBootApplication
public class App {

    public static void main( String[] args ) {
        SpringApplication.run(App.class);
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("assessment3-completed");
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

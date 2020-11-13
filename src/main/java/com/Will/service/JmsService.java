package com.Will.service;

import com.Will.dto.CustomerDTO;
import com.Will.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import javax.jms.*;

@EnableJms
@Slf4j
@Service
public class JmsService {
    private CheckService checkService;

    @Autowired
    private Topic topic;

    @Autowired
    JmsTemplate template;

    public JmsService(CheckService s) {
        this.checkService = s;

    }

    @JmsListener(destination = "assessment3-positions")
    public void listen(String message) throws Exception {

        ObjectMapper messageObj = new ObjectMapper();
        MessageDTO messageDTO = messageObj.readValue(message, MessageDTO.class);
        log.info(message);
        String customerReturn = checkService.getCustomerByOrderId(messageDTO.getOrderId());
        ObjectMapper customerObj = new ObjectMapper();

        CustomerDTO customerDTO = customerObj.readValue(customerReturn, CustomerDTO.class);
        if(checkService.arrived(customerDTO, messageDTO)){
            sendAlert(messageDTO, customerDTO);
        }
    }
    public void sendAlert(MessageDTO messageDTO, CustomerDTO customerDTO){
        template.convertAndSend(topic, "Will N - Order#" + messageDTO.getOrderId() + " for Customer: " + customerDTO.getName() + " complete.");
    }
}

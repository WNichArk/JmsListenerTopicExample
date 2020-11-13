package com.Will.service;

import com.Will.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ApiImpl {
    private RestTemplate restTemplate;


    public ApiImpl(RestTemplate r){
        this.restTemplate = r;

    }

    public String getCustomerByOrderId(int orderId){
        return restTemplate.getForObject("http://localhost:8080/assess3/api/findcustbyorderid/" + orderId, String.class);
    }
    public void completeOrder(OrderDTO orderDTO){
       restTemplate.postForObject("http://localhost:8080/assess3/api/", orderDTO, String.class);
    }

}

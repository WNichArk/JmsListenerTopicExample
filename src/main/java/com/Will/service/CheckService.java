package com.Will.service;

import com.Will.dto.CustomerDTO;
import com.Will.dto.MessageDTO;
import com.Will.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckService {
    private ApiImpl apiService;

    @Autowired
    public CheckService(ApiImpl a) {
        this.apiService = a;
    }
    public boolean arrived (CustomerDTO c, MessageDTO m){
        if(c.getLat() == m.getLat() && c.getLon() == m.getLon()){
            OrderDTO orderDTO = new OrderDTO(m.getOrderId(), c.getId(), 1);
            log.info("Order " + orderDTO.getId() + " for " + c.getName() + " has arrived.");
            apiService.completeOrder(orderDTO);
            return true;
        }
        else{
            return false;
        }
    }
    public String getCustomerByOrderId(int orderId){
        return apiService.getCustomerByOrderId(orderId);
    }
}

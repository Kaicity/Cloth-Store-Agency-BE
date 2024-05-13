package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ExportingBillDto;
import com.example.ctapi.dtos.response.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    @Autowired
    private final SimpMessagingTemplate messagingTemplate;
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    //Wed socket truyen du lieu tu client den admin
    @MessageMapping("/BILL_REAL_TIME/{idSocket}")
    @SendTo("/topic/{idSocket}")
    public SocketMessage billRealTime(@DestinationVariable String idSocket, SocketMessage<ExportingBillDto> message) {
        System.out.println(idSocket + "---" + message.getIdSocket() + "---" + message.getMessage());
        //messagingTemplate.convertAndSend("/topic/" + idSocket, new SocketMessage(message.getIdSocket(), "truyen di dc r be 3 ??? cc j day"));
        return new SocketMessage(message.getIdSocket(), message.getMessage(), new ExportingBillDto());
    }
}

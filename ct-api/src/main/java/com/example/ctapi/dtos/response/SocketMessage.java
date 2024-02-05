package com.example.ctapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocketMessage<T> {
    String idSocket;
    String message;
    private T data;
}

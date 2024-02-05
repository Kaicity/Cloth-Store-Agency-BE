package com.example.ctcoremodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseModel<T> {
    List<String> message;
    int status;
    T result;
}

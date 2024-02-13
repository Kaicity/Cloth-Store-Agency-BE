package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentTransactionDto {
    private String id;
    private PaymentDto payment;
    private int quantity;
    private Double price;
    private Double amount;

    public PaymentTransactionDto() {
        this.id = CreateRandomID.generatingUID();
    }
}

package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReceiptTransactionDto {
    private String id;
    private ReceiptDto receipt;
    private int quantity;
    private Double price;
    private Double amount;

    public ReceiptTransactionDto() {
        this.id = CreateRandomID.generatingUID();
    }
}

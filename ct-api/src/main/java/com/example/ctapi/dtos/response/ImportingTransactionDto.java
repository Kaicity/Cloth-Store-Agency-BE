package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import com.example.ctcoremodel.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImportingTransactionDto {
    private String id;
    private ImportingDto importing;
    private ProductModel product;
    private Double price;
    private int quantity;
    private Double amount;

    public ImportingTransactionDto() {
        this.id = CreateRandomID.generatingUID();
    }
}

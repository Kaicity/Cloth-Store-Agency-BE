package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import com.example.ctcoremodel.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ExportingReturnTransactionDto {
    private String id;
    private ExportingReturnBillDto exportingReturnBill;
    private ProductModel product;
    private Double price;
    private int quantity;
    private Double amount;

    public ExportingReturnTransactionDto() {
        this.id = CreateRandomID.generatingUID();
    }
}

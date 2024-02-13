package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import com.example.ctcoremodel.ColorsModel;
import com.example.ctcoremodel.ProductModel;
import com.example.ctcoremodel.SizesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ExportingBillTransactionDto {
    private String id;
    private ExportingBillDto bill;
    private int quantity;
    private Double amount;
    private ProductModel product;
    private ColorsModel color;
    private SizesModel size;


    public ExportingBillTransactionDto(){
        this.id = CreateRandomID.generatingUID();
    }
}
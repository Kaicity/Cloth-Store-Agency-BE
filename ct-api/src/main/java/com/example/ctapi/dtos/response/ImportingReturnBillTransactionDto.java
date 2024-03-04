package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import com.example.ctcoremodel.ColorsModel;
import com.example.ctcoremodel.ProductModel;
import com.example.ctcoremodel.SizesModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImportingReturnBillTransactionDto {
    private String id;
    private ImportingReturnBIllDto bill;
    private int quantity;
    private Double amount;
    private ProductModel product;
    private ColorsModel color;
    private SizesModel size;

    public ImportingReturnBillTransactionDto(){
        this.id = CreateRandomID.generatingUID();
    }
}

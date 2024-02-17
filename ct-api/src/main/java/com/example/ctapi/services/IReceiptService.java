package com.example.ctapi.services;

import com.example.ctapi.dtos.response.PaymentFullDto;
import com.example.ctapi.dtos.response.ReceiptFullDto;
import com.example.ctapi.dtos.response.ReceiptSearchDto;

public interface IReceiptService {
    void createReceipt(ReceiptFullDto receiptFull);

    void updateReceipt(ReceiptFullDto receiptFull);

    void deleteReceiptFullByid(String id);

    ReceiptSearchDto getAllReceiptFull();

    ReceiptFullDto getReceiptById(String id);
}

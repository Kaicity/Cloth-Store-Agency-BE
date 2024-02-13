package com.example.ctapi.services;

import com.example.ctapi.dtos.response.TypePaymentReceiptDto;
import com.example.ctcommon.enums.TypePaymentReceipt;

import java.util.List;

public interface ITypePaymentReceiptService {
    void addTypePaymentReceipt(TypePaymentReceiptDto typePaymentReceiptDto);

    List<TypePaymentReceiptDto> getAllPayments(TypePaymentReceipt typePaymentReceipt);

    List<TypePaymentReceiptDto> getAllReceipt(TypePaymentReceipt typePaymentReceipt);
}

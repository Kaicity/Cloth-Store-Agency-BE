package com.example.ctapi.services;

import com.example.ctapi.dtos.response.PaymentFullDto;
import com.example.ctapi.dtos.response.PaymentSearchDto;

public interface IPaymentService {
    void createPayment(PaymentFullDto paymentFull);

    void updatePayment(PaymentFullDto paymentFull);

    void deletePaymentFullByid(String id);

    PaymentSearchDto getAllPaymentFull();

    PaymentFullDto getPaymentById(String id);
}

package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.request.BaseSearchDto;

import java.util.List;

public class PaymentSearchDto extends BaseSearchDto<List<PaymentFullDto>> {
    String idCompany;
    String status;
}

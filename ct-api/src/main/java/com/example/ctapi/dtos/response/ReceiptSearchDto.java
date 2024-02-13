package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.request.BaseSearchDto;

import java.util.List;

public class ReceiptSearchDto extends BaseSearchDto<List<ReceiptFullDto>> {
    String idCompany;
    String status;
}

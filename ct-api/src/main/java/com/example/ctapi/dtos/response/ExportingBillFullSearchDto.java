package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.request.BaseSearchDto;

import java.util.List;

public class ExportingBillFullSearchDto extends BaseSearchDto<List<ExportingBillFullDto>> {
    String idCompany;
    String status;
    String priceMin;
    String code;
    String name;
    String rangePrice;

    // {"priceMin":"10-200000"
    // "code":"12313123213123"
    // "name":"ronaldo"}
}

package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.request.BaseSearchDto;

import java.util.List;

public class ImportingReturnBillSearchDto  extends BaseSearchDto<List<ImportingReturnBillFullDto>> {
    String idCompany;
    String status;
    String priceMin;
    String code;
    String name;
    String rangePrice;
}

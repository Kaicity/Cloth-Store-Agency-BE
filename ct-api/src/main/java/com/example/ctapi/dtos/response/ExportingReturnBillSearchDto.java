package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.request.BaseSearchDto;

import java.util.List;

public class ExportingReturnBillSearchDto extends BaseSearchDto<List<ExportingReturnBillFullDto>> {

    String idCompany;
    String status;

}

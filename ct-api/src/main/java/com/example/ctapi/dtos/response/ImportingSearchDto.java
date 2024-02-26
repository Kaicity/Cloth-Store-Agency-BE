package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.request.BaseSearchDto;

import java.util.List;

public class ImportingSearchDto extends BaseSearchDto<List<ImportingFullDto>> {
    String idCompany;
    String status;
}

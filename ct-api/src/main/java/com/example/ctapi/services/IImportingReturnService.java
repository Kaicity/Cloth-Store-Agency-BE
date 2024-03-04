package com.example.ctapi.services;

import com.example.ctapi.dtos.response.ImportingReturnBillFullDto;

public interface IImportingReturnService {
    void createImportingReturnbill(ImportingReturnBillFullDto importingReturnBIll);
}

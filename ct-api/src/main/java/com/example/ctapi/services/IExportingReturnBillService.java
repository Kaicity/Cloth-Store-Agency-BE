package com.example.ctapi.services;

import com.example.ctapi.dtos.response.ImportingSearchDto;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface IExportingReturnBillService {
    public ImportingSearchDto getAllImportingFull(HttpServletRequest request) throws IOException;
}

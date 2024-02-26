package com.example.ctapi.services;

import com.example.ctapi.dtos.response.ExportingReturnBillSearchDto;
import com.example.ctapi.dtos.response.ImportingSearchDto;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface IExportingReturnBillService {
    ExportingReturnBillSearchDto getAllExportingReturnFull(HttpServletRequest request) throws IOException;
}

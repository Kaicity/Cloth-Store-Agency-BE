package com.example.ctapi.services;

import com.example.ctapi.dtos.response.ExportingBillFullDto;
import com.example.ctapi.dtos.response.ExportingBillFullSearchDto;
import com.example.ctcoremodel.ProductModel;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface IExportingbillService {
    void createExportingbill(ExportingBillFullDto exportingBillFullDto) ;

    List<ExportingBillFullDto> getAllExportingbill(HttpServletRequest request) throws IOException;

    List<ProductModel> getTesTToWarehouse(HttpServletRequest request) throws IOException;
    ExportingBillFullSearchDto getAllExportingBillUseBaseSearch(HttpServletRequest request) throws  IOException;
}

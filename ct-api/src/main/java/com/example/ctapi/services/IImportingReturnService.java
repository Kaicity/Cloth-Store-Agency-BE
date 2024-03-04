package com.example.ctapi.services;

import com.example.ctapi.dtos.response.ExportingBillFullDto;
import com.example.ctapi.dtos.response.ImportingReturnBillFullDto;
import com.example.ctapi.dtos.response.ImportingReturnBillSearchDto;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface IImportingReturnService {
    void createImportingReturnbill(ImportingReturnBillFullDto importingReturnBIll);
    ImportingReturnBillFullDto getImportingReturnById(HttpServletRequest request, String id) throws IOException;
    void deleteImportingReturnfullByid(String id);
    List<ImportingReturnBillFullDto> getAllImportingbReturnill(HttpServletRequest request) throws IOException;
    ImportingReturnBillSearchDto getAllImportingReturnBillUseBaseSearch(HttpServletRequest request) throws IOException;
    void updateImportingReturn(ImportingReturnBillFullDto importingReturnBillFullDto);
}

package com.example.ctapi.services;

import com.example.ctapi.dtos.response.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface IExportingReturnBillService {
    ExportingReturnBillSearchDto getAllExportingReturnFull(HttpServletRequest request) throws IOException;
    void deleteExortingReturnFullByid(String id);
    ExportingReturnBillFullDto getExportingReturnById(HttpServletRequest request, String id)  throws IOException;
    void updateExportingReturn(ExportingReturnBillFullDto exportingReturnBillFullDto);
    void createExportingReturn(ExportingReturnBillFullDto exportingReturnBillFullDto);
}

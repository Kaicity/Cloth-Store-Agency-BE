package com.example.ctapi.services;


import com.example.ctapi.dtos.response.ImportingFullDto;
import com.example.ctapi.dtos.response.ImportingSearchDto;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface IImportingService {
    void createImporting(ImportingFullDto importingFull);

    void updateImporting(ImportingFullDto importingFull);

    void deleteImportingFullByid(String id);

    ImportingSearchDto getAllImportingFull(HttpServletRequest request) throws IOException;

    ImportingFullDto getImportingById(HttpServletRequest request, String id)  throws IOException;
}

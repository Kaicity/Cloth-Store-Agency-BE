package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.ImportingSearchDto;
import com.example.ctapi.services.IExportingReturnBillService;
import com.example.ctcommondal.repository.IExportingReturnBillRepository;
import com.example.ctcommondal.repository.IImportingRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class IExportingReturnBillServiceImpl implements IExportingReturnBillService {

    private final Logger logger = LoggerFactory.getLogger(IExportingReturnBillServiceImpl.class);
    private final IExportingReturnBillRepository exportingReturnBillRepository;

    @Transactional
    @Override
    public ImportingSearchDto getAllImportingFull(HttpServletRequest request) throws IOException{
   return null;
    }
}

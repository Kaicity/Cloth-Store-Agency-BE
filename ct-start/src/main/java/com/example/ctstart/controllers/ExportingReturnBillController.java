package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.services.IExportingReturnBillService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/ExportReturn")
public class ExportingReturnBillController {
    private static final Logger logger = LoggerFactory.getLogger(IExportingReturnBillService.class);
    @Autowired
    private IExportingReturnBillService iExportingReturnService;
    @PostMapping("/getAllExportingReturn")
    public ResponseEntity<?> getAllExportingReturn(HttpServletRequest request) {
        int a = 0;
        try {
            var result = iExportingReturnService.getAllExportingReturnFull(request);
            return ResponseEntity.ok(new ResponseDto(List.of("get all importing success"),
                    HttpStatus.OK.value(), result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("get all importing unsuccess"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

}

package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ImportingReturnBillFullDto;
import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.serviceImpl.IExportingServiceImpl;
import com.example.ctapi.services.IImportingReturnService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/Importingbillreturnbill")
public class IImportingReturnBillController {
    private static final Logger logger = LoggerFactory.getLogger(IExportingServiceImpl.class);
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    final IImportingReturnService iImportingReturnService;
    @PostMapping("/create")
    public ResponseEntity<?> createReturnBill(@RequestBody ImportingReturnBillFullDto importingReturnBIll) {
        try {
            iImportingReturnService.createImportingReturnbill(importingReturnBIll);
            return ResponseEntity.ok(new ResponseDto(List.of("create bill success"),
                    HttpStatus.CREATED.value(), importingReturnBIll));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("Can not created bill"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getImportingReturnById(HttpServletRequest request, @PathVariable String id) {
//        try {
//            var result = iImportingReturnService.getExportingById(request, id);
//            return ResponseEntity.ok(new ResponseDto(List.of("data get sucess"),
//                    HttpStatus.OK.value(), result));
//        } catch (RuntimeException | IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get Payment: " + e.getMessage());
//        }
//    }

}

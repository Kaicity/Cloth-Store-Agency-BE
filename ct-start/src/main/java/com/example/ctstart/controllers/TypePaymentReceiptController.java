package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.dtos.response.TypePaymentReceiptDto;
import com.example.ctapi.serviceImpl.ITypePaymentReceiptServiceImpl;
import com.example.ctcommon.enums.TypePaymentReceipt;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/typePaymentReceipt")
public class TypePaymentReceiptController {
    private static final Logger logger = LoggerFactory.getLogger(TypePaymentReceiptController.class);
    @Autowired
    private final ITypePaymentReceiptServiceImpl iTypePaymentReceiptService;

    @PostMapping("/create")
    public ResponseEntity<?> newOption(@RequestBody TypePaymentReceiptDto typePaymentReceiptDto) {
        try {
            iTypePaymentReceiptService.addTypePaymentReceipt(typePaymentReceiptDto);
            return ResponseEntity.ok(new ResponseDto(
                    List.of("Adding data for option"),
                    HttpStatus.CREATED.value(),
                    typePaymentReceiptDto
            ));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(
                    List.of("Can not add data"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ));
        }
    }

    @PostMapping("/getAllPayments")
    public ResponseEntity<?> getALlOptionSizes() {
        try {
            var result = iTypePaymentReceiptService.getAllPayments(TypePaymentReceipt.PAYMENT);
            return ResponseEntity.ok(new ResponseDto(
                    List.of("Get all Payment Types"),
                    HttpStatus.CREATED.value(),
                    result
            ));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(
                    List.of("Can not get Data"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ));
        }
    }

    @PostMapping("/getAllReceipts")
    public ResponseEntity<?> getALlOptionColors() {
        try {
            var result = iTypePaymentReceiptService.getAllReceipt(TypePaymentReceipt.RECEIPT);
            return ResponseEntity.ok(new ResponseDto(
                    List.of("Get all Receipt Types"),
                    HttpStatus.CREATED.value(),
                    result
            ));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(
                    List.of("Can not get Data"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ));
        }
    }
}

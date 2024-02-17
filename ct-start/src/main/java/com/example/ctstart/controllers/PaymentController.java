package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.PaymentFullDto;
import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.services.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/Payment")

public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(IPaymentService.class);
    @Autowired
    private IPaymentService iPaymentService;

    @PostMapping("/createPayment")
    public ResponseEntity<?> createPayment(@RequestBody PaymentFullDto paymentFull) {
        try {
            int a = 0;
            iPaymentService.createPayment(paymentFull);
            return ResponseEntity.ok(new ResponseDto(List.of("create Payment success"),
                    HttpStatus.CREATED.value(), paymentFull));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("Can not created Payment"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping("/getAllPayment")
    public ResponseEntity<?> getAllPayment() {
        int a = 0;
        try {
            var result = iPaymentService.getAllPaymentFull();
            return ResponseEntity.ok(new ResponseDto(List.of("get all Payment success"),
                    HttpStatus.OK.value(), result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("get all Payment unsuccess"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentById(@PathVariable String id) {
        try {
            String result ="Payment deleted id: " +id;
            iPaymentService.deletePaymentFullByid(id);
            return ResponseEntity.ok(new ResponseDto(List.of("Deleting data for Payment"),
                    HttpStatus.OK.value(), result));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("Can not delete data"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping("/updatePayment")
    public ResponseEntity<?> updatePayment(@RequestBody PaymentFullDto paymentFull) {
        try {
            iPaymentService.updatePayment(paymentFull);
            return ResponseEntity.ok(new ResponseDto(List.of("data Updatating sucess"),
                    HttpStatus.OK.value(),paymentFull ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Payment: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable String id) {
        try {
            var result = iPaymentService.getPaymentById(id);
            return ResponseEntity.ok(new ResponseDto(List.of("data get sucess"),
                    HttpStatus.OK.value(),result ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get Payment: " + e.getMessage());
        }
    }
}

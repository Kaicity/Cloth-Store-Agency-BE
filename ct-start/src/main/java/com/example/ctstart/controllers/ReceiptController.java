package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ReceiptFullDto;
import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.services.IReceiptService;
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
@RequestMapping(path = "/api/v1/Receipt")

public class ReceiptController {
    private static final Logger logger = LoggerFactory.getLogger(IReceiptService.class);
    @Autowired
    private IReceiptService iReceiptService;

    @PostMapping("/createReceipt")
    public ResponseEntity<?> createPayment(@RequestBody ReceiptFullDto receiptFull) {
        try {

            iReceiptService.createReceipt(receiptFull);
            return ResponseEntity.ok(new ResponseDto(List.of("create receipt success"),
                    HttpStatus.CREATED.value(), receiptFull));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("Can not created receipt"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping("/getAllReceipt")
    public ResponseEntity<?> getAllReceipt() {
        try {
            var result = iReceiptService.getAllReceiptFull();
            return ResponseEntity.ok(new ResponseDto(List.of("get all receipt success"),
                    HttpStatus.OK.value(), result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("get all receipt unsuccess"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReceiptById(@PathVariable String id) {
        try {
            String result ="Receipt deleted id: " +id;
            iReceiptService.deleteReceiptFullByid(id);
            return ResponseEntity.ok(new ResponseDto(List.of("Deleting data for Receipt"),
                    HttpStatus.OK.value(), result));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("Can not delete data"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping("/updateReceipt")
    public ResponseEntity<?> updatePayment(@RequestBody ReceiptFullDto receiptFull) {
        try {
            iReceiptService.updateReceipt(receiptFull);
            return ResponseEntity.ok(new ResponseDto(List.of("data Updatating sucess"),
                    HttpStatus.OK.value(),receiptFull ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Receipt: " + e.getMessage());
        }
    }
}

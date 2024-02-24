package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ImportingFullDto;
import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.services.IImportingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/Importing")
public class ImportingController {
    private static final Logger logger = LoggerFactory.getLogger(IImportingService.class);
    @Autowired
    private IImportingService iImportingService;

    @PostMapping("/getAllImporting")
    public ResponseEntity<?> getAllImporting(HttpServletRequest request) {
        int a = 0;
        try {
            var result = iImportingService.getAllImportingFull(request);
            return ResponseEntity.ok(new ResponseDto(List.of("get all importing success"),
                    HttpStatus.OK.value(), result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("get all importing unsuccess"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> getAllImporting(@PathVariable String id) {
        try {
            iImportingService.deleteImportingFullByid(id);
            return ResponseEntity.ok(new ResponseDto(List.of("get all importing success"),
                    HttpStatus.OK.value(), "deleted : " + id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("get all importing unsuccess"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImportingById(HttpServletRequest request, @PathVariable String id) {
        try {
            var result = iImportingService.getImportingById(request, id);
            return ResponseEntity.ok(new ResponseDto(List.of("data get sucess"),
                    HttpStatus.OK.value(), result));
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get Payment: " + e.getMessage());
        }
    }

    @PostMapping("/updateImporting")
    public ResponseEntity<?> updatePayment(@RequestBody ImportingFullDto importingFull) {
        try {
            int a = 0;
            iImportingService.updateImporting(importingFull);
            return ResponseEntity.ok(new ResponseDto(List.of("data Updatating success"),
                    HttpStatus.OK.value(), importingFull));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Payment: " + e.getMessage());
        }
    }

    @PostMapping("/createImporting")
    public ResponseEntity<?> createImporting(@RequestBody ImportingFullDto importingFull) {
        try {
            iImportingService.createImporting(importingFull);
            return ResponseEntity.ok(new ResponseDto(List.of("data created success"),
                    HttpStatus.OK.value(), importingFull));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Payment: " + e.getMessage());
        }
    }
}

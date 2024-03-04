package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ExportingReturnBillFullDto;
import com.example.ctapi.dtos.response.ImportingFullDto;
import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.services.IExportingReturnBillService;
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


    @DeleteMapping("/{id}")
    public ResponseEntity<?> getDeteleExportingReturn(@PathVariable String id) {
        try {
            iExportingReturnService.deleteExortingReturnFullByid(id);
            return ResponseEntity.ok(new ResponseDto(List.of("get all importing success"),
                    HttpStatus.OK.value(), "deleted : " + id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("get all importing unsuccess"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExportingReturnById(HttpServletRequest request, @PathVariable String id) {
        try {
            var result = iExportingReturnService.getExportingReturnById(request, id);
            return ResponseEntity.ok(new ResponseDto(List.of("data get sucess"),
                    HttpStatus.OK.value(), result));
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get Payment: " + e.getMessage());
        }
    }

    @PostMapping("/updateExportingReturn")
    public ResponseEntity<?> updatePayment(@RequestBody ExportingReturnBillFullDto exportingReturnBillFull) {
        try {
            int a = 0;
            iExportingReturnService.updateExportingReturn(exportingReturnBillFull);
            return ResponseEntity.ok(new ResponseDto(List.of("data Updatating success"),
                    HttpStatus.OK.value(), exportingReturnBillFull));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Payment: " + e.getMessage());
        }
    }
    @PostMapping("/createExportingReturn")
    public ResponseEntity<?> createImporting(@RequestBody ExportingReturnBillFullDto exportingReturnBillFullDto) {
        try {
            iExportingReturnService.createExportingReturn(exportingReturnBillFullDto);
            return ResponseEntity.ok(new ResponseDto(List.of("data created success"),
                    HttpStatus.OK.value(), exportingReturnBillFullDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Payment: " + e.getMessage());
        }
    }
}

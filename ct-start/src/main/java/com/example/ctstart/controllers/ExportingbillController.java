package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ExportingBillDto;
import com.example.ctapi.dtos.response.ExportingBillFullDto;
import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.dtos.response.SocketMessage;
import com.example.ctapi.serviceImpl.IExportingServiceImpl;
import com.example.ctapi.services.IExportingbillService;
import com.example.ctcoremodel.ProductModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/Exportingbill")
public class ExportingbillController {
    private static final Logger logger = LoggerFactory.getLogger(IExportingServiceImpl.class);
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    private IExportingbillService exportingbillService;

    @PostMapping("/create")
    public ResponseEntity<?> createBill(@RequestBody ExportingBillFullDto exportingBillFullDto) {
        try {
            exportingbillService.createExportingbill(exportingBillFullDto);
            return ResponseEntity.ok(new ResponseDto(List.of("create bill success"),
                    HttpStatus.CREATED.value(), exportingBillFullDto));
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("Can not created bill"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping("/getAllExportingBill")
    public ResponseEntity<?> getAllExportingbill(HttpServletRequest request) {
        try {
            List<ExportingBillFullDto> result = exportingbillService.getAllExportingbill(request);
            return ResponseEntity.ok(new ResponseDto(List.of("get all bill success"),
                    HttpStatus.OK.value(), result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("get all bill unsuccess"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping("testSpr2")
    public void go() {
        messagingTemplate.convertAndSend("/topic/" + "billRealTimeSection",
                new SocketMessage("billRealTimeSection", "gi dc r ha ghe v", new ExportingBillDto()));
    }

    @PostMapping("test")
    public ResponseEntity<?> testToWH(HttpServletRequest request) {
        try {
            List<ProductModel> result = exportingbillService.getTesTToWarehouse(request);
            return ResponseEntity.ok(new ResponseDto(List.of("get all bill success"),
                    HttpStatus.OK.value(), result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of("get all bill unsuccess"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }
}

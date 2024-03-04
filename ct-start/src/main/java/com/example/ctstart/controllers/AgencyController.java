package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.services.IAgencyService;
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
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/Agency")
public class AgencyController {
    private static final Logger logger = LoggerFactory.getLogger(AgencyController.class);
    @Autowired
    private IAgencyService iAgencyService;

    @PostMapping("/getAngecyByIds")
    private ResponseEntity<?> getAngecyByIds(@RequestBody Map<String, String> ids) {
        String id = ids.get("id");
        try {
            var result = iAgencyService.getAgencyById(id);
            return ResponseEntity.ok(new ResponseDto(List.of("get success"), HttpStatus.OK.value(), result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDto(List.of(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }
}

package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import com.example.ctcommon.enums.BillStatus;
import com.example.ctcoremodel.CustomerModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class ExportingBillDto {
    private String id;
    private String code;
    private LocalDateTime dateExport;
    private LocalDateTime dateCreated;
    private Double total;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    private CustomerModel customer;
    private AgencyDto agency;
    private CustomerNotLoginDto customerNotLogin;

    public ExportingBillDto() {
        this.id = CreateRandomID.generatingUID();
        this.code = CreateRandomID.generateRandomId("DH");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        this.dateCreated = LocalDateTime.parse(formattedDateTime, formatter);
    }
}
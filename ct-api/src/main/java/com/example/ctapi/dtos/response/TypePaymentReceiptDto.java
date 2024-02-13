package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor

public class TypePaymentReceiptDto {
    private String id;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateCreated;
    private String type;
    private String name;
    private String description;

    public TypePaymentReceiptDto() {
        this.id = CreateRandomID.generatingUID();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        this.dateCreated = LocalDateTime.parse(formattedDateTime, formatter);
    }
}

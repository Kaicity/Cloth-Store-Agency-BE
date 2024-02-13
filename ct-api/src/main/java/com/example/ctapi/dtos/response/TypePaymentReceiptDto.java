package com.example.ctapi.dtos.response;

import com.example.ctcommon.enums.TypePaymentReceipt;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private TypePaymentReceipt type;
    private String name;
    private String description;

    public TypePaymentReceiptDto() {
        //this.id = CreateRandomID.generatingUID(); //Chức năng thêm TypePaymentReceipt tự sinh id
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        this.dateCreated = LocalDateTime.parse(formattedDateTime, formatter);
    }
}

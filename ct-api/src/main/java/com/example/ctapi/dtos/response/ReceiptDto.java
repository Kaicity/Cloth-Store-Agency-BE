package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import com.example.ctcommon.enums.ReceiptStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class ReceiptDto {
    private String id;
    private String code;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateCreated;
    private Double total;
    @Enumerated(EnumType.STRING)
    private ReceiptStatus status;
    private TypePaymentReceiptDto typePaymentReceipt;
    private String note;

    public ReceiptDto() {
        this.id = CreateRandomID.generatingUID();
        this.code = CreateRandomID.generateRandomId("RC");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        this.dateCreated = LocalDateTime.parse(formattedDateTime, formatter);
    }
}

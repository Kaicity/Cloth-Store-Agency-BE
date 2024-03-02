package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import com.example.ctcommon.enums.ImportingStatus;
import com.example.ctcoremodel.SupplierModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class ExportingReturnBillDto {
    private String id;
    private String code;
    private AgencyDto agency;
    private SupplierModel supplier;
    private Double total;
    @Enumerated(EnumType.STRING)
    private ImportingStatus status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private ImportingDto importing;

    public ExportingReturnBillDto() {
        this.id = CreateRandomID.generatingUID();
        this.code = CreateRandomID.generateRandomId("ER");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        this.dateCreated = LocalDateTime.parse(formattedDateTime, formatter);
    }
}

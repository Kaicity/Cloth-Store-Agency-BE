package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import com.example.ctcommon.enums.ImportingStatus;
import com.example.ctcoremodel.SupplierModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

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
    private LocalDateTime dateExport;
    private LocalDateTime dateUpdated;
    private ImportingDto importing;

    public ExportingReturnBillDto() {
        this.id = CreateRandomID.generatingUID();
        this.code = CreateRandomID.generateRandomId("IP");
    }
}

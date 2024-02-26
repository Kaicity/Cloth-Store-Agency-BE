package com.example.ctcommondal.entity;

import com.example.ctcommon.enums.ImportingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exporting_return_bill")
public class ExportingReturnBillEntity {

    @Id
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "agency_id")
    private String agencyId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "total")
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ImportingStatus status;

    @Column(name = "date_created")
    private LocalDateTime dateExport;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @Column(name = "id_importingbill")
    private String importingId;

}

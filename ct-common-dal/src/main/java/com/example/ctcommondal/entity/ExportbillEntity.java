package com.example.ctcommondal.entity;

import com.example.ctcommon.enums.BillStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exporting_bill")
public class ExportbillEntity {

    @Id
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "date_Export")
    private LocalDateTime dateExport;

    @Column(name = "date_Created")
    private LocalDateTime dateCreated;

    @Column(name = "total")
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BillStatus status;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "agency_id")
    private String agencyId;

}

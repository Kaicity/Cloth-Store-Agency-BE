package com.example.ctcommondal.entity;

import com.example.ctcommon.enums.BillStatus;
import com.example.ctcommon.enums.ImportingReturnStatus;
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
@Table(name = "importing_Return_bill")
public class ImportingReturnbillEntity {


    @Id
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "exporting_Id")
    private String exportingId;

    @Column(name = "date_Export")
    private LocalDateTime dateExport;

    @Column(name = "date_Created")
    private LocalDateTime dateCreated;

    @Column(name = "total")
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ImportingReturnStatus status;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "agency_id")
    private String agencyId;

    @Column(name = "eCustomer_ID")
    private String eid;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;


}

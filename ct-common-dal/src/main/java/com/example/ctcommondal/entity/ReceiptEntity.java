package com.example.ctcommondal.entity;

import com.example.ctcommon.enums.ReceiptStatus;
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
@Table(name = "receipt")
public class ReceiptEntity {

    @Id
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "date_Updated")
    private LocalDateTime dateUpdated;

    @Column(name = "date_Created")
    private LocalDateTime dateCreated;

    @Column(name = "total")
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReceiptStatus status;

    @Column(name = "id_type_receipt")
    private String idTypeReceipt;

    @Column(name = "note")
    private String note;
}
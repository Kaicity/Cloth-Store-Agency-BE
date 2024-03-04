package com.example.ctcommondal.entity;

import com.example.ctcommon.enums.PaymentStatus;
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
@Table(name = "payment")
public class PaymentEntity {

    @Id
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "total")
    private Double total;

    @Column(name = "beneficiary")
    private String beneficiary;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "id_type_payment")
    private String idTypePayment;

    @Column(name = "note")
    private String note;
}
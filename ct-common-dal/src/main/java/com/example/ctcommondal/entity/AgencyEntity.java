package com.example.ctcommondal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "agency")
public class AgencyEntity {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date")
    private LocalDateTime dateCreate;

    @Column(name = "update_date")
    private LocalDateTime dateUpdate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "code")
    private String code;

    @Column(name = "company_id")
    private String companyId;

}

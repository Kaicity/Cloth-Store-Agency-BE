package com.example.ctcoremodel;

import com.example.ctcommon.enums.StatusSupplier;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SupplierModel {
    private String id;
    private String code;
    private String name;
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private StatusSupplier status;
    private LocalDateTime dateCreate;
    private LocalDateTime dateUpdate;
}

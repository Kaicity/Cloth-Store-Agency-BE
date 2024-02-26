package com.example.ctcommondal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "exporting_return_transaction")
public class ExportingReturnTransactionEntity {
    @Id
    private String id;
    @Column(name = "billReturn")
    private String exportReturnId;

    @Column(name = "Product_ID")
    private String productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amount")
    private int amount;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;
}

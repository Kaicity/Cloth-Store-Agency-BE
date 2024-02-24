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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "importing_transaction")
public class ImportingTransactionEntity {
    @Id
    private String id;

    @Column(name = "importing_ID")
    private String importingId;

    @Column(name = "product_ID")
    private String productId;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amount")
    private Double amount;
}

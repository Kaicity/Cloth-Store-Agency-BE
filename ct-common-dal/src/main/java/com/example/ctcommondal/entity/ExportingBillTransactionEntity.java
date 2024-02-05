package com.example.ctcommondal.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "exporting_transaction")
public class ExportingBillTransactionEntity {
    @Id
    private String id;
    @Column(name = "bill_ID")
    private String billID;
    @Column(name = "Product_ID")
    private String productID;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "amount")
    private int amount;
    @Column(name = "size")
    private String size;
    @Column(name = "color")
    private String color;
}


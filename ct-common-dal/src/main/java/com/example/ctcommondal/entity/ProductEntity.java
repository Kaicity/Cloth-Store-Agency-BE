package com.example.ctcommondal.entity;

import com.example.ctcommon.enums.SpecificationProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    private String id;

    @Column(name = "Code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "specification")
    private SpecificationProduct specification;

    @Column(name = "category_id")
    private String categoryID;

    @Column(name = "company_id")
    private String companyID;
}

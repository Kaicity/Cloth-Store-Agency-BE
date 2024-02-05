package com.example.ctcoremodel;

import com.example.ctcommon.enums.SpecificationProduct;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProductFullModel {
    private String id;
    private String code;
    private String name;
    private Double price;
    private String status;
    private String description;
    private String image;
    @Enumerated(EnumType.STRING)
    private SpecificationProduct specification;
    private CateloryModel catelory;
    private CompanyModel company;
    List<ColorsModel> colors;
    List<SizesModel> sizes;
}

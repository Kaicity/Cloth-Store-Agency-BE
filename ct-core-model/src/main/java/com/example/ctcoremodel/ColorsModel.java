package com.example.ctcoremodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ColorsModel {
    private String id;
    private OptionProductModel optionProduct;
    private Double addition;
    private String image;
    private ProductModel product;
}

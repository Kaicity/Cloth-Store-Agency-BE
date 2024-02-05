package com.example.ctcoremodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SizesModel {
    private String id;
    private OptionProductModel optionProduct;
    private Double addition;
    private ProductModel product;
}

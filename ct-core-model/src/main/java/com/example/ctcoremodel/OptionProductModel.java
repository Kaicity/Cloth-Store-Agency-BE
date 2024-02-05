package com.example.ctcoremodel;

import com.example.ctcommon.enums.TypeOptionProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class OptionProductModel {
    private String id;
    private String name;
    private TypeOptionProduct type;
    private Date dateCreate;
    private Date dateUpdate;
}

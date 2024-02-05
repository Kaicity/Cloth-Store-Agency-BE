package com.example.ctcoremodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class AgencyModel {
    private String id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private String phone;
    private String address;
    private String code;
    private String companyId;
}

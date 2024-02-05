package com.example.ctcoremodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@NoArgsConstructor
@Data
public class CompanyModel {
    private String id;
    private String companyid;
    private String name;
    private Date createDate;
    private Date updateDate;
    private String phone;
    private String address;
    private String code;
}

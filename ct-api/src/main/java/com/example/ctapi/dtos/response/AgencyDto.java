package com.example.ctapi.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class AgencyDto {
    private String id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private String phone;
    private String address;
    private String code;
    private String companyId;
}

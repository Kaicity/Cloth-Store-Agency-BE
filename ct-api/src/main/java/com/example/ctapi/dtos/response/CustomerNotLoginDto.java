package com.example.ctapi.dtos.response;

import com.example.ctapi.dtos.BussinessLogic.CreateRandomID;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerNotLoginDto {
    private String eid;
    private String fullName;
    private String phone;
    private String email;
    private String address;

    public CustomerNotLoginDto(){
        this.eid = CreateRandomID.generateRandomId("KH");
    }
}

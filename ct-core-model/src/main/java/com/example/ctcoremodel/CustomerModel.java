package com.example.ctcoremodel;

import com.example.ctcommon.enums.CustomerGender;
import com.example.ctcommon.enums.CustomerRanking;
import com.example.ctcoremodel.bussinessLogic.CreateRandomID;
import com.example.ctcoremodel.bussinessLogic.HandleDate;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomerModel {
    private String id;
    private String eid;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Date birthday;
    private Date date_created;
    private Date date_updated;
    @Enumerated(EnumType.STRING)
    private CustomerRanking ranking;
    @Enumerated(EnumType.STRING)
    private CustomerGender gender;
    private String password;

    private Date getDateNowToDateCreated() {
        return HandleDate.getDatetimeNowFromSystem();
    }

    private Date getDateWhenUpdate() {
        return HandleDate.getDatetimeNowFromSystem();
    }

    public CustomerModel() {
        this.id = CreateRandomID.generatingUID();
        this.eid = CreateRandomID.generateRandomId();
        this.date_created = getDateNowToDateCreated();
    }
}

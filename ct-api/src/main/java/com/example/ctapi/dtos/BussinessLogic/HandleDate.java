package com.example.ctapi.dtos.BussinessLogic;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class HandleDate {

    public static LocalDateTime getDatetimeNowFromSystem() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }
}

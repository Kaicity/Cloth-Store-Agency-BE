package com.example.ctapi.dtos.BussinessLogic;

import java.util.Random;

public class CreateRandomIPID {
    public static String CreateRandomIPID(String tableCode) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        //String formatEid = letters + numbers;
        Random rd = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            char randomChar = letters.charAt(rd.nextInt(letters.length()));
            randomString.append(randomChar);
        }
        randomString.append("-");

        for (int i = 0; i < 4; i++) {
            char randomDigit = numbers.charAt(rd.nextInt(numbers.length()));
            randomString.append(randomDigit);
        }
       return tableCode + "-" + randomString;
    }
}

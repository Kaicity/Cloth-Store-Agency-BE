package com.example.ctcoremodel.bussinessLogic;

import java.util.Random;
import java.util.UUID;

public class CreateRandomID {

    //formar = abcd-1234
    public static String generateRandomId(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        //String formatEid = letters + numbers;
        Random rd = new Random();

        StringBuilder randomString = new StringBuilder();

        for(int i = 0; i < 4; i++){
            char randomChar = letters.charAt(rd.nextInt(letters.length()));
            randomString.append(randomChar);
        }
        randomString.append("-");

        for(int i = 0; i < 4; i++){
            char randomDigit =  numbers.charAt(rd.nextInt(numbers.length()));
            randomString.append(randomDigit);
        }
        return randomString.toString();

    }

    public static String generatingUID(){
        return UUID.randomUUID().toString();
    }
}

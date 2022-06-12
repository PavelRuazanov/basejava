package com.pavelryazanov;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class MainDate {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
    }
}

package com.example.backend_422.common;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class Utils {

    // parse string to Date
    public Date stringToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date1 = sdf.parse(date);
            return date1;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

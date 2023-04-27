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
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean CompareTwoDate(Date date1, Date date2){
        return date1.getTime() > date2.getTime();
    }
    public Date getNowDate(){
        return new Date();
    }

}

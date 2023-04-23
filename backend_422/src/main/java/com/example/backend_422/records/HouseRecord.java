package com.example.backend_422.records;

public record HouseRecord(Integer houseId, String houseName,String address,
                          String pic, String description, String status,
                          String rentPrice, String powerPrice, String waterPrice,
                          String area, String landlordName, String landlordPhone) {

}

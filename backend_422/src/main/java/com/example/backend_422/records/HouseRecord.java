package com.example.backend_422.records;

import org.springframework.web.multipart.MultipartFile;

public record HouseRecord(String houseName, String address,
                          MultipartFile pic, String description, String status,
                          String rentPrice, String powerPrice, String waterPrice,
                          String area, String landlordName, String landlordPhone,Integer landlordId) {

}

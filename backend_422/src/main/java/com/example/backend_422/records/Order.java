package com.example.backend_422.records;

public record Order(Integer orderId, Integer userId, String username, Integer houseId, String houseName, String status,
                    String createdTime, String userPhone,Integer landlordId,String landlordName,String landlordPhone,String address){

}

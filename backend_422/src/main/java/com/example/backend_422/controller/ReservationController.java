package com.example.backend_422.controller;


import com.example.backend_422.common.Result;
import com.example.backend_422.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;
    @GetMapping("getByUserId")
    public Result getReservationByUserId(@RequestParam("userId") Integer userId){
        return reservationService.getByUserId(userId);
    }

    @GetMapping("getByLandlordId")
    public  Result getByLandlordId(@RequestParam("landlordId") Integer landlordId){
        return reservationService.getByLandlordId(landlordId);
    }
    @PostMapping("confirm")
    public Result confirmReservation(@RequestParam("id") Integer id,
                                     @RequestParam("status") String status,
                                     @RequestParam(value = "houseId",required = false) Integer houseId){
        return reservationService.confirmReservation(id,status,houseId);
    }

    @PostMapping("add")
    public Result addReservation (@RequestParam("userId") Integer userId,
                                  @RequestParam("username") String username,
                                  @RequestParam("landlordId") Integer landlordId,
                                  @RequestParam("landlordName") String landlordName,
                                    @RequestParam("houseId") Integer houseId,
                                    @RequestParam("houseName") String houseName,
                                  @RequestParam("startTime") String startTime,
                                    @RequestParam("endTime") String endTime){
        return reservationService.addReservation(userId,username,landlordId,landlordName,houseId,houseName,startTime,endTime);
    }



}

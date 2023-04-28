package com.example.backend_422.controller;


import com.example.backend_422.common.Result;
import com.example.backend_422.service.impl.RentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xwyzsn
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/rent")
@CrossOrigin(origins = "*",maxAge = 3600)
public class RentController {


    @Autowired
    private RentServiceImpl rentService;
    @GetMapping("getByUserId")
    public Result getRentByUserId(@RequestParam("userId") Integer userId){
        return rentService.getRentByUserId(userId);
    }
    @GetMapping("getByLandlordId")
    public Result getByLandlordId(@RequestParam("landlordId") Integer landlordId){
        return rentService.getByLandlordId(landlordId);
    }
    @PostMapping("setStatus")
    public Result setRentStatus(@RequestParam("houseId") Integer houseId,@RequestParam("status") String status,
                                @RequestParam("rentId") Integer rentId){
        return rentService.setRentStatus(houseId,status,rentId);
    }

    @GetMapping("getquit")
    public Result getQuitOrder(@RequestParam("landlordId") Integer landlordId){
        return rentService.getQuitOrder(landlordId);
    }

    @PostMapping("confirmQuit")
    public Result confirmQuit(@RequestParam("status") String status,
                              @RequestParam("rentId") Integer rentId,@RequestParam("endTime") String endTime,
                              @RequestParam(value = "houseId",required = false) Integer houseId){
        return rentService.confirmQuit(endTime,status,rentId,houseId);
}}

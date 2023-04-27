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
}

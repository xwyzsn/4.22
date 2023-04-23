package com.example.backend_422.controller;


import com.example.backend_422.common.Result;
import com.example.backend_422.service.impl.HouseServiceImpl;
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
@RequestMapping("/house")
@CrossOrigin(origins = "*",maxAge = 3600)
public class HouseController {


    @Autowired
    private HouseServiceImpl houseService;

    @GetMapping("getall")
    public Result getAll(){
        return houseService.getAll();
    }


    @GetMapping("getbylandlordid")
    public Result getMyHouse(@RequestParam("landlordId") Integer landlordId){
        return houseService.getMyHouse(landlordId);
    }

    @DeleteMapping("deletebyhouseid")
    public Result deleteByHouseId(@RequestParam("houseId") Integer houseId){
        return houseService.deleteByHouseId(houseId);
    }

    @PostMapping("addnew")
    public Result addNewHouse(@RequestBody com.example.backend_422.entity.House house){
        return houseService.addNewHouse(house);
    }


}

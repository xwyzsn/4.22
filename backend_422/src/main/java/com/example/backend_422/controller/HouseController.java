package com.example.backend_422.controller;


import com.example.backend_422.common.Result;
import com.example.backend_422.entity.House;
import com.example.backend_422.records.HouseRecord;
import com.example.backend_422.service.impl.HouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

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
    public Result addNewHouse(@RequestParam(value = "houseName",required = false) String houseName,
                              @RequestParam(value = "address",required = false) String address,
                              @RequestParam(value = "pic",required = false) MultipartFile pic,
                              @RequestParam(value = "description",required = false) String description,
                              @RequestParam(value = "status",required = false) String status,
                              @RequestParam(value = "rentPrice",required = false) String rentPrice,
                              @RequestParam(value = "powerPrice",required = false) String powerPrice,
                              @RequestParam(value = "waterPrice",required = false) String waterPrice,
                              @RequestParam(value = "area",required = false) String area,
                              @RequestParam(value = "landlordName",required = false) String landlordName,
                              @RequestParam(value = "landlordPhone",required = false) String landlordPhone,
                              @RequestParam(value = "landlordId",required = false) Integer landlordId){
        return houseService.addNewHouse(new HouseRecord(houseName,address,pic,description,status,rentPrice,powerPrice,waterPrice,area,landlordName,landlordPhone,landlordId));
    }

    @PostMapping("update")
    public Result updateHouse(@RequestParam(value = "houseId",required = false) Integer houseId,
                              @RequestParam(value = "houseName",required = false) String houseName,
                              @RequestParam(value = "address",required = false) String address,
                              @RequestParam(value = "pic",required = false) MultipartFile pic,
                              @RequestParam(value = "description",required = false) String description,
                              @RequestParam(value = "status",required = false) String status,
                              @RequestParam(value = "rentPrice",required = false) String rentPrice,
                              @RequestParam(value = "powerPrice",required = false) String powerPrice,
                              @RequestParam(value = "waterPrice",required = false) String waterPrice,
                              @RequestParam(value = "area",required = false) String area,
                              @RequestParam(value = "landlordName",required = false) String landlordName,
                              @RequestParam(value = "landlordPhone",required = false) String landlordPhone,
                              @RequestParam(value = "landlordId",required = false) Integer landlordId){
        return houseService.updateHouse(houseId,new HouseRecord(houseName,address,pic,description,status,rentPrice,powerPrice,waterPrice,area,landlordName,landlordPhone,landlordId));
    }


}

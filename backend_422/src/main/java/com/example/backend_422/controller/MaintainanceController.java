package com.example.backend_422.controller;


import com.example.backend_422.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xwyzsn
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/maintainance")
@CrossOrigin(origins = "*",maxAge = 3600)
public class MaintainanceController {

    @Autowired
    private com.example.backend_422.service.impl.MaintainanceServiceImpl maintainanceService;
    @PostMapping("getMaintainanceListByHouseList")
    public Result getMaintainanceListByHouseList(@RequestParam("houseList") List<Integer> houseList){
        return maintainanceService.getMaintainanceListByHouseList(houseList);
    }

    @PostMapping("report")
    public Result reportIssue(@RequestParam("houseId") Integer homeId,
                              @RequestParam("desc") String desc,
                              @RequestParam("userId") Integer userId,
                              @RequestParam("username") String username,
                              @RequestParam("houseName") String houseName,
                              @RequestParam("reportTime") String reportTime){
        return maintainanceService.reportIssue(homeId,desc,userId,username,houseName,reportTime);
    }

    @GetMapping("getMaintainanceListByUser")
    public Result getMaintainanceListByUser(@RequestParam("userId") Integer userId){
        return maintainanceService.getMaintainanceListByUser(userId);
    }

    @PostMapping("updateStatus")
    public Result updateStatus(@RequestParam("maintainanceId") Integer id,
                               @RequestParam("status") String status){
        return maintainanceService.updateStatus(id,status);
    }


}

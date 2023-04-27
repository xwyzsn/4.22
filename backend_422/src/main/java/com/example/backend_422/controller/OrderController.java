package com.example.backend_422.controller;


import com.example.backend_422.common.Result;
import com.example.backend_422.records.Order;
import com.example.backend_422.service.impl.OrderServiceImpl;
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
@RequestMapping("/order")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("preserve")
    public Result preserveTime(@RequestBody Order order){
        return    orderService.preserveTime(order);
    }

    @GetMapping("getByUserId")
    public Result getByUserId(@RequestParam("userId") String userId){
        return orderService.getByUserId(userId);
    }

    @GetMapping("cancel")
    public Result cancelByOrderId(@RequestParam("orderId") Integer orderId){
        return orderService.cancelByOrderId(orderId);
    }

    @GetMapping("getByLandlordId")
    public Result getByLandlordId(@RequestParam("landlordId") Integer landlordId){
        return orderService.getByLandlordId(landlordId);
    }

    @PutMapping("updateStatus")
    public Result updateStatus(@RequestParam("orderId") Integer orderId,
                               @RequestParam("status") String status){
        return orderService.updateStatus(orderId,status);
    }

}

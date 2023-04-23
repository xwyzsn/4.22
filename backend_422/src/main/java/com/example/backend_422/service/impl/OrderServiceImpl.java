package com.example.backend_422.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend_422.common.Result;
import com.example.backend_422.common.Utils;
import com.example.backend_422.entity.Order;
import com.example.backend_422.mapper.OrderMapper;
import com.example.backend_422.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwyzsn
 * @since 2023-04-22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {



    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private Utils utils;
    public Result preserveTime(com.example.backend_422.records.Order orderRecord) {
        Order order = new Order();
        order.setHouseId(orderRecord.houseId());
        order.setHouseName(orderRecord.houseName());
        order.setUserId(orderRecord.userId());
        order.setCreatedTime(utils.stringToDate(orderRecord.createdTime()));
        order.setStatus(orderRecord.status());
        order.setUserPhone(orderRecord.userPhone());
        order.setUsername(orderRecord.username());
        order.setLandlordId(orderRecord.landlordId());
        order.setLandlordPhone(orderRecord.landlordPhone());
        order.setAddress(orderRecord.address());
        order.setLandlordName(orderRecord.landlordName());
        orderMapper.insert(order);
        return Result.succ("null");
    }

    public Result getByUserId(String userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Order> orders = orderMapper.selectList(wrapper);
        return Result.succ(orders);
    }

    public Result cancelByOrderId(Integer OrderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",OrderId);
        orderMapper.delete(wrapper);
        return Result.succ("success");
    }
}

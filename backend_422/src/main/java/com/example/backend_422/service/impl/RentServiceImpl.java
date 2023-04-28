package com.example.backend_422.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend_422.common.Result;
import com.example.backend_422.common.Utils;
import com.example.backend_422.entity.House;
import com.example.backend_422.entity.Rent;
import com.example.backend_422.mapper.HouseMapper;
import com.example.backend_422.mapper.RentMapper;
import com.example.backend_422.service.RentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwyzsn
 * @since 2023-04-22
 */
@Service
public class RentServiceImpl extends ServiceImpl<RentMapper, Rent> implements RentService {

    @Autowired
    private RentMapper rentMapper;
    public Result getRentByUserId(Integer userId) {
        QueryWrapper<Rent> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return Result.succ(rentMapper.selectList(wrapper));

    }

    public Result getByLandlordId(Integer landlordId) {
        QueryWrapper<Rent> wrapper = new QueryWrapper<>();
        wrapper.eq("landlord_id",landlordId);
        return Result.succ(rentMapper.selectList(wrapper));
    }

    public Result setRentStatus(Integer houseId, String status,Integer rentId) {
        UpdateWrapper<Rent> wrapper = new UpdateWrapper<>();
        wrapper.eq("rent_id",rentId);
        wrapper.set("status",status);
        rentMapper.update(null,wrapper);
        return Result.succ("success");
    }

    public Result getQuitOrder(Integer landlordId) {
        QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("landlord_id",landlordId);
        queryWrapper.eq("status","退租待确认");
        return  Result.succ(rentMapper.selectList(queryWrapper));
    }
    @Autowired
    private  Utils utils;

    @Autowired
    private HouseMapper houseMapper;

    public Result confirmQuit(String endTime, String status, Integer rentId,Integer houseId) {
        UpdateWrapper<Rent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("rent_id",rentId);
        updateWrapper.set("end_time",utils.stringToDate(endTime));
        updateWrapper.set("status",status);
        if(status.equals("已结束")){
            UpdateWrapper<House> houseUpdateWrapper = new UpdateWrapper<>();
            houseUpdateWrapper.eq("house_id",houseId);
            houseUpdateWrapper.set("status","未租");
            houseMapper.update(null,houseUpdateWrapper);
        }
        rentMapper.update(null,updateWrapper);
        return Result.succ("success");
    }
}

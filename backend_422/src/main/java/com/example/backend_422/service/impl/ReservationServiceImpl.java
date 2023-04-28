package com.example.backend_422.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend_422.common.Result;
import com.example.backend_422.common.Utils;
import com.example.backend_422.entity.House;
import com.example.backend_422.entity.Rent;
import com.example.backend_422.entity.Reservation;
import com.example.backend_422.mapper.HouseMapper;
import com.example.backend_422.mapper.RentMapper;
import com.example.backend_422.mapper.ReservationMapper;
import com.example.backend_422.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private Utils utils;

    public Result getByLandlordId(Integer landlordId) {
        QueryWrapper<Reservation> wrapper = new QueryWrapper<>();
        wrapper.eq("landlord_id",landlordId);
        return Result.succ(reservationMapper.selectList(wrapper));
    }

    public Result getByUserId(Integer userId){
        QueryWrapper<Reservation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return Result.succ(reservationMapper.selectList(wrapper));
    }
    @Autowired
    private HouseMapper houseMapper;

    public Result confirmReservation(Integer id, String status,Integer houseId){
        if(status.equals("已拒绝")){
            UpdateWrapper<Reservation> wrapper = new UpdateWrapper<>();
            wrapper.eq("id",id);
            wrapper.set("status",status);
            return Result.succ(reservationMapper.update(null,wrapper));
        }
        if(houseId != null){
            QueryWrapper<Rent> rentQueryWrapper = new QueryWrapper<>();
            rentQueryWrapper.eq("house_id",houseId).orderBy(false,true,"rent_id").last("limit 1");
            Rent rent = rentMapper.selectOne(rentQueryWrapper);
            if(rent != null){
                Boolean res = utils.CompareTwoDate(rent.getEndTime(),utils.getNowDate());
                if(res){
                    return Result.fail("房屋已经租出去了");
                }
            }
        }
        UpdateWrapper<Reservation> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        wrapper.set("status",status);
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Reservation reservation = reservationMapper.selectOne(queryWrapper);

        Rent rent = new Rent();
        rent.setHouseId(houseId);
        rent.setHouseName(reservation.getHouseName());
        rent.setLandlordId(reservation.getLandlordId());
        rent.setUserId(reservation.getUserId());
        rent.setStartTime(reservation.getStartTime());
        rent.setEndTime(reservation.getEndTime());
        rent.setUserId(reservation.getUserId());
        rent.setUsername(reservation.getUsername());
        rent.setLandlordName(reservation.getLandlordName());
        rentMapper.insert(rent);
        UpdateWrapper<House> houseUpdateWrapper = new UpdateWrapper<>();
        houseUpdateWrapper.eq("house_id",houseId);
        houseUpdateWrapper.set("status","已租");
        return Result.succ(reservationMapper.update(null,wrapper));
    }

    public Result addReservation(Integer userId, String username, Integer landlordId, String landlordName,
                                 Integer houseId, String houseName, String startTime, String endTime) {

        QueryWrapper<Rent> rentQueryWrapper = new QueryWrapper<>();
        rentQueryWrapper.eq("house_id",houseId).orderBy(false,true,"rent_id").last("limit 1");
        Rent rent = rentMapper.selectOne(rentQueryWrapper);
        if(rent != null){
            Boolean res = utils.CompareTwoDate(rent.getEndTime(),utils.getNowDate());
            if(res){
                return Result.fail("房屋已经租出去了");
            }
        }
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setUsername(username);
        reservation.setLandlordId(landlordId);
        reservation.setLandlordName(landlordName);
        reservation.setHouseId(houseId);
        reservation.setHouseName(houseName);
        reservation.setStartTime(utils.stringToDate(startTime));
        reservation.setEndTime(utils.stringToDate(endTime));
        reservationMapper.insert(reservation);
        return Result.succ("预约成功");


    }
}

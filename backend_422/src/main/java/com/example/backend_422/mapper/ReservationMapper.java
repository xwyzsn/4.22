package com.example.backend_422.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend_422.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {

}

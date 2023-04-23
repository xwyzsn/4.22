package com.example.backend_422.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend_422.common.Result;
import com.example.backend_422.entity.House;
import com.example.backend_422.mapper.HouseMapper;
import com.example.backend_422.service.HouseService;
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
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Autowired
    private HouseMapper houseMapper;
    public Result getAll() {
        return Result.succ(houseMapper.selectList(null));
    }

    public Result getMyHouse(Integer landlordId) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("landlord_id",landlordId);
        return Result.succ(houseMapper.selectList(wrapper));
    }

    public Result deleteByHouseId(Integer houseId) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("house_id",houseId);
        houseMapper.delete(wrapper);
        return Result.succ("null");
    }
}

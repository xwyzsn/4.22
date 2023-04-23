package com.example.backend_422.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend_422.common.Result;
import com.example.backend_422.entity.Rent;
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
}

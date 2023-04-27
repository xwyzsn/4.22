package com.example.backend_422.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend_422.common.Result;
import com.example.backend_422.common.Utils;
import com.example.backend_422.entity.Maintainance;
import com.example.backend_422.mapper.MaintainanceMapper;
import com.example.backend_422.service.MaintainanceService;
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
public class MaintainanceServiceImpl extends ServiceImpl<MaintainanceMapper, Maintainance> implements MaintainanceService {

    @Autowired
    private MaintainanceMapper maintainanceMapper;
    @Autowired
    private Utils utils;
    public Result getMaintainanceListByHouseList(List<Integer> houseList) {
        QueryWrapper<Maintainance> wrapper = new QueryWrapper<>();
        wrapper.in("house_id",houseList);
        return Result.succ(maintainanceMapper.selectList(wrapper));
    }

    public Result reportIssue(Integer homeId, String desc, Integer userId, String username, String houseName,String reportTime) {
        Maintainance maintainance = new Maintainance();
        maintainance.setHouseId(homeId);
        maintainance.setDesc(desc);
        maintainance.setUserId(userId);
        maintainance.setUsername(username);
        maintainance.setHouseName(houseName);
        maintainance.setStatus("未修复");
        maintainance.setReportTime(utils.stringToDate(reportTime));
        return Result.succ(maintainanceMapper.insert(maintainance));
    }

    public Result getMaintainanceListByUser(Integer userId) {
        QueryWrapper<Maintainance> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return Result.succ(maintainanceMapper.selectList(wrapper));
    }

    public Result updateStatus(Integer id, String status) {
        UpdateWrapper<Maintainance> wrapper = new UpdateWrapper<>();
        wrapper.eq("maintainance_id",id);
        wrapper.set("status",status);
        if(status.equals("已修复")){
            wrapper.set("finish_time",utils.getNowDate());
        }
        return Result.succ(maintainanceMapper.update(null,wrapper));
    }
}

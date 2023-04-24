package com.example.backend_422.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend_422.common.Result;
import com.example.backend_422.entity.House;
import com.example.backend_422.mapper.HouseMapper;
import com.example.backend_422.records.HouseRecord;
import com.example.backend_422.service.HouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.UUID;

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

    public Result addNewHouse(HouseRecord houseRecord) {
//        System.out.println(houseRecord.pic());
        House house = new House();

        if(houseRecord.pic() != null){
            UUID uuid = UUID.randomUUID();
            String filename = uuid.toString().replaceAll("-","");
            String originalFilename = houseRecord.pic().getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = filename + suffix;
            String path = "/Users/jan/Desktop/project-2/422/img/" + newFilename;
            File file = new File(path);
            try {
                houseRecord.pic().transferTo(file);
                house.setPic(newFilename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(houseRecord.houseName() != null){
            house.setName(houseRecord.houseName());
        }
        if(houseRecord.address() != null){
            house.setAddress(houseRecord.address());
        }
        if(houseRecord.area() != null){
            house.setArea(Float.parseFloat(houseRecord.area()));
        }
        if(houseRecord.description() != null){
            house.setDescription(houseRecord.description());
        }
        if(houseRecord.landlordId() != null){
            house.setLandlordId(houseRecord.landlordId());
        }
        if(houseRecord.landlordName() != null){
            house.setLandlordName(houseRecord.landlordName());
        }
        if(houseRecord.landlordPhone() != null){
            house.setLandlordPhone(houseRecord.landlordPhone());
        }
        if(houseRecord.powerPrice() != null){
            house.setPowerPrice(BigDecimal.valueOf(Float.parseFloat(houseRecord.powerPrice())));
        }
        if(houseRecord.rentPrice() != null){
            house.setRentPrice(BigDecimal.valueOf(Float.parseFloat(houseRecord.rentPrice())));
        }
        if(houseRecord.status() != null){
            house.setStatus(houseRecord.status());
        }
        if(houseRecord.waterPrice() != null){
            house.setWaterPrice(BigDecimal.valueOf(Float.parseFloat(houseRecord.waterPrice())));
        }
        houseMapper.insert(house);
        return Result.succ("null");
    }
}

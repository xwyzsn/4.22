package com.example.backend_422.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class Reservation {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;

    private String username;

    private Integer houseId;

    private String houseName;

    private Date startTime;

    private Date endTime;

    private Integer landlordId;

    private String landlordName;

    private String status;


}

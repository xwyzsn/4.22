package com.example.backend_422.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xwyzsn
 * @since 2023-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private Integer userId;

    private String username;

    private Integer houseId;

    private String houseName;

    private String status;

    private Date createdTime;

    private String userPhone;

    private Integer landlordId;
    private String landlordName;

    private String landlordPhone;

    private String address;



}

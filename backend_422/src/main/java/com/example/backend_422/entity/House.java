package com.example.backend_422.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class House implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "house_id", type = IdType.AUTO)
    private Integer houseId;

    private String name;

    private String pic;

    private String address;

    private BigDecimal rentPrice;

    private BigDecimal powerPrice;

    private BigDecimal waterPrice;

    private Float area;

    private String description;

    private String status;

    private  Integer landlordId;
    private  String landlordName;

    private  String landlordPhone;




}

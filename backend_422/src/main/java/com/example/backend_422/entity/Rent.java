package com.example.backend_422.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rent_id", type = IdType.AUTO)
    private Integer rentId;

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

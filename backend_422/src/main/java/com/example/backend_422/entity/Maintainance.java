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
public class Maintainance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "maintainance_id", type = IdType.AUTO)
    private Integer maintainanceId;

    private String desc;

    private Date reportTime;

    private Date finishTime;

    private String status;

    private Integer userId;

    private String username;

    private Integer houseId;

    private String houseName;


}

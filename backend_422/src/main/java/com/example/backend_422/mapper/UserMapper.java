package com.example.backend_422.mapper;

import com.example.backend_422.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xwyzsn
 * @since 2023-04-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

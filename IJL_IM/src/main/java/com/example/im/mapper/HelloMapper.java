package com.example.im.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.im.entity.Hello;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhongjunjie
 * @since 2021-10-04
 */
@Mapper
public interface HelloMapper extends BaseMapper<Hello> {

}

package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavoursMapper {

    /**
     * 批量插入口味数据
     * @param flavorList
     */
    public void insertBatch(List<DishFlavor> flavorList);
}

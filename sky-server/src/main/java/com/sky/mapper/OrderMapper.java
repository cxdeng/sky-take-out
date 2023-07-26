package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据订单状态和订单时间查询订单
     * @param status
     * @param orderTime
     * @return
     */
    List<Orders> getByStatusAndTimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 更新订单
     * @param order
     */
    void update(Orders order);
}

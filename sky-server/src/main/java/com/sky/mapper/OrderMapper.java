package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     *
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据订单状态和订单时间查询订单
     *
     * @param status
     * @param orderTime
     * @return
     */
    List<Orders> getByStatusAndTimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 更新订单
     *
     * @param order
     */
    void update(Orders order);

    /**
     * 根据id查询订单
     *
     * @param id
     * @return
     */
    Orders getById(Long id);


    /**
     * 查询某一天营业额
     * @param map
     * @return
     */
    Double sumByMap(Map<Object, Object> map);
}

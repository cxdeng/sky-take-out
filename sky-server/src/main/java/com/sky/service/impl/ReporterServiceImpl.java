package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.service.ReporterService;
import com.sky.vo.TurnoverReportVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReporterServiceImpl implements ReporterService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        // 当前集合用于存放从begin到end范围内的每天日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }


        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate date : dateList) {
            // 查date日期对应的营业额数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            // select sum(amount) from orders where_time > ? and order_time < ? and status = 5;
            Map<Object, Object> map = new HashMap<>();
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("status", Orders.COMPLETED);
            Double turnover = orderMapper.sumByMap(map);

            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }

        String dateListStr = StringUtils.join(dateList, ",");
        String turnoverListStr = StringUtils.join(turnoverList, ",");

        return TurnoverReportVO.builder()
                .dateList(dateListStr)
                .turnoverList(turnoverListStr)
                .build();
    }
}

package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReporterService;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Slf4j
@Api(tags = "数据统计相关接口")
@RequestMapping("/admin/report")
public class ReporterController {

    @Autowired
    private ReporterService reporterService;

    /**
     * 营业额统计
     *
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnoverStatistics(@RequestParam("begin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("营业额数据统计: {}, {}", begin, end);
        return Result.success(reporterService.getTurnoverStatistics(begin, end));
    }


    /**
     * 用户统计
     *
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("userStatistics")
    @ApiOperation("用户统计")
    public Result<UserReportVO> userStatistics(@RequestParam("begin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("用户数据统计: {}, {}", begin, end);
        return Result.success(reporterService.getUserStatistics(begin, end));
    }

}

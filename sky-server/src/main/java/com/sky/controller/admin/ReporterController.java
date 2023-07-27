package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReporterService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
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

import javax.servlet.http.HttpServletResponse;
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
    @GetMapping("/turnoverStatistics")
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
    @GetMapping("/userStatistics")
    @ApiOperation("用户统计")
    public Result<UserReportVO> userStatistics(@RequestParam("begin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("用户数据统计: {}, {}", begin, end);
        return Result.success(reporterService.getUserStatistics(begin, end));
    }


    @ApiOperation("订单统计统计")
    @GetMapping("/orderStatistics")
    public Result<OrderReportVO> orderStatistics(@RequestParam("begin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("订单统计统计: {}, {}", begin, end);
        return Result.success(reporterService.getOrderStatistics(begin, end));
    }


    @ApiOperation("销量排名top10")
    @GetMapping("/tio10")
    public Result<SalesTop10ReportVO>  top10(@RequestParam("begin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("销量排名top10: {}, {}", begin, end);
        return Result.success(reporterService.getSalesTop10(begin, end));
    }


    /**
     * 导出运营数据报表
     * @param response
     */
    @ApiOperation("导出运营数据报表")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        reporterService.exportBusinessData(response);
    }

}

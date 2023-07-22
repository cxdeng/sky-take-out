package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation(value = "员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("员工退出")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * 新增员工
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping()
    @ApiOperation("新增员工")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工: {}", employeeDTO);
        employeeService.save(employeeDTO);

        return Result.success();
    }


    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 启用禁用员工账号
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result startOrStop(@PathVariable("status") Integer status, @RequestParam("id") Long id) {
        log.info("启用禁用员工账号: {}, {}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }


    /**
     * 根据Id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据Id查询员工信息")
    public Result<Employee> getById(@PathVariable("id") Long id) {
        log.info("根据Id查询员工信息: {}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }


    @PutMapping
    @ApiOperation("编辑员工信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("编辑员工信息: {}", employeeDTO);
        employeeService.update(employeeDTO);

        return Result.success();
    }

}

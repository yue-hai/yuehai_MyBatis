package com.yuehai.mybatis.dao;

import com.yuehai.mybatis.bean.Employee;

/**
 * @author 月海
 * @create 2022/1/28 22:21
 */

// Mapper 接口
public interface EmployeeMapper {
    // 根据 id 查询
    public Employee getEmployeeById(Integer id);
}

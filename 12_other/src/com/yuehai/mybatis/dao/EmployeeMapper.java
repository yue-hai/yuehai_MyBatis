package com.yuehai.mybatis.dao;


import com.yuehai.mybatis.bean.Employee;

import java.util.List;

/**
 * @author 月海
 * @create 2022/1/28 22:21
 */

// Mapper 接口
public interface EmployeeMapper {
    // 根据 id 查询
    public Employee getEmployeeById(Integer id);
    // 查询所有员工
    public List<Employee> getEmps();
    // 添加员工
    public Long addEmp(Employee employee);
}

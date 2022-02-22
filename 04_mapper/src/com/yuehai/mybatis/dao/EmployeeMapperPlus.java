package com.yuehai.mybatis.dao;


import com.yuehai.mybatis.bean.Employee;

import java.util.List;

/**
 * @author 月海
 * @create 2022/1/31 15:05
 */

// Mapper 接口
public interface EmployeeMapperPlus {
    // 根据 id 查询
    public Employee getEmployeeById(Integer id);
    // 关联查询：Employee 与 Department，查询 Employee 的同时查询员工对应的部门
    public Employee getEmpAndDept(Integer id);
    // 根据 id 查询，分步查询
    public Employee getEmpByIdStep(Integer id);
    // 按照部门 id 查询多个员工
    public List<Employee> getEmpsByDeptId(Integer deptId);
}

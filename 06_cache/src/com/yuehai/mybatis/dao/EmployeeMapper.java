package com.yuehai.mybatis.dao;

import com.yuehai.mybatis.bean.Employee;

import java.util.List;

/**
 * @author 月海
 * @create 2022/2/1 21:33
 */

// mapper 接口文件
public interface EmployeeMapper {
    // 携带了哪个字段查询条件就带上这个字段的值，练习 if
    public List<Employee> getEmpsByConditionIf(Employee employee);
    // 根据 id 查询
    public Employee getEmpById(Integer id);
}

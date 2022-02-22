package com.yuehai.sm.dao;

import com.yuehai.sm.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 月海
 * @create 2022/2/1 21:33
 */

// mapper 接口文件
// 创建 bean 实例，表明是持久层
@Repository
public interface EmployeeMapper {
    // 查询所有员工
    public List<Employee> getEmps();
    // 批量添加
    public void addEmp(Employee employee);
}

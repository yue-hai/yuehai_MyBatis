package com.yuehai.sm.service;

import com.yuehai.sm.bean.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 月海
 * @create 2022/2/2 22:20
 */

public interface EmployeeService {
    // 查询所有员工
    public List<Employee> getEmps();
    // 批量添加
    public void addEmp();
}

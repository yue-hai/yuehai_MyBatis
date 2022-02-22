package com.yuehai.sm.service.impl;

import com.yuehai.sm.bean.Employee;
import com.yuehai.sm.dao.EmployeeMapper;
import com.yuehai.sm.service.EmployeeService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author 月海
 * @create 2022/2/2 22:21
 */

// 创建 bean 实例，表明是 Service 层
@Service
public class EmployeeServiceImpl implements EmployeeService {

    // 注入属性注解，按类型注入
    @Autowired
    private EmployeeMapper employeeMapper;

    // 注入属性注解，按类型注入，自动装配 sqlSession
    // 因在 applicationContext.xml 有配置，这个就是可以批量执行的 sqlSession
    @Autowired
    private SqlSession sqlSession;

    public List<Employee> getEmps() {
        // 调用 employeeMapper mapper接口文件的查询所有员工方法
        return employeeMapper.getEmps();
    }

    public void addEmp() {
        // 获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        // 调用接口的方法，批量添加员工
        for (int i = 0; i < 10; i++) {
            // UUID.randomUUID().toString().substring(0,5)：作为 lastName，取前 5 位
            mapper.addEmp(new Employee(null,UUID.randomUUID().toString().substring(0,5),"0","email"));
        }

    }
}

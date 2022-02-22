package com.yuehai.sm.Controller;

import com.yuehai.sm.bean.Employee;
import com.yuehai.sm.service.EmployeeService;
import com.yuehai.sm.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author 月海
 * @create 2022/2/2 22:16
 */

// 创建 bean 实例，表明是 Controller 层
@Controller
public class EmployeeController {

    // 注入属性注解，按类型注入
    @Autowired
    private EmployeeServiceImpl employeeService;

    // 查询所有员工
    @RequestMapping("/getemps")
    // 使用 Map 向 request 域对象共享数据
    public String getemps(Map<String,Object> map){
        // 调用 employeeService 的查询所有员工方法
        List<Employee> emps = employeeService.getEmps();
        // 向请求域共享数据，参数1为名称，参数2为值
        map.put("allEmps", emps);

        return "list";
    }

    // 批量添加
    @RequestMapping("/addEmp")
    // 使用 Map 向 request 域对象共享数据
    public String addEmp(Map<String,Object> map){
        // 调用 employeeService 的查询所有员工方法
        employeeService.addEmp();
        // 向请求域共享数据，参数1为名称，参数2为值
        map.put("addEmp", "添加成功");

        return "addEmp";
    }

}

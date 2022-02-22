package com.yuehai.mybatis.dao;


import com.yuehai.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 月海
 * @create 2022/1/28 22:21
 */

// Mapper 接口
public interface EmployeeMapperDynamicSQL {
    // 携带了哪个字段查询条件就带上这个字段的值，练习 if
    public List<Employee> getEmpsByConditionIf(Employee employee);
    // 携带了哪个字段查询条件就带上这个字段的值，练习 trim
    public List<Employee> getEmpsByConditionTrim(Employee employee);
    // 携带了哪个字段查询条件就带上这个字段的值，练习 choose
    public List<Employee> getEmpsByConditionChoose(Employee employee);
    // 修改（更新）字段，练习 trim 中的 set 标签
    public void updateEmp(Employee employee);
    // 传入 list 集合作为 id，来批量查询数据，练习 foreach
    public List<Employee> getEmpsByConditionForeach(@Param("ids")List<Integer> ids);
    // 传入 list 集合的 Employee 对象数据，来批量添加数据，练习 foreach
    public void addEmps(@Param("emps") List<Employee> emps);
    // 查询，练习 MyBatis 的两个内置参数
    public List<Employee> getEmpsTestInnerParameter(Employee employee);
    // 查询，练习 bind 的两个内置参数
    public List<Employee> getEmpsTestbind(Employee employee);
}

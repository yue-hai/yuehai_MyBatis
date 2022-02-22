package com.yuehai.mybatis.dao;


import com.yuehai.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 月海
 * @create 2022/1/28 22:21
 */

// Mapper 接口
public interface EmployeeMapper {
    // 添加
    public Long addEmp(Employee employee);
    // 根据 id 修改（更新）
    public Boolean updateEmp(Employee employee);
    // 根据 id 删除
    public Boolean deleteEmpById(Integer id);
    // 根据 id 查询
    public Employee getEmployeeById(Integer id);
    // 根据 id 和 姓氏查询
    public Employee getEmployeeByIdAndLastName(@Param("id") Integer id,@Param("lastName") String lastName);
    // 根据姓氏模糊查询
    public List<Employee> getEmpsByLastNameLike(String lastName);
    // 返回一条记录的 map 集合，key 是列名，值是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);
    // 多条记录封装一个 map 集合，键是这条记录的主键，值是记录封装后的 javaBean
    // @MapKey("id")：告诉 MyBatis 封装这个 map 的时候使用哪个属性作为主键
    @MapKey("id")
    public Map<Integer,Employee> getEmpsByLastNameLikeReturnMap(String lastName);
}

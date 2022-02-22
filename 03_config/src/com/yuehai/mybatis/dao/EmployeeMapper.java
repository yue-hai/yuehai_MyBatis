package com.yuehai.mybatis.dao;


import com.yuehai.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author 月海
 * @create 2022/1/28 22:21
 */

// Mapper 接口
public interface EmployeeMapper {
    // 根据 id 查询
    // mapper 映射，没有 sql 映射文件，所有的 sql 都是利用注解写在接口上
    // @Select("select id,last_name lastName,email,gender from tbl_employee where id = #{id}")
    public Employee getEmployeeById(Integer id);
}

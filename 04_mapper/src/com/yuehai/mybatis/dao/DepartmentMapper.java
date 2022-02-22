package com.yuehai.mybatis.dao;

import com.yuehai.mybatis.bean.Department;

/**
 * @author 月海
 * @create 2022/1/31 16:46
 */

// Mapper 接口
public interface DepartmentMapper {
    // 根据 id 查询部门
    public Department getDeptById(Integer id);
    // 根据 id 查询中所有的员工
    public Department getDeptByIdPlus(Integer id);
    // 根据 id 查询部门，分步查询
    public Department getDeptByIdStep(Integer id);
}

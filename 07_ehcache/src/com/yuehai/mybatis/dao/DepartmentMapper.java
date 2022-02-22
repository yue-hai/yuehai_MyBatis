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
}

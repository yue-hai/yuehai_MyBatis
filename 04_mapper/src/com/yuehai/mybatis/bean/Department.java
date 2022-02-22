package com.yuehai.mybatis.bean;

import java.util.List;

/**
 * @author 月海
 * @create 2022/1/31 15:35
 */
public class Department {
    // 部门 id
    private Integer id;
    // 部门名称
    private String departmentName;
    // 该部门下的所有员工
    private List<Employee> emps;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public List<Employee> getEmps() { return emps; }
    public void setEmps(List<Employee> emps) { this.emps = emps; }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", emps=" + emps +
                '}';
    }
}

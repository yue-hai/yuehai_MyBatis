package com.yuehai.mybatis.bean;

import java.io.Serializable;

/**
 * @author 月海
 * @create 2022/1/28 20:52
 */

// 使用@Alias注解为类指定一个别名，防止批量起别名时有相同的别名
// @Alias("employee")
public class Employee implements Serializable {
    // 序列化对象
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    private Department dept;

    public Employee() { }
    public Employee(Integer id, String lastName, String gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }
    public Employee(Integer id, String lastName, String gender, String email, Department dept) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dept = dept;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Department getDept() { return dept; }
    public void setDept(Department dept) { this.dept = dept; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

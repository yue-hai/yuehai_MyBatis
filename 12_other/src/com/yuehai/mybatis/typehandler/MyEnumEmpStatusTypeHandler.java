package com.yuehai.mybatis.typehandler;

/**
 * @author 月海
 * @create 2022/2/4 21:41
 */

import com.yuehai.mybatis.bean.EmpStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义类型处理器
 * 实现 TypeHandler 接口、或者继承 BaseTypeHandler
 */
public class MyEnumEmpStatusTypeHandler implements TypeHandler<EmpStatus> {

    // 定义当前数据如何保存到数据库中
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, EmpStatus empStatus, JdbcType jdbcType) throws SQLException {
        System.out.println("要保存的状态码："+ empStatus.getCode());
        // 将保存时默认保存的枚举的名字，改为状态码
        // empStatus.getCode().toString()：将 int 类型的状态码转为 String 类型
        preparedStatement.setString(i, empStatus.getCode().toString());
    }

    // 查询时修改查询到的数据，按数据库列名
    @Override
    public EmpStatus getResult(ResultSet resultSet, String s) throws SQLException {
        // 需要根据从数据库中拿到的枚举的状态码返回一个枚举对象
        int code = resultSet.getInt(s);
        System.out.println("从数据库中获取的状态码："+ code);
        EmpStatus status = EmpStatus.getEmpStatusByCode(code);
        return status;
    }

    // 查询时修改查询到的数据，按数据库列索引
    @Override
    public EmpStatus getResult(ResultSet resultSet, int i) throws SQLException {
        // 需要根据从数据库中拿到的枚举的状态码返回一个枚举对象
        int code = resultSet.getInt(i);
        System.out.println("从数据库中获取的状态码："+ code);
        EmpStatus status = EmpStatus.getEmpStatusByCode(code);
        return status;
    }

    // 查询时修改查询到的数据，调用的存储过程的获取方式
    @Override
    public EmpStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        // 需要根据从数据库中拿到的枚举的状态码返回一个枚举对象
        int code = callableStatement.getInt(i);
        System.out.println("从数据库中获取的状态码："+ code);
        EmpStatus status = EmpStatus.getEmpStatusByCode(code);
        return status;
    }
}

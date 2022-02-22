package com.yuehai.mybatis.test;

import com.yuehai.mybatis.bean.Employee;
import com.yuehai.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 月海
 * @create 2022/1/28 21:13
 */
public class MyBatisTest {

    /**
     * 1、获取 SqlSessionFactory 对象
     *      解析文件的每一个信息保存在 Configuration 中，
     *      返回包含 Configuration 的 DefaultSqlSession；
     * 	        注意：【MappedStatement】：代表一个增删改查的详细信息
     *
     * 2、获取 sqlSession 对象
     *      返回一个 DefaultSQlSession 对象，包含 Executor 和 Configuration;
     * 	    这一步会创建 Executor 对象；
     *
     * 3、获取接口的代理对象（MapperProxy）
     *      getMapper，使用 MapperProxyFactory 创建一个 MapperProxy 的代理对象
     * 	    代理对象里面包含了，DefaultSqlSession（Executor）
     *
     * 4、执行增删改查方法
     *
     * 总结：
     *      1、根据配置文件（全局，sql映射）初始化出Configuration对象
     *  	2、创建一个DefaultSqlSession对象，他里面包含Configuration以及
     *  		Executor（根据全局配置文件中的defaultExecutorType创建出对应的Executor）
     *      3、DefaultSqlSession.getMapper（）：拿到Mapper接口对应的MapperProxy；
     *      4、MapperProxy里面有（DefaultSqlSession）；
     *      5、执行增删改查方法：
     *   		1）、调用DefaultSqlSession的增删改查（Executor）；
     *   		2）、会创建一个StatementHandler对象。
     *   			（同时也会创建出ParameterHandler和ResultSetHandler）
     *   		3）、调用StatementHandler预编译参数以及设置参数值;
     *   			使用ParameterHandler来给sql设置参数
     *   		4）、调用StatementHandler的增删改查方法；
     *   		5）、ResultSetHandler封装结果
     * 注意：
     *   	四大对象每个创建的时候都有一个interceptorChain.pluginAll(parameterHandler);
     */

    @Test
    public void test() throws IOException {
        // 1、获取配置文件，根据配置文件获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        // 4、调用接口的方法
        Employee employeeById = mapper.getEmployeeById(1);
        // 打印
        System.out.println(employeeById);

        // 5、关闭数据库链接
        sqlSession.close();
    }
}

package com.yuehai.mybatis.test;

import com.yuehai.mybatis.bean.Employee;
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
     * MyBatis 使用步骤：
     * 1、根据 xml 配置文件（全局配置文件）创建一个 SqlSessionFactory 对象，配置文件中有数据源和一些运行环境的信息
     * 2、sql 映射文件，配置了每一个 sql，以及 sql 的封装规则等
     * 3、将 sql 映射文件注册在全局配置文件中
     * 4、写代码：
     *      ①：根据全局配置文件得到 SqlSessionFactory 工厂
     *      ②：使用 SqlSessionFactory 工厂获取到 sqlSession 对象，使用他来执行增删改查，一个 sqlSession 就是代表和数据库的一次会话，用完就要关闭
     *      ③：使用 sql 语句的唯一标识来告诉 MyBatis 执行哪一个 sql，sql 都是保存在 sql 映射文件中的
     *      ④：
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        // 1、获取配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3、调用方法
        // 参数1：sql 语句的唯一标识，MyBatis 配置文件中的 namespace + id
        // 参数2：执行 sql 要用的参数，占位符
        Employee employee = sqlSession.selectOne("com.yuehai.mybatis.bean.Employee.selectEmp", 1);

        // 打印
        System.out.println(employee);

        // 4、关闭数据库链接
        sqlSession.close();
    }
}

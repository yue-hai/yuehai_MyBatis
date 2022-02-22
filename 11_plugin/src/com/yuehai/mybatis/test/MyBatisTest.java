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

    // 将获取 SqlSessionFactory 的方法抽取出来，以便经常使用
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 插件原理
     * 在四大对象创建的时候
     * 1、每个创建出来的对象不是直接返回的，而是
     * 		interceptorChain.pluginAll(parameterHandler);
     * 2、获取到所有的Interceptor（拦截器）（插件需要实现的接口）；
     * 		调用interceptor.plugin(target);返回target包装后的对象
     * 3、插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面）
     * 		我们的插件可以为四大对象创建出代理对象；
     * 		代理对象就可以拦截到四大对象的每一个执行；
     *
     public Object pluginAll(Object target) {
         for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
         }
         return target;
     }
     */

    /**
     * 插件编写：
     * 1、编写 Interceptor 的实现类
     * 2、使用 @Intercepts 注解完成插件签名
     * 3、将写好的插件注册到全局配置文件中
     * 4、测试
     */
    @Test
    public void test2() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4、调用接口的方法
            Employee employeeById = mapper.getEmployeeById(1);

            // 5、打印测试
            System.out.println(employeeById);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

}

package com.yuehai.mybatis.test;

import com.yuehai.mybatis.bean.Department;
import com.yuehai.mybatis.bean.Employee;
import com.yuehai.mybatis.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * 1、MyBatis 允许增删改指定定义以下类型返回值：
     *      Integer、Long、Boolean、void
     * 2、我们需要手动提交数据
     *      sqlSessionFactory.openSession();     --> 手动提交
     *      sqlSessionFactory.openSession(true); --> 自动提交
     */
    @Test
    public void test() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 4、调用接口的方法，携带了哪个字段查询条件就带上这个字段的值，练习 if
            Employee employee = new Employee(1,null,"0",null);
            List<Employee> empsByConditionIf = mapper.getEmpsByConditionIf(employee);
            for (Employee emp : empsByConditionIf){
                System.out.println(emp);
            }

            // 5、手动提交数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    @Test
    public void test2() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 4、调用接口的方法，携带了哪个字段查询条件就带上这个字段的值，练习 trim
            Employee employee = new Employee(null,"A","0",null);
            List<Employee> empsByConditionIf = mapper.getEmpsByConditionTrim(employee);
            for (Employee emp : empsByConditionIf){
                System.out.println(emp);
            }

            // 5、手动提交数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    @Test
    public void test3() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 4、调用接口的方法，携带了哪个字段查询条件就带上这个字段的值，练习 choose
            Employee employee = new Employee(1,"A",null,null);
            List<Employee> empsByConditionIf = mapper.getEmpsByConditionChoose(employee);
            for (Employee emp : empsByConditionIf){
                System.out.println(emp);
            }

            // 5、手动提交数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    @Test
    public void test4() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 4、调用接口的方法，修改（更新）字段，练习 trim 中的 set 标签
            Employee employee = new Employee(6,"B","1","B@qq.com");
            mapper.updateEmp(employee);

            // 查询修改后的字段
            Employee employee2 = new Employee(null,null,null,null);
            List<Employee> empsByConditionIf = mapper.getEmpsByConditionChoose(employee2);
            for (Employee emp : empsByConditionIf){
                System.out.println(emp);
            }

            // 5、手动提交数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    @Test
    public void test5() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 4、调用接口的方法，传入 list 集合作为 id，来批量查询数据，练习 foreach
            List<Employee> emps = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3, 4, 5, 6));
            for (Employee emp : emps){
                System.out.println(emp);
            }

            // 5、手动提交数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    @Test
    public void test6() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 4、调用接口的方法，传入 list 集合的 Employee 对象数据，来批量添加数据，练习 foreach
            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null,"D","1","D@qq.com",new Department(1)));
            emps.add(new Employee(null,"E","1","E@qq.com",new Department(1)));
            mapper.addEmps(emps);

            // 查询修改后的字段
            Employee employee2 = new Employee(null,null,null,null);
            List<Employee> empsByConditionIf = mapper.getEmpsByConditionChoose(employee2);
            for (Employee emp : empsByConditionIf){
                System.out.println(emp);
            }

            // 5、手动提交数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    @Test
    public void test7() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 4、调用接口的方法，查询，练习 MyBatis 的两个内置参数
            Employee employee = new Employee();
            List<Employee> emps = mapper.getEmpsTestInnerParameter(employee);
            for (Employee emp : emps){
                System.out.println(emp);
            }

            // 5、手动提交数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    @Test
    public void test8() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 4、调用接口的方法，查询，练习 MyBatis 的两个内置参数
            Employee employee = new Employee();
            employee.setLastName("A");
            List<Employee> emps = mapper.getEmpsTestbind(employee);
            for (Employee emp : emps){
                System.out.println(emp);
            }

            // 5、手动提交数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }


}

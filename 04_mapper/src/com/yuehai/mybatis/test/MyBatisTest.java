package com.yuehai.mybatis.test;


import com.yuehai.mybatis.bean.Department;
import com.yuehai.mybatis.bean.Employee;
import com.yuehai.mybatis.dao.DepartmentMapper;
import com.yuehai.mybatis.dao.EmployeeMapper;
import com.yuehai.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4、调用接口的方法
            // 添加
            Employee employee = new Employee(null, "A", "0", "A@qq.com");
            Long addEmp = mapper.addEmp(employee);
            // 打印返回值
            System.out.println(employee.getId());

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
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4、调用接口的方法，根据 id 和 姓氏查询
            Employee employeeByIdAndLastName = mapper.getEmployeeByIdAndLastName(4, "A");
            // 打印返回值
            System.out.println(employeeByIdAndLastName);

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
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4、调用接口的方法，

            // 根据姓氏模糊查询
//            List<Employee> list = mapper.getEmpsByLastNameLike("%A%");
//            /**
//             * 结果：
//             * Employee{id=4, lastName='A', gender='0', email='A@qq.com'}
//             * Employee{id=6, lastName='A', gender='0', email='A@qq.com'}
//             * Employee{id=7, lastName='A', gender='0', email='A@qq.com'}
//             */
//            for(Employee employee : list){
//                System.out.println(employee);
//            }

            // 返回一条记录的 map 集合，key 是列名，值是对应的值
//            Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
//            /**
//             * 结果：
//             * {gender=0, last_name=tom, id=1, email=tom@qq.com}
//             */
//            System.out.println(map);

            // 多条记录封装一个 map 集合，键是这条记录的主键，值是记录封装后的 javaBean
            Map<Integer, Employee> map = mapper.getEmpsByLastNameLikeReturnMap("A");
            /**
             * 结果：
             * {4=Employee{id=4, lastName='A', gender='0', email='A@qq.com'},
             * 6=Employee{id=6, lastName='A', gender='0', email='A@qq.com'},
             * 7=Employee{id=7, lastName='A', gender='0', email='A@qq.com'}}
             */
            System.out.println(map);

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
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);

            // 4、调用接口的方法，根据 id 查询
            Employee employeeById = mapper.getEmployeeById(1);
            System.out.println(employeeById);

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
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);

            // 4、调用接口的方法，根据 id 查询
            Employee empAndDept = mapper.getEmpAndDept(1);
            System.out.println(empAndDept);
            System.out.println(empAndDept.getDept());

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
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);

            // 4、调用接口的方法，根据 id 查询，分步查询
            Employee empByIdStep = mapper.getEmpByIdStep(1);
            System.out.println(empByIdStep.getLastName());
            System.out.println(empByIdStep.getDept());

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
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            // 4、调用接口的方法，根据 id 查询中所有的员工
            Department deptByIdPlus = mapper.getDeptByIdPlus(1);
            System.out.println(deptByIdPlus);
            System.out.println(deptByIdPlus.getEmps());

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
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            // 4、调用接口的方法，根据 id 查询中所有的员工
            Department deptByIdStep = mapper.getDeptByIdStep(2);
            System.out.println(deptByIdStep.getDepartmentName());
            System.out.println(deptByIdStep.getEmps());

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

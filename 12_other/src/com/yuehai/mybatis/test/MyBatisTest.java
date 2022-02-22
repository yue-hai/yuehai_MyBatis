package com.yuehai.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuehai.mybatis.bean.EmpStatus;
import com.yuehai.mybatis.bean.Employee;
import com.yuehai.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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

    // 使用 MyBatis 原生的分页
    @Test
    public void test() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4、调用 MyBatis 的分页插件方法，返回值为分页的多个参数
            // 参数1：第几页；参数2：显示多少条数据
            Page<Object> page = PageHelper.startPage(2, 5);

            // 5、调用接口的方法，查询所有员工
            List<Employee> emps = mapper.getEmps();

            // 6、打印测试
            for(Employee emp : emps){
                System.out.println(emp);
            }
            /**
             * MyBatis 原生的分页参数：
             * 当前页码：page.getPageNum()
             * 总记录数：page.getTotal()
             * 每页的记录数：page.getPageSize()
             * 总页码：page.getPages()
             */
            System.out.println("当前页码：" + page.getPageNum());
            System.out.println("总记录数：" + page.getTotal());
            System.out.println("每页的记录数：" + page.getPageSize());
            System.out.println("总页码：" + page.getPages());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    // 使用 PageInfo 的分页
    @Test
    public void test2() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4、调用 MyBatis 的分页插件方法，返回值为分页的多个参数
            // 参数1：第几页；参数2：显示多少条数据
            Page<Object> page = PageHelper.startPage(5, 1);

            // 5、调用接口的方法，查询所有员工
            List<Employee> emps = mapper.getEmps();

            // 6、使用 PageInfo 包装查询出来的数据
            // 参数1：查询出来的数据；
            // 参数2：设置的 Navigatepage 的参数，连续显示的页数
            //        比如当前是第5页，则显示：34567
            //        比如当前是第7页，则显示：56789
            PageInfo<Employee> info = new PageInfo<>(emps,5);

            // 7、打印测试
            for(Employee emp : emps){
                System.out.println(emp);
            }

            /**
             * PageInfo 的分页参数：
             * 当前页码：info.getPageNum()
             * 总记录数：info.getTotal()
             * 每页的记录数：info.getPageSize()
             * 总页码：info.getPages()
             * 是否第一页：info.isIsFirstPage()
             * 连续显示的页码：info.getNavigatepageNums()，是个数组
             */
            System.out.println("当前页码："+info.getPageNum());
            System.out.println("总记录数："+info.getTotal());
            System.out.println("每页的记录数："+info.getPageSize());
            System.out.println("总页码："+info.getPages());
            System.out.println("是否第一页："+info.isIsFirstPage());
            System.out.println("连续显示的页码：");
            int[] nums = info.getNavigatepageNums();
            for (int i = 0; i < nums.length; i++) {
                System.out.println(nums[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    // 批量操作
    @Test
    public void test3() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 可以执行批量操作的 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

        try {
            // 获取程序运行开始的时间
            long start = System.currentTimeMillis();

            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4、调用接口的方法，批量添加员工
            for (int i = 0; i < 10000; i++) {
                // UUID.randomUUID().toString().substring(0,5)：作为 lastName，取前 5 位
                mapper.addEmp(new Employee(UUID.randomUUID().toString().substring(0,5),"0","email"));
            }

            // 5、关闭事务，手动提交数据
            // 批量：预编译sql一次 --> 设置参数 --> 10000次 --> 执行（1次）
            sqlSession.commit();

            // 获取程序运行结束的时间
            long end = System.currentTimeMillis();

            // 获取程序运行的总时间
            System.out.println("执行时长：" + (end-start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

    @Test
    public void testEnumUse(){
        EmpStatus login = EmpStatus.LOGIN;
        System.out.println("枚举的索引："+login.ordinal());
        System.out.println("枚举的名字："+login.name());

        System.out.println("枚举的状态码："+login.getCode());
        System.out.println("枚举的提示消息："+login.getMsg());
    }

    /**
     * 默认mybatis在处理枚举对象的时候保存的是枚举的名字：EnumTypeHandler
     * 改为保存索引：EnumOrdinalTypeHandler：
     */
    // 处理枚举类
    @Test
    public void test4() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4、调用接口的方法，添加员工
            // 由于使用了自定义的类型处理器，有意添加到数据库时
            Employee emp = new Employee("lastName","0","email");
            mapper.addEmp(emp);

            // 5、手动提交数据
            sqlSession.commit();

            System.out.println("保存成功：id 为：" + emp.getId());

            // 调用接口的方法，根据 id 查询
            Employee employeeById = mapper.getEmployeeById(emp.getId());

            System.out.println("用户状态为：" + employeeById.getEmpStatus());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            sqlSession.close();
        }
    }

}

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
     * 两级缓存：
     * 一级缓存：本地缓存
     *      与数据库同一次会话期间查询到的数据会放在本地缓存中
     *      以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库
     *
     *      一级缓存失效情况：
     *          1、SqlSession不同
     *          2、SqlSession相同，但是查询条件不同（当前一级缓存中还没有这个数据）
     *          3、SqlSession相同，两次查询期间执行了任何一次增删改操作
     *                  （本次增删改操作可能对当前数据有影响）
     *          4、SqlSession相同，两次查询期间手动清空了缓存
     *
     * 二级缓存：全局缓存，基于 namespace 级别的缓存，一个 namespace 对应一个二级缓存
     *      工作机制：
     *      1、一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中；
     * 		2、如果会话关闭；一级缓存中的数据会被保存到二级缓存中；
     *         新的会话查询信息，就可以参照二级缓存中的内容；
     * 		3、sqlSession 通过  EmployeeMapper 查询  Employee
     * 	       sqlSession 通过  DepartmentMapper 查询 Department
     * 		   不同 namespace 查出的数据会放在自己对应的缓存中（map）
     *
     * 		效果：从数据库查出的数据会被先放到一级缓存中，
     * 	      	 只有会话提交或者关闭以后，一级缓存中的数据才会转义到二级缓存中
     *
     * 	    顺序：程序会先去二级缓存查询，查不到再去一级缓存，都没有最后才去数据库查询
     *
     * 		使用：
     * 	        1、在全局配置文件中开启全局二级缓存配置：
     * 	            <setting name="cacheEnabled" value="true"/>
     * 	        2、在 sql 映射文件中配置使用二级缓存
     * 	            <cache />
     * 	        3、POJO 实体类需要实现序列化接口 Serializable
     * 	        4、关闭 sqlSession 后数据才会存入二级缓存
     *
     * 第三方缓存整合：
     * 	    1、导入第三方缓存包即可；
     * 	    2、导入与第三方缓存整合的适配包；官方有；
     * 	    3、mapper.xml 中使用自定义缓存
     * 	        <cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
     */
    // 演示二级缓存
    @Test
    public void test() throws IOException {
        // 1、获取 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2、获取 sqlSession 实例，能直接执行已经映射的 sql 语句
        // 没有参数的 openSession() 方法，获取到的 sqlSession 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取第二个 SqlSession 对象
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        // 3、获取接口类的对象，会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //    使用第二个 SqlSession 对象获取 第二个 mapper2 对象
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        // 4、调用接口的方法，根据 id 查询
        Employee emp = mapper.getEmpById(1);
        System.out.println(emp);
        // 5、关闭资源，关闭 sqlSession 后数据才会存入二级缓存
        sqlSession.close();

        // 再次进行这些数据的查询
        // 使用不同的 SqlSession 再次查询相同的数据
        Employee emp2 = mapper2.getEmpById(1);
        System.out.println(emp2);
        // 判断一下这两次的对象是否相等
        // 结果：false
        System.out.println(emp == emp2);
        // 关闭资源
        sqlSession2.close();
    }


}

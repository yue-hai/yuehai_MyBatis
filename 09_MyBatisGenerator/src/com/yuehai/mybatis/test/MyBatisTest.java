package com.yuehai.mybatis.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.yuehai.mybatis.bean.Employee;
import com.yuehai.mybatis.bean.EmployeeExample;
import com.yuehai.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/*import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;*/

public class MyBatisTest {

    // 运行逆向工程，创建 bean、mapper 接口、sql 映射文件
    @Test
    public void testMbg() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback, warnings);
        myBatisGenerator.generate(null);
    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    // 测试简单版的CRUD
	@Test
	public void testMyBatis3Simple() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.selectByExample(null);
			for (Employee employee : list) {
				System.out.println(employee.getId());
			}
		}finally{
			openSession.close();
		}
	}

    // 测试豪华版，生成有where标签的CRUD
	// QBC风格的带条件查询
	@Test
	public void testMyBatis3() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

			// xxxExample就是封装查询条件的
			// 1、查询所有
			// List<Employee> emps = mapper.selectByExample(null);

			// 2、查询员工名字中有 a 字母的，和员工性别是 0 的
			// 封装员工查询条件的 example
			EmployeeExample example = new EmployeeExample();
			// 创建一个Criteria，这个Criteria就是拼装查询条件
			// select id, last_name, email, gender, d_id from tbl_employee
			EmployeeExample.Criteria criteria = example.createCriteria();
			// 查询员工名字中有 a 字母的
			criteria.andLastNameLike("%a%");
			// 查询员工性别是 0 的
			criteria.andGenderEqualTo("0");

			// 创建一个新的拼装查询条件
			EmployeeExample.Criteria criteria2 = example.createCriteria();
			// 查询员工 Email 中有 a 字母的
			criteria2.andEmailLike("%a%");
			// 将第二个拼装查询条件也放到封装员工查询条件的 example 中
			example.or(criteria2);

			// 将封装完的查询条件传进来，并查询
			List<Employee> list = mapper.selectByExample(example);
			for (Employee employee : list) {
				System.out.println(employee.getId());
			}

		}finally{
			openSession.close();
		}
	}


}

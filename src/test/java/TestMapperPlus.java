import cn.alan.mapper.DeptMapper;

import cn.alan.mapper.EmployeeMapperPlus;
import cn.alan.entity.Dept;
import cn.alan.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


public class TestMapperPlus {

    // 根据XML配置文件，生成SqlSessionFactory实例
    public SqlSessionFactory getSqlSessionFactory(String resource) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    // 1. 查询单条数据,返回结果集用自定义的resultMap进行封装
    @Test
    public void test1() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmployeeById(1);

            System.out.println(employee);
        } finally {
            session.close();
        }
    }

    // 2. 联合查询：查询员工及对应的部门
    @Test
    public void test2() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmployeeAndDept(3);
            System.out.println(employee);
            System.out.println(employee.getDept());
        } finally {
            session.close();
        }
    }

    // 3. 使用association进行分步查询
    @Test
    public void test3() throws Exception {
        
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmployeeByIdStep(1);
            System.out.println(employee);
            System.out.println(employee.getDept());
        } finally {
            session.close();
        }
    }

    // 4. 查部门，并同时将部门下的员工也一并查出来
    @Test
    public void test4() throws Exception {

        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            DeptMapper mapper = session.getMapper(DeptMapper.class);
            Dept dept = mapper.getDeptWithEmployeeById(1);
            System.out.println(dept);
            System.out.println(dept.getEmployees());
        } finally {
            session.close();
        }
    }

    // 5. 使用分步式查询：查部门，并同时将部门下的员工也一并查出来
    @Test
    public void test5() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            DeptMapper mapper = session.getMapper(DeptMapper.class);
            Dept dept = mapper.getDeptWithEmployeeByIdStep(2);
            System.out.println(dept);
            System.out.println(dept.getEmployees());
        } finally {
            session.close();
        }
    }

    // 6. 使用鉴别器
    @Test
    public void test6() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmployeeById2(3);
            System.out.println(employee);
            System.out.println(employee.getDept());
        } finally {
            session.close();
        }
    }

}

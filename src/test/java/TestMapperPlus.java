import dao.EmployeeMapper;

import dao.EmployeeMapperPlus;
import entity.Employee;
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



}

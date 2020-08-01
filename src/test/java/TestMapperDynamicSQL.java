import cn.alan.mapper.EmployeeMapperDynamicSQL;
import cn.alan.entity.Dept;
import cn.alan.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestMapperDynamicSQL {

    // 根据XML配置文件，生成SqlSessionFactory实例
    public SqlSessionFactory getSqlSessionFactory(String resource) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    // 1. if-where 标签
    @Test
    public void test1() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null, "%jerry%", "女", null);
            List<Employee> list = mapper.getEmployeeByIf(employee);

            System.out.println(list);
        } finally {
            session.close();
        }
    }

    // 2. if-trim 标签
    @Test
    public void test2() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null, "%jerry%", "女", null);
            List<Employee> list = mapper.getEmployeeByTrim(employee);

            System.out.println(list);
        } finally {
            session.close();
        }
    }

    // 3. choose-when
    @Test
    public void test3() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null, "%jerry%", "女", null);
            List<Employee> list = mapper.getEmployeeByChoose(employee);

            System.out.println(list);
        } finally {
            session.close();
        }
    }

    // 4. update - set
    @Test
    public void test4() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(5, "Peter", "男", null);
            mapper.updateEmployee(employee);

            // 要手动调用提交
            session.commit();

        } finally {
            session.close();
        }
    }

    // 5. foreach
    @Test
    public void test5() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);

            List<Employee> list = mapper.getEmployeeByForEach(Arrays.asList(1, 2, 3, 4));

            System.out.println(list);

        } finally {
            session.close();
        }
    }

    // 6. foreach -- 批量新增
    @Test
    public void test6() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);

            List<Employee> list = new ArrayList<>();
            list.add(new Employee(null,"Jack1","男","jack1@163.com",new Dept(1)));
            list.add(new Employee(null,"Jack2","男","jack2@163.com",new Dept(2)));
            list.add(new Employee(null,"Rose","女","rose1@163.com",new Dept(1)));
            mapper.addEmployees(list);

            session.commit();

        } finally {
            session.close();
        }
    }

    // 7. 两个内部参数
    @Test
    public void test7() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);

            List<Employee> list = mapper.testInnerParam(new Employee(null,"Jerry",null,null));

            System.out.println(list);

        } finally {
            session.close();
        }
    }

}

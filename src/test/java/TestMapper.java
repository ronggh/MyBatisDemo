import cn.alan.mapper.EmployeeMapper;
import cn.alan.mapper.EmployeeMapperAnnotation;
import cn.alan.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1、接口式编程
 * 原生：		Dao		====>  DaoImpl
 * mybatis：	Mapper	====>  xxMapper.xml
 * <p>
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * （将接口和xml进行绑定）
 * EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * sql映射文件：保存了每一个sql语句的映射信息：
 * 将sql抽取出来。
 */

public class TestMapper {

    // 根据XML配置文件，生成SqlSessionFactory实例
    public SqlSessionFactory getSqlSessionFactory(String resource) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3、将sql映射文件注册在全局配置文件中
     * 4、写代码：
     * 1）、根据全局配置文件得到SqlSessionFactory；
     * 2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     * 一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     */
    @Test
    public void test1() throws IOException {
        // 1、获取sqlSession实例，能直接执行已经映射的sql语句
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 第一个参数：sql的唯一标识，从XML配置文件中读取，进行映射
            // 一般写XML中命名空间.语句的ID标识
            // 第二个参数：执行sql语句要用的参数
            Employee employee = sqlSession.selectOne(
                    "dao.EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    // 使用义接口的方式
    @Test
    public void test2() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test4() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmpById(2);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 测试增删改
     * 1、mybatis允许增删改直接定义以下类型返回值
     * Integer、Long、Boolean、void
     * 2、我们需要手动提交数据
     * sqlSessionFactory.openSession();===》手动提交
     * sqlSessionFactory.openSession(true);===》自动提交
     *
     * @throws IOException
     */
    @Test
    public void test5() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        //1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //测试添加
            Employee employee = new Employee(null, "jerry4", "女", "jerry4@gmail.com");
            mapper.addEmployee(employee);
            System.out.println(employee.getId());

            //测试修改
//            Employee employee = new Employee(3, "jerry", "女", "jerry@126.com");
//            boolean updateEmp = mapper.updateEmployee(employee);
//            System.out.println(updateEmp);
            //测试删除
            mapper.deleteEmployee(2);
            //2、手动提交数据
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    // 多参数查询
    public void test6() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeByIdAndLastName(1, "Tom1");
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    @Test
    // 多参数查询，使用命名参数方式
    public void test7() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeByIdAndLastName2(1, "Tom1");
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    @Test
    // 多参数查询，使用Map方式
    public void test8() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id",3);
            map.put("lastName","jerry");
            Employee employee = mapper.getEmployeeByMap(map);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    @Test
    // 查询结果为List
    public void test9() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.getByLastNameLike("%jerry%");
            for(Employee employee:employees){
                System.out.println(employee);
            }

        } finally {
            sqlSession.close();
        }

    }

    @Test
    // 查询单条记录，返回Map类型
    public void test10() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.getEmployeeByIdReturnMap(1);
            System.out.println(map);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    //  // 查询多条记录，返回Map类型，key为记录的主健值，value为Employee对象
    public void test11() throws Exception {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> map = mapper.getEmployeeByLastNameLikeReturnMap("%jerry%");
            System.out.println(map);
        } finally {
            sqlSession.close();
        }

    }



}

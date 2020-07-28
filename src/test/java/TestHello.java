import dao.EmployeeMapper;
import entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.InputStream;

public class TestHello {

    // 根据XML配置文件，生成SqlSessionFactory实例
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3、将sql映射文件注册在全局配置文件中
     * 4、写代码：
     * 		1）、根据全局配置文件得到SqlSessionFactory；
     * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     *
     */
    @Test
    public void test1() throws IOException {
        // 1、获取sqlSession实例，能直接执行已经映射的sql语句
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 第一个参数：sql的唯一标识，从XML配置文件中读取，进行映射
            // 一般写XML中命名空间.语句的ID标识
            // 第二个参数：执行sql语句要用的参数
            Employee employee = sqlSession.selectOne(
                    "EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    // 使用义接口的方式
    @Test
    public void test2() throws Exception{
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
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
}

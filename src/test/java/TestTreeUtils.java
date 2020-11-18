import cn.alan.entity.Employee;
import cn.alan.entity.MyNode;
import cn.alan.mapper.EmployeeMapperDynamicSQL;
import cn.alan.mapper.MyNodeMapper;
import cn.alan.servicer.MyNodeService;
import cn.alan.utils.MyTreeUtils;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.InputStream;

import java.util.List;

public class TestTreeUtils {
    // 根据XML配置文件，生成SqlSessionFactory实例
    public SqlSessionFactory getSqlSessionFactory(String resource) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void testTreeUtils() throws Exception {

        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis_config2.xml");
        // 2、获取sqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            MyNodeMapper mapper = session.getMapper(MyNodeMapper.class);

            List<MyNode> nodes = mapper.buildNodesFrom(3);
            if (null != nodes && nodes.size() > 0) {
//                System.out.println(nodes);
                List<MyNode> tree = MyTreeUtils.buildTree(nodes,3);
//                System.out.println(tree);
                System.out.println(JSON.toJSONString(tree));
            }
            else
            {
                System.out.println("null");
            }
        } finally {
            session.close();
        }

    }
}

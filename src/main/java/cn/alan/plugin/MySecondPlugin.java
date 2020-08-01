package cn.alan.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;
@Intercepts(
        {
                @Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
        })
public class MySecondPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MySecondPlugin...plugin:mybatis将要包装的对象"+target);
        Object wrap = Plugin.wrap(target,this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MySecondPlugin 插件配置的信息："+properties);
    }
}

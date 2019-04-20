package top.huzhurong.monitor.mybatis.plugin;

import com.alibaba.druid.pool.DruidPooledPreparedStatement;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * @author chenshun00@gmail.com
 * @since 2019/4/19
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@Slf4j
public class Apc implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        PreparedStatement preparedStatement;
        if (args[0] instanceof DruidPooledPreparedStatement) {
            preparedStatement = (DruidPooledPreparedStatement) args[0];
        } else {
            Field field = args[0].getClass().getSuperclass().getDeclaredField("h");
            field.setAccessible(true);
            PreparedStatementLogger preparedStatementLogger = (PreparedStatementLogger) field.get(args[0]);
            preparedStatement = preparedStatementLogger.getPreparedStatement();
        }
        int len = 39 + Integer.toHexString(preparedStatement.hashCode()).length();
        Object result = invocation.proceed();
        int size;
        if (result instanceof List) {
            size = ((List) result).size();
        } else {
            size = (int) result;
        }
        log.info("执行的sql语句:[{}] 受影响行数 [{}]", preparedStatement.toString().substring(len).trim().replaceAll("\n", "")
                .replaceAll("\\s+", " "), size);
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

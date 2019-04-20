### mybatis 拦截执行sql

```text
执行的sql语句:[delete from perform_data where id = 7] 受影响行数 [0]
执行的sql语句:[INSERT INTO perform_data ( context, type, meta_Id, add_Time ) VALUES ( '11', 'ggg', '123', '2019-04-20 10:33:09' )] 受影响行数 [1]
执行的sql语句:[SELECT id,context,type,meta_id,add_time FROM perform_data] 受影响行数 [493]
```


```text
    Object[] args = invocation.getArgs();
    PreparedStatement preparedStatement;
    //和具体的datasource实现有关
    if (args[0] instanceof DruidPooledPreparedStatement) {
        preparedStatement = (DruidPooledPreparedStatement) args[0];
    } else {
        Field field = args[0].getClass().getSuperclass().getDeclaredField("h");
        field.setAccessible(true);
        PreparedStatementLogger preparedStatementLogger = (PreparedStatementLogger) field.get(args[0]);
        preparedStatement = preparedStatementLogger.getPreparedStatement();
    }
```
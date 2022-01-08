package info.wufc.learning.design_pattern.factory.factorymethod.demonstrate;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: QueryRunner
 * @Description: 查询运行者
 * @Info: createdBy alien on 2019/10/19/019 14:00
 */
public abstract class QueryRunner {
    public void run() throws SQLException {
        Connection connection = createConnection();
        String sql = createSql();
        run(connection, sql);
    }

    protected abstract Connection createConnection();

    protected abstract String createSql();

    protected abstract void run(Connection connection, String sql) throws SQLException;

}

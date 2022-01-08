package info.wufc.learning.design_pattern.factory.factorymethod.demonstrate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName: SysBaseQueryRunner
 * @Description: 具体生产者，以及通过模版方法组合的逻辑
 * @Info: createdBy alien on 2019/10/19/019 14:04
 */
public class SysBaseQueryRunner extends QueryRunner {
    @Override
    protected Connection createConnection() {
        // 示意性代码
        return null;
    }

    @Override
    protected String createSql() {
        return null;
    }

    @Override
    protected void run(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }
}

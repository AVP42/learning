package info.wufc.learning.design_pattern.factory.factorymethod.demonstrate;

import java.sql.SQLException;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 14:06
 */
public class Client {
    public static void main(String[] args) throws SQLException {
        new OracleQueryRunner().run();
        new SysBaseQueryRunner().run();
    }

}

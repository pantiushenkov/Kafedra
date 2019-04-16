package lib.java.dao2.config;

import lib.java.Utils.UniverConst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(UniverConst.JDBC_URL, UniverConst.USER, UniverConst.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

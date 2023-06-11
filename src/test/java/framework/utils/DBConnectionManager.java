package framework.utils;

import java.sql.*;

public class DBConnectionManager {
    private static final String host = JsonDataUtils.getConfigStringValueByKey("host");
    private static final String port = JsonDataUtils.getConfigStringValueByKey("port");
    private static final String dbName = JsonDataUtils.getConfigStringValueByKey("dbname");
    private static final String username = JsonDataUtils.getConfigStringValueByKey("username");
    private static final String password = JsonDataUtils.getConfigStringValueByKey("password");
    private static final String dbUrl = String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
    private static volatile Connection connection  = null;

    private DBConnectionManager() {}

    public static Connection dbConnection(){
        if (connection == null) {
            synchronized (DBConnectionManager.class) {
                if (connection==null) {
                    try {
                        connection = DriverManager.getConnection(dbUrl,username, password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    public static void executeUpdateStatement(String query){
        try {
            Statement stmt = DBConnectionManager.dbConnection().createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

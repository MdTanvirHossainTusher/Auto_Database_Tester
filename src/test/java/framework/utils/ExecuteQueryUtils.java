package framework.utils;

import aquality.selenium.core.logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteQueryUtils {
    public static ResultSet executeQuery(String query){

        ResultSet resultSet = null;

        try {
            Statement statement = DBConnectionManager.dbConnection().createStatement();
            resultSet = statement.executeQuery(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
        return resultSet;
    }
}

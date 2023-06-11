package framework.utils;


import aquality.selenium.core.logging.Logger;
import framework.enums.AttributesEnum;
import testing.model.Author;
import testing.model.TestTable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CRUDUtils {

    private static final String tempTableName = "temp_test";

    public static List<TestTable> getRepeatedDigitsRecords (){

        List<TestTable> testTableList = new ArrayList<>();

        try {

            String query = QueryUtils.getRepeatedDigitsRecords();
            ResultSet resultSet = ExecuteQueryUtils.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString(AttributesEnum.NAME.getColumnName());
                int statusId = resultSet.getInt(AttributesEnum.STATUS_ID.getColumnName());
                String methodName = resultSet.getString(AttributesEnum.METHOD_NAME.getColumnName());
                long projectId = resultSet.getLong(AttributesEnum.PROJECT_ID.getColumnName());
                long sessionId = resultSet.getLong(AttributesEnum.SESSION_ID.getColumnName());
                Timestamp startTime = resultSet.getTimestamp(AttributesEnum.START_TIME.getColumnName());
                Timestamp endTime = resultSet.getTimestamp(AttributesEnum.END_TIME.getColumnName());
                String env = resultSet.getString(AttributesEnum.ENV.getColumnName());
                String browser = resultSet.getString(AttributesEnum.BROWSER.getColumnName());
                long authorId = resultSet.getLong(AttributesEnum.AUTHOR_ID.getColumnName());

                TestTable testTable = new TestTable(name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);
                testTableList.add(testTable);

            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
        return testTableList;
    }


    public static void copyAndPasteRecords(List<TestTable> records) {

        try{
            for(TestTable record: records) {
                String query = QueryUtils.copyRecord(record);
                DBConnectionManager.executeUpdateStatement(query);
            }

        }catch (Exception e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
    }


    public static long isAuthorExists(){

        long authorId = -1;

        try {

            String authorName = JsonDataUtils.getStringValueByKey("authorName");
            String query = QueryUtils.findAuthor(authorName);
            ResultSet resultSet = ExecuteQueryUtils.executeQuery(query);

            if (resultSet.next()) {
                authorId = resultSet.getInt("id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
        return authorId;
    }

    public static void insertAuthorData(){

        try {

            String authorName = JsonDataUtils.getStringValueByKey("authorName");
            String userName = JsonDataUtils.getStringValueByKey("userName");
            String email = JsonDataUtils.getStringValueByKey("email");

            Author author = new Author(authorName, userName, email);

            String query = QueryUtils.insertAuthorData(author);
            DBConnectionManager.executeUpdateStatement(query);
        }
        catch (Exception e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
    }

    public static void addPrevTestRecords(String projectName, int statusId, String testMethodName, long projectId, long sessionId,
                           Timestamp startTime, Timestamp endTime, String env, String browserName){

        try {

            long authorId = isAuthorExists();

            if(authorId == -1){

                insertAuthorData();
                authorId = isAuthorExists();
            }

            TestTable testTable = new TestTable(projectName, statusId, testMethodName, projectId, sessionId, startTime, endTime, env, browserName, authorId);

            String query = QueryUtils.addRecord(testTable);
            DBConnectionManager.executeUpdateStatement(query);

        }
        catch (Exception e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
    }


    public static void updateRecords(String projectName, String testMethodName, long projectId, long sessionId, String env) {
        try {

            String query = QueryUtils.getRecordsAsDescOrder(JsonDataUtils.getIntValueByKey("getTableRecords"));
            ResultSet resultSet = ExecuteQueryUtils.executeQuery(query);

            while (resultSet.next()) {

                long id = resultSet.getLong("id");

                if (id > JsonDataUtils.getIntValueByKey("totalTableRecords") + 1) {

                    query = QueryUtils.updateRecords(id, projectName, testMethodName, projectId, sessionId, env);
                    DBConnectionManager.executeUpdateStatement(query);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
    }


    public static boolean isUpdated(boolean isAdded, boolean isRepeatedDataUpdated){
        boolean isUpdated = true;
        String query = null;

        try {

            if(isRepeatedDataUpdated) query = QueryUtils.getRecordsAsDescOrder(JsonDataUtils.getIntValueByKey("getTableRecords"));
            else if(isAdded) query = QueryUtils.getRecordsAsDescOrder(JsonDataUtils.getIntValueByKey("getRecord"));

            ResultSet resultSet = ExecuteQueryUtils.executeQuery(query);

            while (resultSet.next()){

                String projectName = JsonDataUtils.getStringValueByKey("projectName");
                String name = resultSet.getString("name");

                if(!projectName.equalsIgnoreCase(name)){
                    isUpdated = false;
                }
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
        return isUpdated;
    }


    public static boolean isRecordsDeleted(){
        boolean isDeleted = false;
        try {
            String query = QueryUtils.getRecordsAsDescOrder(JsonDataUtils.getIntValueByKey("getRecord"));
            ResultSet resultSet = ExecuteQueryUtils.executeQuery(query);

            while (resultSet.next()){

                long id = resultSet.getLong("id");

                if(JsonDataUtils.getIntValueByKey("totalTableRecords") + 1 <= id){
                    isDeleted = true;
                }
            }
            resultSet.close();
            DBConnectionManager.dbConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
        return isDeleted;
    }


    public static void deleteCopiedRecords(){

        try {

            Connection connection = DBConnectionManager.dbConnection();

            String createTempTableQuery = QueryUtils.tempTableQuery(tempTableName);
            connection.createStatement().executeUpdate(createTempTableQuery);

            String deleteQuery = QueryUtils.deleteRecord(tempTableName);
            connection.createStatement().executeUpdate(deleteQuery);

            String dropTempTableQuery = QueryUtils.dropTable(tempTableName);
            connection.createStatement().executeUpdate(dropTempTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
    }
}

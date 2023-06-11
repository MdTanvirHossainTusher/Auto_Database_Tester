package framework.utils;


import testing.model.Author;
import testing.model.TestTable;


public class QueryUtils {

    private static final String projectName = JsonDataUtils.getStringValueByKey("projectName");
    private static final long authorId = CRUDUtils.isAuthorExists();
    private static String query = null;

    public static String copyRecord(TestTable record){

        query = String.format("INSERT INTO Test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) " +
                        "VALUES ('%s', %d, '%s', %d, %d, '%s', '%s', '%s', '%s', %d)", projectName, record.getStatusId(),
                record.getMethodName(), record.getProjectId(), record.getSessionId(), record.getStartTime(),
                record.getEndTime(), record.getEnv(), record.getBrowser(), authorId);

        return query;
    }

    public static String addRecord(TestTable record){
        query = String.format("INSERT INTO Test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) " +
                        "VALUES ('%s', %d, '%s', %d, %d, '%s', '%s', '%s', '%s', %d)", record.getName(), record.getStatusId(),
                record.getMethodName(), record.getProjectId(), record.getSessionId(), record.getStartTime(),
                record.getEndTime(), record.getEnv(), record.getBrowser(), record.getAuthorId());
        return query;
    }

    public static String updateRecords(long id, String projectName, String testMethodName, long projectId, long sessionId, String env){
        return String.format("UPDATE Test SET name = '%s', method_name = '%s', project_id = %d, session_id = %d, env = '%s' WHERE id = %d", projectName,
                testMethodName, projectId, sessionId, env, id);
    }

    public static String getRepeatedDigitsRecords(){
        return "SELECT * FROM Test WHERE id REGEXP '([0-9])\\\\1' LIMIT 10";
    }

    public static String getRecordsAsDescOrder(int limit){
        return String.format("SELECT * FROM test ORDER BY id DESC LIMIT %d", limit);
    }

    public static String tempTableQuery(String tableName){
        return String.format("CREATE TEMPORARY TABLE %s SELECT id FROM Test ORDER BY id DESC LIMIT 10", tableName);
    }

    public static String deleteRecord(String tableName){
        return String.format("DELETE FROM Test WHERE id IN (SELECT id FROM %s)", tableName);
    }

    public static String dropTable(String tableName){
        return String.format("DROP TABLE %s", tableName);
    }

    public static String findAuthor(String authorName){
        return String.format("SELECT id FROM author WHERE name = '%s'", authorName);
    }

    public static String insertAuthorData(Author author){
        return String.format("INSERT INTO author (name, login, email) VALUES ('%s', '%s', '%s')", author.getName(), author.getUsername(),
                author.getEmail());
    }
}

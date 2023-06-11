package testing.testcase;

import framework.utils.CRUDUtils;
import framework.utils.JsonDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import testing.model.TestTable;
import java.util.List;


public class DBTest{
    @Test
    public void executeCRUD(){

        List<TestTable> records = CRUDUtils.getRepeatedDigitsRecords();
        CRUDUtils.copyAndPasteRecords(records);

        String projectName = JsonDataUtils.getStringValueByKey("projectName");
        String testMethodName = JsonDataUtils.getStringValueByKey("methodName");
        long projectId = JsonDataUtils.getIntValueByKey("projectId");
        long sessionId = JsonDataUtils.getIntValueByKey("sessionId");
        String env = JsonDataUtils.getStringValueByKey("environment");

        CRUDUtils.updateRecords(projectName, testMethodName, projectId, sessionId, env);
        Assert.assertTrue(CRUDUtils.isUpdated(false, true), "Tests are not completed, information is not updated");

        CRUDUtils.deleteCopiedRecords();
        Assert.assertTrue(CRUDUtils.isRecordsDeleted(), "The records have not been deleted");
    }
}

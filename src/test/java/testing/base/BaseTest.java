package testing.base;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.utils.CRUDUtils;
import framework.utils.JsonDataUtils;
import framework.utils.TimeUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.sql.Timestamp;


public abstract class BaseTest {

    private final Browser browser = AqualityServices.getBrowser();
    private final String browserName = String.valueOf(AqualityServices.getBrowser().getBrowserName());
    private Timestamp startTime = null;


    @BeforeMethod
    public void setup(){
        startTime = TimeUtils.getTime();

        browser.maximize();
        browser.goTo(JsonDataUtils.getUrl("mainUrl"));
    }

    @AfterMethod
    public void teardown(ITestResult result){
        browser.quit();

        String testMethodName = result.getInstanceName();
        int testStatus = result.getStatus();

        Timestamp endTime = TimeUtils.getTime();

        String projectName = JsonDataUtils.getStringValueByKey("projectName");
        long projectId = JsonDataUtils.getIntValueByKey("projectId");
        long sessionId = JsonDataUtils.getIntValueByKey("sessionId");
        String env = JsonDataUtils.getStringValueByKey("environment");

        CRUDUtils.addPrevTestRecords(projectName, testStatus, testMethodName, projectId, sessionId, startTime, endTime, env, browserName);
        Assert.assertTrue(CRUDUtils.isUpdated(true, false), "Information not added");
    }
}

package framework.utils;

import org.json.simple.JSONObject;
import testing.utils.FileManager;

import static framework.utils.ReadJsonFileUtils.readJsonFile;

public class TestDataUtils {

    private TestDataUtils(){}

    public static JSONObject readTestJsonFile() {
        return readJsonFile(FileManager.TEST_DATA_FILE_PATH);
    }
}

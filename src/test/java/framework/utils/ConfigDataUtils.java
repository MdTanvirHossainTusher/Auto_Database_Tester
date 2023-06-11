package framework.utils;


import org.json.simple.JSONObject;
import testing.utils.FileManager;

import static framework.utils.ReadJsonFileUtils.readJsonFile;

public class ConfigDataUtils {

    private ConfigDataUtils(){}

    public static JSONObject readConfigStringValueByKey() {
        return readJsonFile(FileManager.CONFIG_DATA_FILE_PATH);
    }
    public static JSONObject readConfigUrls() {
        return readJsonFile(FileManager.CONFIG_URL_FILE_PATH);
    }

    public static JSONObject readConfigEndPoints() {
        return readJsonFile(FileManager.CONFIG_ENDPOINT_FILE_PATH);
    }
}

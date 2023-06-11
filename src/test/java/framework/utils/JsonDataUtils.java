package framework.utils;


public class JsonDataUtils {

    public static String getUrl(String key){
        return (String) ConfigDataUtils.readConfigUrls().get(key);
    }

    public static String getConfigEndPoint(String key) {
        return (String) ConfigDataUtils.readConfigEndPoints().get(key);
    }

    public static String getConfigStringValueByKey(String key) {
        return (String) ConfigDataUtils.readConfigStringValueByKey().get(key);
    }

    public static String getStringValueByKey(String key) {
        return (String) TestDataUtils.readTestJsonFile().get(key);
    }

    public static int getIntValueByKey(String key) {
        return (int)(long) TestDataUtils.readTestJsonFile().get(key);
    }
}

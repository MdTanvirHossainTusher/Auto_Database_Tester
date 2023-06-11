package framework.utils;

import aquality.selenium.core.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ReadJsonFileUtils {

    private static JSONObject jsonData;

    private ReadJsonFileUtils(){}

    public static JSONObject readJsonFile(String filename){
        try {
            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader(filename);
            Object obj = parser.parse(fileReader);
            jsonData = (JSONObject) obj;
            fileReader.close();
        } catch (Exception e) {
            Logger.getInstance().error(e.getMessage());
            e.printStackTrace();
        }
        return jsonData;
    }
}

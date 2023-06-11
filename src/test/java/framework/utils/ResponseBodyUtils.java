package framework.utils;

import framework.enums.ClientCredentialsEnum;
import java.util.HashMap;
import java.util.Map;

public class ResponseBodyUtils {
    public static Map<String, String> getResponseBody(){

        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("refresh_token", ClientCredentialsEnum.REFRESH_TOKEN.getClientCredentialsValue());
        requestBody.put("client_id", ClientCredentialsEnum.CLIENT_ID.getClientCredentialsValue());
        requestBody.put("client_secret", ClientCredentialsEnum.CLIENT_SECRET.getClientCredentialsValue());
        requestBody.put("grant_type", ClientCredentialsEnum.GRANT_TYPE.getClientCredentialsValue());

        return requestBody;
    }
}

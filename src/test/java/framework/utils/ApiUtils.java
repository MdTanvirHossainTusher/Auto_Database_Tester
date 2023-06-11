package framework.utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testing.model.AccessTokenResponse;
import testing.model.MessageDetails;


public class ApiUtils {

    private static final String apiUrl = JsonDataUtils.getUrl("apiUrl");
    private static final String messageEndPoint = JsonDataUtils.getConfigEndPoint("messagesEndPoint");

    static {
        RestAssured.baseURI = apiUrl;
    }

    public static Response sendGetReqForAuth(String accessToken, String messageId) {
        String endpoint = messageId.equals("") ? messageEndPoint : messageEndPoint.concat(messageId);
        return RestAssured
                .given()
                .auth().oauth2(accessToken)
                .when()
                .get(endpoint);
    }

    public static Response sendGetReqForAllMessages(String accessToken) {
        return sendGetReqForAuth(accessToken, "");
    }

    public static MessageDetails sendGetReqForSpecificMsgId(String accessToken, String messageId) {
        String endpoint = messageEndPoint + messageId;

        return RestAssured.given()
                .auth().oauth2(accessToken)
                .when()
                .get(endpoint)
                .as(MessageDetails.class);
    }

    public static String generateAccessToken() {

        AccessTokenResponse response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParams(ResponseBodyUtils.getResponseBody())
                .when()
                .post(JsonDataUtils.getUrl("googleAuthUrl"))
                .then()
                .extract()
                .response()
                .as(AccessTokenResponse.class);

        return response.getAccessToken();
    }
}

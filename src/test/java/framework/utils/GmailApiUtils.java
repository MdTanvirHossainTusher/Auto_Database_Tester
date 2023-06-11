package framework.utils;


import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import testing.model.Header;
import testing.model.MessageDetails;
import testing.model.Part;
import java.nio.charset.StandardCharsets;

public class GmailApiUtils {

    public static JSONArray getMessages(String accessToken){

        Response response = ApiUtils.sendGetReqForAllMessages(accessToken);
        JSONObject json = new JSONObject(response.asString());

        return json.getJSONArray("messages");
    }


    public static String getDesiredFromEmail(Header[] payloadHeaders){
        String from = "";

        for(Header header : payloadHeaders){
            if(header.getName().equals("From")){
                from = header.getValue();
                break;
            }
        }
        return from;
    }


    public static String getEmailBody(MessageDetails messageResponse, String fromAddress){

        String emailBody = "";

        if (fromAddress.contains(JsonDataUtils.getStringValueByKey("subscriptionEmail"))) {

            Part[] payloadParts = messageResponse.getPayload().getParts();
            String body = "";

            for(Part part: payloadParts){
                if(part.getMimeType().equals("text/plain") || part.getMimeType().equals("text/html")){
                    body = part.getPartBody().getData();
                    break;
                }
            }
            emailBody =  new String(Base64.decodeBase64(body), StandardCharsets.UTF_8);
        }
        return emailBody;
    }


    public static String accessGmailContent()  {

        String emailBody = "";

        String apiToken = ApiUtils.generateAccessToken();
        String accessToken = String.valueOf(apiToken);

        JSONArray messages = getMessages(accessToken);

        for (int i = 0; i < 1; i++) {

            JSONObject message = messages.getJSONObject(i);
            String messageId = message.getString("id");

            MessageDetails messageResponse = ApiUtils.sendGetReqForSpecificMsgId(accessToken, messageId);

            Header[] payloadHeaders = messageResponse.getPayload().getHeaders();

            String fromAddress = getDesiredFromEmail(payloadHeaders);
            emailBody = getEmailBody(messageResponse,fromAddress);
        }
        return emailBody;
    }
}

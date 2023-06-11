package framework.enums;

import testing.utils.ClientCredentials;

public enum ClientCredentialsEnum {

    CLIENT_ID(ClientCredentials.CLIENT_ID),
    CLIENT_SECRET(ClientCredentials.CLIENT_SECRET),
    REFRESH_TOKEN(ClientCredentials.REFRESH_TOKEN),
    GRANT_TYPE(ClientCredentials.GRANT_TYPE);

    final String clientCredentialsValue;

    ClientCredentialsEnum(String clientCredentialsValue){
        this.clientCredentialsValue = clientCredentialsValue;
    }

    public String getClientCredentialsValue() {
        return clientCredentialsValue;
    }
}

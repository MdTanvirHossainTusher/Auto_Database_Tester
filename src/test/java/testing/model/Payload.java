package testing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    @JsonProperty("partId")
    private String partId;

    @JsonProperty("mimeType")
    private String mimeType;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("headers")
    private Header[] headers;

    @JsonProperty("body")
    private PayloadBody body;

    @JsonProperty("parts")
    private Part[] parts;
}

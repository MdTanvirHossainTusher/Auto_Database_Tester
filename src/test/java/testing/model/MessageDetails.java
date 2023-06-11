package testing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDetails {

    @JsonProperty("id")
    private String id;

    @JsonProperty("threadId")
    private String threadId;

    @JsonProperty("labelIds")
    private List<String> labelIds;

    @JsonProperty("snippet")
    private String snippet;

    @JsonProperty("payload")
    private Payload payload;

    @JsonProperty("sizeEstimate")
    private String sizeEstimate;

    @JsonProperty("historyId")
    private String historyId;

    @JsonProperty("internalDate")
    private String internalDate;
}
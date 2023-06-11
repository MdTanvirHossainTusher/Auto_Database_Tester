package testing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header {
    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;
}

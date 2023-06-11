package testing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartsBody {

    @JsonProperty("size")
    private String size;

    @JsonProperty("data")
    private String data;
}
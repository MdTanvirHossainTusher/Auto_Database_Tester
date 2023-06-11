package testing.model;

import lombok.*;
import java.sql.Timestamp;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestTable {

    private String name;
    private int statusId;
    private String methodName;
    private long projectId;
    private long sessionId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String env;
    private String browser;
    private long authorId;
}

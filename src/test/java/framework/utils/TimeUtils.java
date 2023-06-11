package framework.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    public static Timestamp getTime(){

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedStartDateTime = dateTime.format(formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(formattedStartDateTime, formatter);

        return Timestamp.valueOf(localDateTime);
    }
}

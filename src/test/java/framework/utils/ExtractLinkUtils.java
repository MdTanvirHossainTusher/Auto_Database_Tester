package framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractLinkUtils {

    public static String extractLink(String body) {

        Pattern pattern = Pattern.compile("href=\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(body);

        String link = null;

        if (matcher.find()) {
            link = matcher.group(1);
        }
        return link;
    }

    public static String getLink(String body) {

        String link = null;
        try {
            link = extractLink(body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return link;
    }
}

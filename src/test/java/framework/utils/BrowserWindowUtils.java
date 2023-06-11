package framework.utils;

import aquality.selenium.browser.AqualityServices;
import java.util.Set;

public class BrowserWindowUtils {
    public static void switchToUnsubscribeTab(){

        String currentTab = AqualityServices.getBrowser().tabs().getCurrentTabHandle();
        Set<String> tabs = AqualityServices.getBrowser().tabs().getTabHandles();

        for (String tab : tabs) {
            if (!tab.equals(currentTab)) {
                AqualityServices.getBrowser().tabs().closeTab();
                AqualityServices.getBrowser().tabs().switchToTab(tab);
                break;
            }
        }
    }
}

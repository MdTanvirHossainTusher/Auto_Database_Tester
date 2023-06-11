package testing.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SubscriptionConfirmedPage extends Form {

    private static final String siteBtnLocator = "//a[contains(@class,'c-link--btn')]";
    private final IButton backToSiteBtn = getElementFactory().getButton(By.xpath(siteBtnLocator),"Back to site");

    public SubscriptionConfirmedPage() {
        super(By.xpath(siteBtnLocator), "Subscription confirmed form");
    }

    public void clickBackToSiteBtn(){
        backToSiteBtn.click();
    }
}

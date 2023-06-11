package testing.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class HomePage extends Form {

    private final ILabel newsletters = getElementFactory().getLabel(By.xpath("//span[contains(@data-event,'newsletter-link-header')]"),"Newsletters");
    private final IButton agreeClosePopupBtn = getElementFactory().getButton(By.xpath("//button[contains(@id,'agree')]"),"Agree close popup button");

    public HomePage() {
        super(By.xpath("//select[contains(@class,'c-select__element')]"), "Home Page");
    }

    public void clickOnNewsLetters(){
        newsletters.clickAndWait();
    }

    public void clickOnAgreeClosePopupBtn(){
        agreeClosePopupBtn.click();
    }
}

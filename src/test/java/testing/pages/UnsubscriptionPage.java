package testing.pages;


import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class UnsubscriptionPage extends Form {
    private static final String confirmBtnLocator = "//button[@type='submit']";
    private final IButton submitBtn = getElementFactory().getButton(By.xpath(confirmBtnLocator),"Unsubscription submit button");
    private final ITextBox email = getElementFactory().getTextBox(By.xpath("//input[@id='email']"),"Enter email");
    private final ILabel unsubscriptionMessage = getElementFactory().getLabel(By.xpath("//strong"),"Unsubscription success message");

    public UnsubscriptionPage() {
        super(By.xpath(confirmBtnLocator), "Unsubscription Page");
    }

    public void clickSubmitBtn(){
        submitBtn.click();
    }

    public void enterEmail(String mail){
        email.clearAndType(mail);
    }

    public String getUnsubscriptionMsg(){
        return unsubscriptionMessage.getElement().getText();
    }
}

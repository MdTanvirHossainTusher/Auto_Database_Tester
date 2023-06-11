package testing.form;


import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class EmailForm extends Form {

    private final ITextBox emailTextBox = getElementFactory().getTextBox(By.xpath("//input[contains(@class,'w-full') and @type='email']"),"Email text box");
    private final IButton submitBtn = getElementFactory().getButton(By.xpath("//input[@data-event='NL_submit']"),"Submit button");

    public EmailForm() {
        super(By.xpath("//section[contains(@class,'sticky')]"), "Email form");
    }

    public void enterEmail(String email){
        emailTextBox.clearAndType(email);
    }

    public void clickSubmitBtn(){
        submitBtn.clickAndWait();
    }

}

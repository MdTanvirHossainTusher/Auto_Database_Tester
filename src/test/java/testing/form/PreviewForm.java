package testing.form;


import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class PreviewForm extends Form {

    private static final String crossLocator = "//a[contains(@class,'close-modal')]";

    public PreviewForm() {
        super(By.xpath(crossLocator), "Preview Form");
    }
}

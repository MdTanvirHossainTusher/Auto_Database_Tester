package testing.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.JavaScript;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import framework.utils.JsonDataUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testing.form.EmailForm;
import testing.form.PreviewForm;
import java.util.List;

public class NewsletterPage extends Form {

    private int selectedNewsletter = -1;
    private static final String iFrame = "//iframe";
    private final List<WebElement> previewIFrame = AqualityServices.getBrowser().getDriver().findElements(By.xpath(iFrame));
    private static final String unsubscribeLocator = "//table[contains(@class,'width100pc') and @role='presentation']//table[contains(@class,'width100pc')  and @role='presentation']//tr//td[contains(@style,'padding: 24px')]//p//a";
    private final List<IButton> chooseNewsletter = getElementFactory().findElements(By.xpath("//label[contains(@class,'unchecked-label')]"), "Choose this newsletter", ElementType.BUTTON);
    private final List<ILink> previews = getElementFactory().findElements(By.xpath("//a[contains(@class,'mt-3')]"), "See preview link", ElementType.LINK);
    private final IButton crossBtn = getElementFactory().getButton(By.xpath("//button[@id='didomi-notice-agree-button']"),"Cross button");
    private final ILink unsubscribe = getElementFactory().getLink(By.xpath(unsubscribeLocator), "Culture, Green, Travel preview");
    private final ILink euronewsToday = getElementFactory().getLink(By.xpath("//table[15]//table[1]//tr[3]/td/p/a"), "Euronews today preview");
    private final ILink briefing = getElementFactory().getLink(By.xpath("//table[21]//table[1]//tr[3]/td/p/a"), "The briefing preview");
    private final ILink theWatch = getElementFactory().getLink(By.xpath("//table[16]//table[1]//tr[3]/td/p/a"), "The watch preview");
    private final ILink culture = getElementFactory().getLink(By.xpath("//table[11]//table[@class='width100pc']//tr[3]/td/p/a"), "Culture preview");
    private final ILink next = getElementFactory().getLink(By.xpath("//table[12]//table[1]//tr[3]//td//p//a[2]"), "Next preview");
    private final ILink green = getElementFactory().getLink(By.xpath("//table[13]//table[1]//tr[3]/td/p/a"), "Green preview");
    private final ILink travel = getElementFactory().getLink(By.xpath("//table[12]//table[@class='width100pc']//tr[3]/td/p/a"), "Travel preview");


    public NewsletterPage() {
        super(By.xpath("//input[contains(@type,'email')]"), "Newsletter Page");
    }

    public void chooseRandomSubscriptionPlan(int newsletterNo){

        for(int i = 0; i < chooseNewsletter.size(); i++){
            if(newsletterNo == i){
                selectedNewsletter = i;
                chooseNewsletter.get(i).clickAndWait();
                break;
            }
        }
    }

    public EmailForm getEmailForm() {
        return new EmailForm();
    }

    public PreviewForm getPreviewForm() {
        return new PreviewForm();
    }

    public int getSelectedNewsletter(){
        return selectedNewsletter;
    }

    public void clickEuronewsToday(){
        AqualityServices.getBrowser().executeScript(JavaScript.CLICK_ELEMENT, euronewsToday.getElement());
    }

    public void clickBriefing(){
        AqualityServices.getBrowser().executeScript(JavaScript.CLICK_ELEMENT, briefing.getElement());
    }

    public void clickTheWatch(){
        AqualityServices.getBrowser().executeScript(JavaScript.CLICK_ELEMENT, theWatch.getElement());
    }

    public void clickCulture(){
        AqualityServices.getBrowser().executeScript(JavaScript.CLICK_ELEMENT, culture.getElement());
    }

    public void clickNext(){
        AqualityServices.getBrowser().executeScript(JavaScript.CLICK_ELEMENT, next.getElement());
    }

    public void clickGreen(){
        AqualityServices.getBrowser().executeScript(JavaScript.CLICK_ELEMENT, green.getElement());
    }

    public void clickTravel(){
        AqualityServices.getBrowser().executeScript(JavaScript.CLICK_ELEMENT, travel.getElement());
    }

    public void clickCrossBtn(){
        crossBtn.clickAndWait();
    }

    public void choosePreview(){
        for(int i = 0; i < previews.size(); i++){
            if(selectedNewsletter == i){
                previews.get(i).clickAndWait();
                break;
            }
        }
    }

    public void switchToFrame(){
        List<WebElement> previewIFrame = AqualityServices.getBrowser().getDriver().findElements(By.xpath(iFrame));
        AqualityServices.getBrowser().getDriver().switchTo().frame(previewIFrame.get(JsonDataUtils.getIntValueByKey("totalSubscriptionPlan")));
    }

    private void interactWithLink(int linkIndex) {
        switch (linkIndex) {
            case 0 -> clickEuronewsToday();
            case 1 -> clickBriefing();
            case 2 -> clickTheWatch();
            case 3 -> clickCulture();
            case 4 -> clickNext();
            case 5 -> clickGreen();
            case 6 -> clickTravel();
            case 7 -> clickCrossBtn();
        }
    }

    public void clickPreview(int i) {
        AqualityServices.getBrowser().executeScript(JavaScript.CLICK_ELEMENT, previews.get(i).getElement());

        for (int j = 0; j < previewIFrame.size(); j++) {
            if (j == JsonDataUtils.getIntValueByKey("totalSubscriptionPlan")) {
                switchToFrame();
                interactWithLink(i);
            }
        }
    }

    public void clickUnsubscribePlan(int selectedPreview) {

        for (int i = 0; i < previews.size(); i++) {
            if (selectedPreview == i) {
                clickPreview(i);
            }
        }
    }
}

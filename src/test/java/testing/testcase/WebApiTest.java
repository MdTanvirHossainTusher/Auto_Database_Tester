package testing.testcase;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import framework.utils.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import testing.base.BaseTest;
import testing.pages.HomePage;
import testing.pages.NewsletterPage;
import testing.pages.SubscriptionConfirmedPage;
import testing.pages.UnsubscriptionPage;
import testing.utils.RandomUtils;


public class WebApiTest extends BaseTest {
    @Test
    public void webApiTest(){

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().waitForDisplayed(),"Main page of Euronews is not opened");

        homePage.clickOnAgreeClosePopupBtn();


        homePage.clickOnNewsLetters();
        NewsletterPage newsletterPage = new NewsletterPage();
        Assert.assertTrue(newsletterPage.state().waitForDisplayed(),"Page \"Newsletters\" is not opened");

        int newsletterNo = RandomUtils.randomSubscriptionPlan();
        newsletterPage.chooseRandomSubscriptionPlan(newsletterNo);
        Assert.assertTrue(newsletterPage.getEmailForm().state().waitForDisplayed(),"An email form has not appeared at the bottom of the page");


        newsletterPage.getEmailForm().enterEmail(JsonDataUtils.getStringValueByKey("email"));
        newsletterPage.getEmailForm().clickSubmitBtn();
        AqualityServices.getBrowser().goTo(ExtractLinkUtils.getLink(GmailApiUtils.accessGmailContent()));


        SubscriptionConfirmedPage subscriptionConfirmedPage = new SubscriptionConfirmedPage();
        Assert.assertTrue(subscriptionConfirmedPage.state().waitForDisplayed(),"An email form has not appeared at the bottom of the page");

        subscriptionConfirmedPage.clickBackToSiteBtn();
        Assert.assertTrue(homePage.state().waitForDisplayed(),"Main page of Euronews is not opened");


        homePage.clickOnNewsLetters();
        newsletterPage.choosePreview();

        Assert.assertTrue(newsletterPage.getPreviewForm().state().waitForDisplayed(),"A preview of the required plan is not opened");

        int selectedPreview = newsletterPage.getSelectedNewsletter();
        newsletterPage.clickUnsubscribePlan(selectedPreview);

        UnsubscriptionPage unsubscriptionPage = new UnsubscriptionPage();

        BrowserWindowUtils.switchToUnsubscribeTab();
        Assert.assertTrue(unsubscriptionPage.state().waitForDisplayed(),"Unsubscribe page is not opened");

        unsubscriptionPage.enterEmail(JsonDataUtils.getStringValueByKey("email"));
        unsubscriptionPage.clickSubmitBtn();
        String unsubscriptionMsg = unsubscriptionPage.getUnsubscriptionMsg();

        Assert.assertEquals(unsubscriptionMsg, JsonDataUtils.getStringValueByKey("subscriptionMsg"),"Unsubscribe page is not opened");

        boolean mailBody = GmailApiUtils.accessGmailContent().contains(JsonDataUtils.getStringValueByKey("subscriptionMsg"));
        Assert.assertFalse(mailBody,"The letter has arrived");

        Logger.getInstance().info("The test hasn't completed.");


    }
}

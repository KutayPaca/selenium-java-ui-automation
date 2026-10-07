package tests.auth;

import base.BaseTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.auth.ForgotPasswordPage;

public class ForgotPasswordTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;

    @BeforeMethod
    public void setupForgotPasswordTest()
    {
        driver.get(driver.getCurrentUrl()+"forgot_password");
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }
    @Test
    public void testRetrivePasswordRedirect()
    {
        //Eylem
        forgotPasswordPage.enterEmail("kutay@gmail.com");
        forgotPasswordPage.clickRetrievePassword();

        //Doğrulama
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("email_sent"),"form gönderildikten sonra url yönlendirmesi yapılamadı");
    }

}

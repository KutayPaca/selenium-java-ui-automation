package pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class RedirectLinkPage extends BasePage {

    // Ekranda tıklanacak olan "here" linkinin ID locator'ı
    private final By redirectLink = By.id("redirect");

    public RedirectLinkPage(WebDriver driver) {
        super(driver);
    }

    // EYLEM: Yönlendirme tetikleyicisine tıklama
    public void clickRedirect() {
        driver.findElement(redirectLink).click();
    }
}
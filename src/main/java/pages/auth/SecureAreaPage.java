package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

public class SecureAreaPage extends BasePage {

    private By logoutButton = By.cssSelector("a[href='/logout']"); // Kapanış parantezindeki yazım hatası giderildi
    private By flashMessage = By.id("flash");

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public boolean isSecureAreaLoaded() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(flashMessage, "You logged into a secure area!"));
    }
}
package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;
import java.time.Duration;

public class LoginPage extends BasePage {

    // Element adresleri
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");

    // Constructor (Super ile BasePage'e driver'ı yollar)
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public String getPasswordInputType() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).getAttribute("type");
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isAtLoginPage() {
        try {
            // Local wait tanımlamaya gerek kalmadı, base sınıftan gelen wait objesini kullanabiliriz,
            // ancak TimeoutException yönetimi için süreyi kısa tutmak istersen lokal tanımlayabilirsin.
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getFlashMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
    }

    public boolean isFlashMessagePresent(String expectedText) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(flashMessage, expectedText));
    }
}
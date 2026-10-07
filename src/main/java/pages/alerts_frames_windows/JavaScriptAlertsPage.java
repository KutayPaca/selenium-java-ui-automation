package pages.alerts_frames_windows;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class JavaScriptAlertsPage extends BasePage {

    // Locator'lar: Butonları üzerlerindeki onclick attributelerine göre yakalamak en güvenlisidir.
    private By jsAlertButton = By.cssSelector("button[onclick='jsAlert()']");
    private By jsConfirmButton = By.cssSelector("button[onclick='jsConfirm()']");
    private By jsPromptButton = By.cssSelector("button[onclick='jsPrompt()']");
    private By resultText = By.id("result");

    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    // --- EYLEMLER (ACTIONS) ---

    public void clickJsAlertButton() {
        driver.findElement(jsAlertButton).click();
    }

    public void clickJsConfirmButton() {
        driver.findElement(jsConfirmButton).click();
    }

    public void clickJsPromptButton() {
        driver.findElement(jsPromptButton).click();
    }

    // --- ALERT (UYARI) YÖNETİM METOTLARI ---

    // Tarayıcı odağını Alert'e geçirip "Tamam/Accept" butonuna basar
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    // Tarayıcı odağını Alert'e geçirip "İptal/Dismiss" butonuna basar
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    // Alert'in üzerindeki uyarı metnini (Örn: "I am a JS Alert") okur
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    // JS Prompt (Giriş kutulu uyarı) içine metin gönderir
    public void typeTextIntoAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    // --- DOĞRULAMA (ASSERTION) İÇİN OKUMA METODU ---
    // İşlem bittikten sonra HTML sayfasına yansıyan sonuç metnini alır
    public String getResultText() {
        return driver.findElement(resultText).getText();
    }
}
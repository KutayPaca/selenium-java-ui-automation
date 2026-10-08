package pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

public class KeyPressesPage extends BasePage {

    // Locator'lar
    private By resultText = By.id("result");

    public KeyPressesPage(WebDriver driver) {
        super(driver);
    }

    // EYLEM: Klavyeden özel bir fonksiyon tuşuna basma
    public void pressKey(Keys key) {
        // Belirli bir elemente değil, doğrudan tarayıcıya tuş gönderiyoruz
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }

    // EYLEM: Standart bir harfe basma
    public void pressKey(String character) {
        Actions actions = new Actions(driver);
        actions.sendKeys(character).perform();
    }

    // DOĞRULAMA: Altta beliren sonuç metnini okuma
    public String getResultText() {
        // Sayfadaki JS DOM'u güncelleyene kadar dinamik olarak bekle (Metin boş olmayana kadar)
        wait.until(d -> !d.findElement(resultText).getText().isEmpty());
        return driver.findElement(resultText).getText();
    }
}
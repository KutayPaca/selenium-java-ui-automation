package pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class HorizontalSliderPage extends BasePage {

    // Locator'lar
    private By sliderInput = By.cssSelector("input[type='range']");
    private By sliderValueText = By.id("range");

    public HorizontalSliderPage(WebDriver driver) {
        super(driver);
    }

    // EYLEM: Slider'ı belirli bir miktar sağa kaydırma
    public void moveSliderRight(int times) {
        // Slider elementine odaklanıp, istenen sayı kadar SAĞ OK tuşu gönderiyoruz
        for (int i = 0; i < times; i++) {
            driver.findElement(sliderInput).sendKeys(Keys.ARROW_RIGHT);
        }
    }

    // DOĞRULAMA: Slider'ın yanındaki güncel değeri okuma
    public String getSliderValue() {
        return driver.findElement(sliderValueText).getText();
    }
}
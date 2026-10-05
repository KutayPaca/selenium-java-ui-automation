package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

public class InputsPage extends BasePage {

    //locator
    private By numberInput = By.cssSelector("input[type=number]");

    public InputsPage(WebDriver driver) {
        super(driver); // driver'ı BasePage'e gönderiyoruz
    }

    //Alana veri gönderme
    public void enterData(String data) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(numberInput)).clear();
        driver.findElement(numberInput).sendKeys(data);
    }

    //Alandaki mevcut veriyi okuma eylemi (ÖNEMLİ: getText() yerine getAttribute)
    public String getInputValue() {
        return driver.findElement(numberInput).getAttribute("value");
    }

    //Klavye tuşlarını simüle etme
    public void pressArrowUp() {
        driver.findElement(numberInput).sendKeys(Keys.ARROW_UP);
    }

    public void pressArrowDown() {
        driver.findElement(numberInput).sendKeys(Keys.ARROW_DOWN);
    }

}

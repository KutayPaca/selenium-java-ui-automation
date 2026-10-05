package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class CheckboxesPage extends BasePage {

    //CSS Selector ile aynı taga sahip elementleri sırasıyla yakalama
    private By checkbox1 = By.cssSelector("input[type='checkbox']:nth-of-type(1)");
    private By checkbox2 = By.cssSelector("input[type='checkbox']:nth-of-type(2)");

    public CheckboxesPage(WebDriver driver) {
        super(driver);
    }

    // Durum (State) Okuma Metodları
    // isSelected() metodu, element seçiliyse true, değilse false döner.
    public boolean isCheckbox1Selected() {
        return driver.findElement(checkbox1).isSelected();
    }
    public boolean isCheckbox2Selected() {
        return driver.findElement(checkbox2).isSelected();
    }
    // Eylem (Action) Metodları
    public void clickCheckbox1(){
        driver.findElement(checkbox1).click();
    }
    public void clickCheckbox2(){
        driver.findElement(checkbox2).click();
    }

}

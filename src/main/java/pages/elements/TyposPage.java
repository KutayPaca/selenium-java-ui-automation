package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class TyposPage extends BasePage {

    private By secondParagraph = By.cssSelector("#content p:nth-of-type(2)");

    public TyposPage(WebDriver driver) {
        super(driver);
    }

    public String getParagraphText(){
        return driver.findElement(secondParagraph).getText();
    }
}


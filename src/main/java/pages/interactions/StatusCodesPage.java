package pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class StatusCodesPage extends BasePage {

    public StatusCodesPage(WebDriver driver) {
        super(driver);
    }

    // YARDIMCI EYLEM: Linke tıklamadan sadece hedef URL'ini (href) okur (API testi için)
    public String getStatusCodeLinkHref(String statusCode) {
        return driver.findElement(By.linkText(statusCode)).getAttribute("href");
    }

}
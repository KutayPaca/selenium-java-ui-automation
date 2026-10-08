package pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class StatusCodesPage extends BasePage {

    public StatusCodesPage(WebDriver driver) {
        super(driver);
    }

    // EYLEM: Parametre olarak verilen koda (örn: "404") sahip linke tıklar
    public void clickStatusCodeLink(String statusCode) {
        // Dinamik locator: By.linkText ile doğrudan görünen metne tıklıyoruz
        driver.findElement(By.linkText(statusCode)).click();
    }

    // YARDIMCI EYLEM: Linke tıklamadan sadece hedef URL'ini (href) okur (API testi için)
    public String getStatusCodeLinkHref(String statusCode) {
        return driver.findElement(By.linkText(statusCode)).getAttribute("href");
    }

    // DOĞRULAMA: Tıkladıktan sonra açılan sayfadaki sonuç metnini okur
    public String getResultMessage() {
        // Sonuç sayfasındaki metin <p> etiketi içinde yer alıyor
        return driver.findElement(By.cssSelector("div.example p")).getText();
    }
}
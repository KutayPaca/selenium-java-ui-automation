package pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

public class HoversPage extends BasePage {

    // Profil fotoğraflarının genel kapsayıcısı (3 adet var)
    private By figureBox = By.className("figure");
    // Fare üzerine gelince ortaya çıkan gizli bilgi alanı
    private By boxCaption = By.className("figcaption");

    public HoversPage(WebDriver driver) {
        super(driver);
    }

    // --- EYLEMLER (ACTIONS) ---

    // Parametre olarak verilen sıradaki (1, 2 veya 3) resmin üzerine fareyi götürür
    public void hoverOverFigure(int index) {
        // FindElements listesi 0'dan başladığı için index'ten 1 çıkarıyoruz (Kullanıcı dostu olması için)
        WebElement figure = driver.findElements(figureBox).get(index - 1);

        // Actions sınıfı ile donanımsal fare hareketi simülasyonu
        Actions actions = new Actions(driver);
        actions.moveToElement(figure).perform(); // .perform() KOMUTUNU ASLA UNUTMA!
    }

    // --- DOĞRULAMA (ASSERTION) İÇİN OKUMA METOTLARI ---

    // İlgili resmin altındaki yazının görünür olup olmadığını kontrol eder
    public boolean isCaptionDisplayed(int index) {
        return driver.findElements(boxCaption).get(index - 1).isDisplayed();
    }

    // Fare ile üzerine geldikten sonra beliren h5 etiketli ismi (Örn: "name: user1") çeker
    public String getHoverNameText(int index) {
        return driver.findElements(boxCaption).get(index - 1).findElement(By.tagName("h5")).getText();
    }

    // Tıklanabilir "View profile" linkinin href özelliğini (URL'ini) çeker
    public String getHoverLinkAttribute(int index) {
        return driver.findElements(boxCaption).get(index - 1).findElement(By.tagName("a")).getAttribute("href");
    }
}
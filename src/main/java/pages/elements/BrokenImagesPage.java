package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class BrokenImagesPage extends BasePage {

    // .example div'i içindeki tüm <img> etiketlerini yakaladım
    private By images = By.cssSelector(".example img");

    public BrokenImagesPage(WebDriver driver) {
        super(driver);
    }

    // Sayfadaki toplam görsel sayısını verir
    public int getAllImagesCount() {
        return driver.findElements(images).size();
    }

    // Sayfadaki kırık (render edilememiş) görsellerin listesini döndürür
    public List<String> getBrokenImageSources() {
        List<WebElement> imageElements = driver.findElements(images);
        List<String> brokenImages = new ArrayList<>();

        for (WebElement img : imageElements) {
            // naturalWidth tarayıcının görseli çizip çizemediğini söyler
            String naturalWidth = img.getAttribute("naturalWidth");
            String imageSrc = img.getAttribute("src");

            // Eğer naturalWidth null ise veya "0" ise görsel ekranda kırıktır
            if (naturalWidth == null || naturalWidth.equals("0")) {
                brokenImages.add(imageSrc);
            }
        }

        return brokenImages;
    }
}
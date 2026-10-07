package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

import java.util.List;

public class AddRemoveElementsPage extends BasePage {

    // Locator'lar
    private By addElementButton = By.cssSelector("button[onclick='addElement()']");

    // Dikkat: Burada ID veya tekil bir class yerine,
    // eklenen TÜM delete butonlarını yakalayacak ortak bir locator kullanıyoruz.
    private By deleteButtons = By.cssSelector("#elements button.added-manually");

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }

    // "Add Element" butonuna tıklama eylemi
    public void clickAddElement() {
        driver.findElement(addElementButton).click();
    }

    // Eklenen "Delete" butonlarının sayısını bulma eylemi (Öğrenme Odaklı Kısım)
    public int getAddedElementsCount() {
        // findElements kullanarak tüm butonları listeye alıyoruz
        List<WebElement> elements = driver.findElements(deleteButtons);
        // Listenin boyutunu (eleman sayısını) döndürüyoruz
        return elements.size();
    }

    // Belirli bir sıradaki "Delete" butonuna tıklama eylemi
    public void clickDeleteButton(int index) {
        List<WebElement> elements = driver.findElements(deleteButtons);
        // NullPointerException veya IndexOutOfBounds almamak için bir güvenlik kontrolü
        if (elements.size() > index) {
            elements.get(index).click();
        } else {
            System.out.println("Belirtilen indekste silinecek buton bulunamadı!");
        }
    }
}
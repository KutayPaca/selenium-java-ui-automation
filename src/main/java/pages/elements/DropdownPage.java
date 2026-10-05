package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

public class DropdownPage extends BasePage {
    //locator sadece ana kapsayıcıyı buldum içindeki optionları select sınıfı halledecek
    private By dropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        super(driver);
    }
    // YARDIMCI METOD: Select nesnesini döndürür.
    // Bunu private yapıyoruz çünkü sadece bu sınıfın içinde kullanılacak.
    private Select findDropdownElement(){
        return new Select(driver.findElement(dropdown));
    }
    // Görünen Metin (Kullanıcı gibi düşün)
    public void selectByVisibleText(String text){
        findDropdownElement().selectByVisibleText(text);
    }
    // İndeks (Sıralama mantığı, 0'dan başlar)
    public void selectByIndex(int index){
        findDropdownElement().selectByIndex(index);
    }
    // Değer (Geliştirici gibi düşün)
    public void selectByValue(String value){
        findDropdownElement().selectByValue(value);
    }
    // DOĞRULAMA (ASSERTION) İÇİN OKUMA METODU
    // Seçilen mevcut seçeneğin metnini okuyup test sınıfına yollar.
    public String getSelectedOptionText(){
        return findDropdownElement().getFirstSelectedOption().getText();
    }
}

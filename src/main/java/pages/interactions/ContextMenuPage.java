package pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

public class ContextMenuPage extends BasePage {

    // Kutu elementinin adresi
    private By hotSpotBox = By.id("hot-spot");

    public ContextMenuPage(WebDriver driver) {
        super(driver);
    }

    // EYLEM: Kutuya sağ tıklama
    public void rightClickOnBox() {
        // Actions nesnesini driver ile başlatıyoruz
        Actions actions = new Actions(driver);

        // contextClick() sağ tıklama komutudur.
        // Ancak perform() demezsek bu eylem sadece hafızada bekler, tarayıcıya iletilmez.
        actions.contextClick(driver.findElement(hotSpotBox)).perform();
    }
}
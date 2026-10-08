package tests.interactions;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.interactions.ContextMenuPage;

public class ContextMenuTest extends BaseTest {

    private ContextMenuPage contextMenuPage;

    @BeforeMethod
    public void setupContextMenuTest() {
        driver.get(driver.getCurrentUrl() + "context_menu");
        contextMenuPage = new ContextMenuPage(driver);
    }

    @Test
    public void testContextMenuAlert() {
        // 1. Eylem: Kutuya sağ tıkla (Bu eylem alert fırlatacak)
        contextMenuPage.rightClickOnBox();

        // 2. Doğrulama: Alert içine girip metni okuma
        // Tarayıcı odağını Alert'e geçirmeliyiz çünkü Alert'ler DOM (HTML) içinde değildir.
        String actualAlertText = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText, "You selected a context menu", "Sağ tık sonrası açılan alert metni eşleşmedi!");

        // 3. Eylem: Alert'i onaylayıp kapatarak tarayıcıyı normale döndürme
        driver.switchTo().alert().accept();
    }
}
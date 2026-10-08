package tests.interactions;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.interactions.KeyPressesPage;

public class KeyPressesTest extends BaseTest {

    private KeyPressesPage keyPressesPage;

    @BeforeMethod
    public void setupKeyPressesTest() {
        driver.get(driver.getCurrentUrl() + "key_presses");
        keyPressesPage = new KeyPressesPage(driver);
    }

    @Test
    public void testPressEnterKey() {
        // Eylem: Enter tuşunu simüle et
        keyPressesPage.pressKey(Keys.ENTER);
        // Doğrulama: Ekrandaki sonucu teyit et
        Assert.assertEquals(keyPressesPage.getResultText(), "You entered: ENTER", "ENTER tuşu doğru algılanmadı!");
    }

    @Test
    public void testPressBackspaceKey() {
        keyPressesPage.pressKey(Keys.BACK_SPACE);
        Assert.assertEquals(keyPressesPage.getResultText(), "You entered: BACK_SPACE", "BACK_SPACE tuşu doğru algılanmadı!");
    }

    @Test
    public void testPressCtrlKey() {
        keyPressesPage.pressKey(Keys.CONTROL);
        Assert.assertEquals(keyPressesPage.getResultText(), "You entered: CONTROL", "CTRL tuşu doğru algılanmadı!");
    }

    @Test
    public void testPressAlphabetKey() {
        keyPressesPage.pressKey("A");
        Assert.assertEquals(keyPressesPage.getResultText(), "You entered: A", "Karakter tuşu doğru algılanmadı!");
    }

}
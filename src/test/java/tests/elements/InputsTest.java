package tests.elements;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.base.BasePage;
import pages.elements.InputsPage;

public class InputsTest extends BaseTest {

    private InputsPage inputsPage;

    @BeforeMethod
    public void setupInputsTest() {
        // Ana URL'in sonuna 'inputs' ekleyerek ilgili sayfaya gidiyoruz
        driver.get(driver.getCurrentUrl()+"inputs");
        inputsPage = new InputsPage(driver);
    }

    @Test
    public void testValidNumberInput() {
        //eylem
        inputsPage.enterData("123");

        //doğrulama
        String actualValue = inputsPage.getInputValue();
        Assert.assertEquals(actualValue, "123","sayısal değer doğru girilemedi");
    }
    @Test
    public void testInvalidLettersInput() {
        inputsPage.enterData("abc");
        String actualValue = inputsPage.getInputValue();
        Assert.assertEquals(actualValue,"","Rakam alanına harf girilmesine izin verildi");
    }
    @Test
    public void testKeyboardArrowKeys() {
        inputsPage.enterData("5");

        inputsPage.pressArrowUp();
        Assert.assertEquals(inputsPage.getInputValue(),"6","Yukarı ok tuşu değeri arttırmadı");

        inputsPage.pressArrowDown();
        Assert.assertEquals(inputsPage.getInputValue(),"5","Aşağı ok tuşu değeri azaltmadı");
    }
}

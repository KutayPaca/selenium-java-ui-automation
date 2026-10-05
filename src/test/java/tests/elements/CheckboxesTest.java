package tests.elements;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.elements.CheckboxesPage;

public class CheckboxesTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeMethod
    public void setCheckboxesTest()
    {
        // Ana URL'in sonuna 'checkboxes' ekleyerek sayfaya gidiyoruz
        driver.get(driver.getCurrentUrl()+"checkboxes");
        checkboxesPage = new CheckboxesPage(driver);
    }

    @Test
    public void testDefaultCheckboxesStates()
    {
        // Sayfa ilk açıldığında elementlerin default durumlarını doğrula

        // Checkbox 1'in seçili OLMAMASINI bekliyoruz
        Assert.assertFalse(checkboxesPage.isCheckbox1Selected(),"Checkbox 1 varsayılan olarak seçili geldi");

        // Checkbox 2'nin seçili OLMASINI bekliyoruz
        Assert.assertTrue(checkboxesPage.isCheckbox2Selected(),"Checkbox 2 varsayılan olarak seçili gelmedi");
    }

    @Test
    public void testToggleCheckboxes()
    {
        // İki elemente de tıklayıp mevcut durumlarının tersine döndüğünü doğrula
        checkboxesPage.clickCheckbox1();
        Assert.assertTrue(checkboxesPage.isCheckbox1Selected(),"checkbox1 tıklandıktan sonra seçili olarak gelmedi");

        checkboxesPage.clickCheckbox2();
        Assert.assertFalse(checkboxesPage.isCheckbox2Selected(),"checkbox2 tıklandıktan sonra seçili olarak gelmedi");
    }
}

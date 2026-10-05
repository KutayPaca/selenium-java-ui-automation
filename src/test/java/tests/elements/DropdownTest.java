package tests.elements;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.elements.DropdownPage;

public class DropdownTest extends BaseTest {

    private DropdownPage dropdownPage;

    @BeforeMethod
    public void setupDropdownTest()
    {
        driver.get(driver.getCurrentUrl()+"dropdown");
        dropdownPage = new DropdownPage(driver);
    }

    @Test
    public void testSelectByVisibleText()
    {
        // Ekranda gördüğümüz metni kullanarak Option 1i seçelim.
        dropdownPage.selectByVisibleText("Option 1");

        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption, "Option 1","Görünen metin ile seçim başarısız");
    }
    @Test
    public void testSelectByValueAttribute(){
        // HTML içindeki value niteliğini (attribute) kullanarak Option 2i seçelim.
        // Option 2'nin HTML kodu şöyledir: <option value="2">Option 2</option>
        dropdownPage.selectByValue("2");
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption,"Option 2","Value niteliği ile seçim başarısız");
    }
    @Test
    public void testSelectByIndex() {
        // Sıra numarasına göre seçim yapalım.
        // İndeks 0 = "Please select an option" (devre dışı bırakılmış varsayılan başlık)
        // İndeks 1 = "Option 1"
        // İndeks 2 = "Option 2"

        dropdownPage.selectByIndex(1); // 1. sıradakini yani Option 1'i seçmesini bekliyoruz.

        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption, "Option 1", "İndeks numarası ile seçim başarısız!");
    }
}

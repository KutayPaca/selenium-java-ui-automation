package tests.interactions;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.interactions.HorizontalSliderPage;

public class HorizontalSliderTest extends BaseTest {

    private HorizontalSliderPage horizontalSliderPage;

    @BeforeMethod
    public void setupSliderTest() {
        driver.get(driver.getCurrentUrl() + "horizontal_slider");
        horizontalSliderPage = new HorizontalSliderPage(driver);
    }

    @Test
    public void testSliderToSpecificValue() {
        // Eylem: Slider her adımda 0.5 artıyor. Hedefimiz 4 ise, 8 kere sağa kaydırmalıyız.
        horizontalSliderPage.moveSliderRight(8);

        // Doğrulama: Ekrandaki metnin "4" olduğunu kontrol et
        String actualValue = horizontalSliderPage.getSliderValue();
        Assert.assertEquals(actualValue, "4", "Slider beklenen değere ulaşamadı!");
    }
}
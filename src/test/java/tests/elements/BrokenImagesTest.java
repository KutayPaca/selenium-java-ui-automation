package tests.elements;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.elements.BrokenImagesPage;

import java.util.List;

public class BrokenImagesTest extends BaseTest {

    private BrokenImagesPage brokenImagesPage;

    @BeforeMethod
    public void setupBrokenImagesTest() {
        driver.get(driver.getCurrentUrl() + "broken_images");
        brokenImagesPage = new BrokenImagesPage(driver);
    }

    @Test
    public void testDetectBrokenImages() {
        // 1. Kırık görselleri tara ve listeyi al
        List<String> brokenImages = brokenImagesPage.getBrokenImageSources();

        // 2. DOĞRULAMA (Assertion):
        // Sayfada HİÇBİR kırık görsel bulunmamalıdır.
        // Script sayfada kaç tane kırık resim olduğunu kendi tespit eder;
        // 1 tane bile varsa testi patlatıp hatayı ve kırık URL'leri rapora döker!
        Assert.assertTrue(brokenImages.isEmpty(),
                "Sayfada " + brokenImages.size() + " adet kırık görsel tespit edildi! " +
                        "Kırık URL'ler: " + brokenImages);

    }
}
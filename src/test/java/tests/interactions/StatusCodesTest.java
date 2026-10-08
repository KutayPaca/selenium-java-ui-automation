package tests.interactions;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.interactions.StatusCodesPage;

import java.net.HttpURLConnection;
import java.net.URL;

public class StatusCodesTest extends BaseTest {

    private StatusCodesPage statusCodesPage;

    @BeforeMethod
    public void setupStatusCodesTest() {
        driver.get(driver.getCurrentUrl() + "status_codes");
        statusCodesPage = new StatusCodesPage(driver);
    }

    // 1. ADIM: TestNG'ye test verilerini sağlayacak olan DataProvider'ı oluşturuyoruz
    // İlk değer sayfadaki linkin metni (String), ikinci değer beklenen HTTP yanıt kodu (int)
    @DataProvider(name = "statusCodeData")
    public Object[][] provideStatusCodes() {
        return new Object[][] {
                {"200", 200},
                {"301", 301},
                {"404", 404},
                {"500", 500}
        };
    }

    // 2. ADIM: Test metodumuzu DataProvider'a bağlıyoruz
    @Test(dataProvider = "statusCodeData")
    public void testAllStatusCodesViaHttpConnection(String linkText, int expectedCode) {

        // Eylem: İlgili linkin hedef URL'sini sayfa nesnemizden çekiyoruz
        String targetUrl = statusCodesPage.getStatusCodeLinkHref(linkText);

        try {
            // Java ile Ağ (Network) isteği oluşturma
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 301 (Yönlendirme) kodunu doğrudan okuyabilmek için Java'nın otomatik yönlendirmesini kapatıyoruz
            connection.setInstanceFollowRedirects(false);

            connection.setRequestMethod("GET");
            connection.connect();

            // Gerçekten dönen HTTP yanıt kodunu alıyoruz
            int responseCode = connection.getResponseCode();

            // Doğrulama
            Assert.assertEquals(responseCode, expectedCode, linkText + " linki için beklenen ağ yanıtı alınamadı!");

            connection.disconnect();

        } catch (Exception e) {
            Assert.fail("Ağ bağlantısı sırasında bir hata oluştu: " + e.getMessage());
        }
    }
}
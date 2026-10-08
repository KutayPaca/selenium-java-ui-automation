package tests.interactions;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.interactions.RedirectLinkPage;

public class RedirectLinkTest extends BaseTest {

    private RedirectLinkPage redirectLinkPage;

    @BeforeMethod
    public void setupRedirectTest() {
        driver.get(driver.getCurrentUrl() + "redirector");
        redirectLinkPage = new RedirectLinkPage(driver);
    }

    @Test
    public void testRedirectionPath() {
        // Eylem: Linke tıkla ve sunucunun tarayıcıyı yönlendirmesini bekle
        redirectLinkPage.clickRedirect();

        // Doğrulama: Yönlendirme bittikten sonra mevcut URL'yi çek
        String currentUrl = driver.getCurrentUrl();

        // Final URL'nin "status_codes" endpoint'ini içerip içermediğini kontrol et
        Assert.assertNotNull(currentUrl); //Eğer tarayıcıyla iletişim anlık olarak koparsa ve getCurrentUrl() metodu geriye 'null' (hiçlik) döndürürse ne olur?
        // Null bir değerin üzerinden .contains() metodunu çağırmaya çalışırsan program NullPointerException fırlatıp çöker.
        Assert.assertTrue(currentUrl.contains("status_codes"), "Yönlendirme başarısız oldu! Bulunulan URL: " + currentUrl);
    }
}
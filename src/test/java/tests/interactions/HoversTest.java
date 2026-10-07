package tests.interactions;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.interactions.HoversPage;

public class HoversTest extends BaseTest {

    private HoversPage hoversPage;

    @BeforeMethod
    public void setupHoversTest() {
        // Ana URL'in sonuna endpoint ekliyoruz
        driver.get(driver.getCurrentUrl() + "hovers");
        hoversPage = new HoversPage(driver);
    }

    @Test
    public void testHoverUser1() {
        // Eylem: 1. kullanıcının profil resminin üzerine fareyi götür (tıklama yok)
        hoversPage.hoverOverFigure(1);

        // Doğrulama 1: Normalde gizli olan bilgi alanının (caption) görünür hale geldiğini ispatla
        Assert.assertTrue(hoversPage.isCaptionDisplayed(1), "1. kullanıcı için gizli bilgiler görünür hale gelmedi!");

        // Doğrulama 2: Görünen ismin doğru olduğunu kontrol et
        Assert.assertEquals(hoversPage.getHoverNameText(1), "name: user1", "Kullanıcı ismi eşleşmedi!");
    }

    @Test
    public void testHoverAllUsers() {
        // Ekstra Beceri: Dinamik test
        // 3 adet profil fotoğrafı olduğu için bir for döngüsü ile hepsini tek bir testte doğrulayabiliriz
        for (int i = 1; i <= 3; i++) {

            // Eylem: İlgili index'teki resme fareyi kaydır
            hoversPage.hoverOverFigure(i);

            // Doğrulama: Alanın açıldığını ve doğru kullanıcı ismini içerdiğini test et
            Assert.assertTrue(hoversPage.isCaptionDisplayed(i), i + ". profilin detayları ekranda belirmedi!");
            Assert.assertEquals(hoversPage.getHoverNameText(i), "name: user" + i, i + ". profilin isminde hata var!");

            // Doğrulama: Linkin doğru sayfaya işaret edip etmediğini kontrol et
            String expectedLinkPath = "/users/" + i;
            Assert.assertTrue(hoversPage.getHoverLinkAttribute(i).contains(expectedLinkPath),
                    i + ". profilin linki doğru URL'i barındırmıyor!");
        }
    }
}
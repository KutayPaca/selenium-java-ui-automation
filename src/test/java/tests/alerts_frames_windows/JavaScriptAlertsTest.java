package tests.alerts_frames_windows;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.alerts_frames_windows.JavaScriptAlertsPage;

public class JavaScriptAlertsTest extends BaseTest {

    private JavaScriptAlertsPage alertsPage;

    @BeforeMethod
    public void setupAlertsTest() {
        // BaseTest'ten gelen ana URL'in sonuna javascript_alerts ekleyerek sayfaya gidiyoruz
        driver.get(driver.getCurrentUrl() + "javascript_alerts");
        alertsPage = new JavaScriptAlertsPage(driver);
    }

    @Test
    public void testAcceptJsAlert() {
        // Eylem: Sadece "Tamam" butonu olan basit alert'i aç
        alertsPage.clickJsAlertButton();

        // Doğrulama 1: Alert metni doğru mu?
        Assert.assertEquals(alertsPage.getAlertText(), "I am a JS Alert", "Alert metni eşleşmedi!");

        // Eylem: Alert'i onayla
        alertsPage.acceptAlert();

        // Doğrulama 2: Sayfadaki sonuç metni doğru güncellendi mi?
        Assert.assertEquals(alertsPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void testDismissJsConfirm() {
        // Eylem: Hem "Tamam" hem "İptal" seçeneği olan confirm alert'i aç
        alertsPage.clickJsConfirmButton();

        // Eylem: İptal'e (Cancel) bas
        alertsPage.dismissAlert();

        // Doğrulama: İptal edildiğine dair dönen mesajı kontrol et
        Assert.assertEquals(alertsPage.getResultText(), "You clicked: Cancel", "Confirm alert iptal edilemedi!");
    }

    @Test
    public void testTypeIntoJsPrompt() {
        // Eylem: Kullanıcıdan metin bekleyen prompt alert'i aç
        alertsPage.clickJsPromptButton();

        String inputData = "Test Otomasyon Mühendisi";

        // Eylem: Alert içindeki metin kutusuna yazı gönder ve onayla
        alertsPage.typeTextIntoAlert(inputData);

        alertsPage.acceptAlert();

        // Doğrulama: Gönderilen metnin sayfaya aynen yazıldığını teyit et
        Assert.assertEquals(alertsPage.getResultText(), "You entered: " + inputData, "Prompt alanına veri doğru girilemedi!");
    }
}
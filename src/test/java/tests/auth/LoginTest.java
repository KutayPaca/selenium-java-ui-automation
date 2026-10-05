package tests.auth;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.auth.LoginPage;
import pages.auth.SecureAreaPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupTest() {
        // BaseTest ana URL'e (the-internet.herokuapp.com) gitti.
        // Biz sadece login sayfasına yönlendiriyoruz.
        driver.get(driver.getCurrentUrl() + "login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfullogin() {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        boolean isSuccess = loginPage.isFlashMessagePresent("You logged into a secure area!");
        Assert.assertTrue(isSuccess, "Başarılı giriş mesajı görüntülenemedi");
    }

    @Test
    public void testValidUsernameInvalidPassword() {
        loginPage.login("tomsmith", "cemil");
        boolean isPasswordError = loginPage.isFlashMessagePresent("Your password is invalid!");
        Assert.assertTrue(isPasswordError, "Hatalı şifre mesajı görüntülenemedi");
    }

    @Test
    public void invalidUsernameValidPassword() {
        loginPage.login("Kutay", "SuperSecretPassword!");
        boolean isInvalidUsername = loginPage.isFlashMessagePresent("Your username is invalid!");
        Assert.assertTrue(isInvalidUsername, "Hatalı kullanıcı ismi giriş mesajı görüntülenemedi");
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("Kutay", "cemil");
        boolean isError = loginPage.isFlashMessagePresent("Your username is invalid!");
        Assert.assertTrue(isError, "Hatalı kullanıcı adı mesajı görüntülenemedi");
    }

    @Test
    public void testPasswordIsMasked() {
        String inputType = loginPage.getPasswordInputType();
        Assert.assertEquals(inputType, "password", "Şifre alanı maskelenmemiş!");
    }

    @Test
    public void testLogoutAndBackNavigationSecurity() {
        loginPage.login("tomsmith", "SuperSecretPassword!");

        SecureAreaPage secureAreaPage = new SecureAreaPage(driver);
        Assert.assertTrue(secureAreaPage.isSecureAreaLoaded(), "Güvenli alana giriş yapılamadı!");

        secureAreaPage.clickLogout();

        boolean isLoggedOut = loginPage.isFlashMessagePresent("You logged out of the secure area!");
        Assert.assertTrue(isLoggedOut, "Çıkış mesajı görüntülenemedi!");

        driver.navigate().back();
        driver.navigate().refresh();
        Assert.assertTrue(loginPage.isAtLoginPage(), "Güvenlik Açığı: Geri butonuna basılınca oturum yeniden açıldı!");
    }
}
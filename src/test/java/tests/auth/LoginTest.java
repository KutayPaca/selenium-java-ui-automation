package tests.auth;

import base.BaseTest;
import core.JsonDataReader; // Yeni yazdığımız reader'ı import ediyoruz
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.auth.LoginPage;
import pages.auth.SecureAreaPage;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setupTest() {
        driver.get(driver.getCurrentUrl() + "login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfullogin() {
        // Verileri JSON'dan çekiyoruz
        String user = JsonDataReader.getUsername("validUser");
        String pass = JsonDataReader.getPassword("validUser");

        loginPage.login(user, pass);
        boolean isSuccess = loginPage.isFlashMessagePresent("You logged into a secure area!");
        Assert.assertTrue(isSuccess, "Başarılı giriş mesajı görüntülenemedi");
    }

    @Test
    public void testValidUsernameInvalidPassword() {
        String user = JsonDataReader.getUsername("invalidPasswordUser");
        String pass = JsonDataReader.getPassword("invalidPasswordUser");

        loginPage.login(user, pass);
        boolean isPasswordError = loginPage.isFlashMessagePresent("Your password is invalid!");
        Assert.assertTrue(isPasswordError, "Hatalı şifre mesajı görüntülenemedi");
    }

    @Test
    public void invalidUsernameValidPassword() {
        String user = JsonDataReader.getUsername("invalidUsernameUser");
        String pass = JsonDataReader.getPassword("invalidUsernameUser");

        loginPage.login(user, pass);
        boolean isInvalidUsername = loginPage.isFlashMessagePresent("Your username is invalid!");
        Assert.assertTrue(isInvalidUsername, "Hatalı kullanıcı ismi giriş mesajı görüntülenemedi");
    }

    @Test
    public void testInvalidLogin() {
        String user = JsonDataReader.getUsername("invalidBothUser");
        String pass = JsonDataReader.getPassword("invalidBothUser");

        loginPage.login(user, pass);
        boolean isError = loginPage.isFlashMessagePresent("Your username is invalid!");
        Assert.assertTrue(isError, "Hatalı kullanıcı mesajı görüntülenemedi");
    }

    @Test
    public void testPasswordIsMasked() {
        String inputType = loginPage.getPasswordInputType();
        Assert.assertEquals(inputType, "password", "Şifre alanı maskelenmemiş!");
    }

    @Test
    public void testLogoutAndBackNavigationSecurity() {
        String user = JsonDataReader.getUsername("validUser");
        String pass = JsonDataReader.getPassword("validUser");

        loginPage.login(user, pass);
        SecureAreaPage secureAreaPage = new SecureAreaPage(driver);
        Assert.assertTrue(secureAreaPage.isSecureAreaLoaded(), "Güvenli alana giriş yapılamadı!");

        secureAreaPage.clickLogout();
        boolean isLoggedOut = loginPage.isFlashMessagePresent("You logged out of the secure area!");
        Assert.assertTrue(isLoggedOut, "Çıkış mesajı görüntülenemedi!");

        driver.navigate().back();
        driver.navigate().refresh();
        Assert.assertTrue(loginPage.isAtLoginPage(), "Güvenlik Açığı: Geri butonuna basınca oturum yeniden açıldı!");
    }
}
package base;

import core.ConfigReader;
import core.ExecutionListener;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            // YENİ VE KESİN ÇÖZÜM: Testi gizli sekmede başlatarak Chrome'un tüm şifre ve sızıntı uyarılarını pasifize ediyoruz
            options.addArguments("--incognito");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-extensions");
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            // Dinleyici (Listener) Entegrasyonu
            WebDriver originalDriver = new ChromeDriver(options);
            ExecutionListener listener = new ExecutionListener();
            driver = new org.openqa.selenium.support.events.EventFiringDecorator<>(listener).decorate(originalDriver);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));

        // Teste başlamadan önce ana URL'e git
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
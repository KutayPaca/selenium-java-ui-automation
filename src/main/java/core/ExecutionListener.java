package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class ExecutionListener implements WebDriverListener {

    private boolean isVisualMode;
    private int sleepDuration;

    public ExecutionListener() {
        // config.properties dosyasından değerleri çekiyoruz
        this.isVisualMode = Boolean.parseBoolean(ConfigReader.getProperty("visualMode"));

        String sleepStr = ConfigReader.getProperty("visualSleepMs");
        this.sleepDuration = (sleepStr != null && !sleepStr.isEmpty()) ? Integer.parseInt(sleepStr) : 500;
    }

    // Herhangi bir elemente tıklanmadan HEMEN ÖNCE araya girer
    @Override
    public void beforeClick(WebElement element) {
        if (isVisualMode) {
            highlightAndSleep(element);
        }
    }

    // Herhangi bir elemente veri (sendKeys) gönderilmeden HEMEN ÖNCE araya girer
    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        if (isVisualMode) {
            highlightAndSleep(element);
        }
    }

    // Ortak Vurgulama ve Bekleme Metodu
    private void highlightAndSleep(WebElement element) {
        WebDriver driver = ((WrapsDriver) element).getWrappedDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. Elementin orijinal stilini hafızaya al
        String originalStyle = element.getAttribute("style");

        // 2. Elemente kırmızı çerçeve ve sarı arka plan ekle
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);

        // 3. İstenilen süre kadar sistemi beklet (İzleme Modu)
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 4. İşlem bittikten sonra elementin rengini eski haline döndür
        js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }
}
package tests.elements;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.elements.TyposPage;

public class TyposTest extends BaseTest {

    private TyposPage typosPage;

    @BeforeMethod
    public void setupTyposTest()
    {
        driver.get(driver.getCurrentUrl()+"typos");
        typosPage = new TyposPage(driver);
    }
    @Test
    public void testParagraphTextSpelling(){
        String actualText = typosPage.getParagraphText();
        String expectedText ="Sometimes you'll see a typo, other times you won't.";
        Assert.assertEquals(actualText,expectedText);
        // Doğrulama: Kelimenin "won,t" değil, "won't" olarak doğru yazıldığını garanti ediyoruz
        Assert.assertEquals(actualText, expectedText,"Metinde yazım hatası (typo) tespit edildi!");
    }
}

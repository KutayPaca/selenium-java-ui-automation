package tests.elements;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.elements.AddRemoveElementsPage;

public class AddRemoveElementsTest extends BaseTest {

    private AddRemoveElementsPage addRemoveElementsPage;

    @BeforeMethod
    public void setupAddRemoveTest() {
        // config.properties'den gelen ana URL'in sonuna endpoint ekliyoruz
        driver.get(driver.getCurrentUrl() + "add_remove_elements/");
        addRemoveElementsPage = new AddRemoveElementsPage(driver);
    }

    @Test
    public void testAddSingleElement() {
        // Eylem: 1 kez ekle butonuna bas
        addRemoveElementsPage.clickAddElement();

        // Doğrulama: Eklenen element sayısının 1 olduğunu kontrol et
        int count = addRemoveElementsPage.getAddedElementsCount();
        Assert.assertEquals(count, 1, "Element eklenemedi, sayı 1 değil!");
    }

    @Test
    public void testAddMultipleAndRemoveElements() {
        // Eylem: Döngü ile 3 kez element ekleyelim
        for (int i = 0; i < 3; i++) {
            addRemoveElementsPage.clickAddElement();
        }

        // Doğrulama 1: 3 elementin de eklendiğini teyit edelim
        Assert.assertEquals(addRemoveElementsPage.getAddedElementsCount(), 3, "3 adet element başarılı şekilde eklenemedi!");

        // Eylem: İlk sıradaki (indeks 0) silme butonuna tıklayalım
        addRemoveElementsPage.clickDeleteButton(0);

        // Doğrulama 2: 3 elementten 1'i silinince geriye 2 kalmalı
        Assert.assertEquals(addRemoveElementsPage.getAddedElementsCount(), 2, "Element silindikten sonra sayı doğru değil!");
    }

    @Test
    public void testRemoveAllElements() {
        // Eylem: 1 tane ekle, sonra onu sil
        addRemoveElementsPage.clickAddElement();
        addRemoveElementsPage.clickDeleteButton(0);

        // Doğrulama: Sayfada hiç silme butonu kalmadığını kanıtla (Liste boyutu 0 olmalı)
        Assert.assertEquals(addRemoveElementsPage.getAddedElementsCount(), 0, "Sayfada hala silinmemiş elementler var!");
    }
}
# The Internet (Herokuapp) - Test Otomasyon Projesi

Bu proje, test otomasyonu temellerini pekiştirmek ve sektörel pratik kazanmak amacıyla hazırlanmış bir **UI Test Otomasyonu** portfolyo çalışmasıdır. Test hedefi olarak popüler pratik ortamı olan **The Internet (the-internet.herokuapp.com)** web uygulaması kullanılmıştır.

Projede **Page Object Model (POM)** mimarisi uygulanmış, **Selenium 4** özellikleri kullanılmış, test verileri harici JSON dosyasından yönetilmiş ve temiz bir test altyapısı hedeflenmiştir.

---

## Projenin Amacı ve Öğrenilenler

Bu çalışmada aşağıdaki temel test otomasyonu pratikleri uygulanmıştır:

- **Page Object Model (POM)**: Sayfa locator'ları ve web eylemleri `pages` paketinde; test adımları ve doğrulamalar (assertions) `tests` paketinde toplanarak kod tekrarı önlendi.
- **Selenium 4 Dinleyici (ExecutionListener)**: Test koşumu sırasında hangi elemente tıklandığını veya hangi kutuya yazı yazıldığını gözle görebilmek için elementleri anlık sarı arka plan ve kırmızı çerçeveyle vurgulayan `WebDriverListener` geliştirildi.
- **Veri Güdümlü Test (Data-Driven Testing)**:
  - Login test verileri `loginData.json` dosyasından Google Gson ile dinamik olarak okundu.
  - HTTP durum kodları testinde TestNG `@DataProvider` kullanılarak aynı test farklı parametrelerle (200, 301, 404, 500) çalıştırıldı.
- **Dinamik Bekleme (Wait) Yönetimi**: `Thread.sleep` yerine `WebDriverWait` ve `ExpectedConditions` kullanılarak testlerin kararlılığı artırıldı.
- **Ağ Seviyesinde Doğrulama (HttpURLConnection)**: Durum kodları sayfasında sadece arayüze tıklamak yerine, Java'nın ağ kütüphanesi ile linklerin gerçekten doğru HTTP kodunu döndüğü teyit edildi.

---

## Kullanılan Teknolojiler

- **Java (JDK 24)**: Testlerin yazıldığı temel programlama dili.
- **Selenium WebDriver (4.35.0)**: Tarayıcı otomasyonu, element etkileşimleri, Actions API.
- **TestNG**: Test senaryolarının yönetimi, assertion doğrulamaları ve test süiti (`testng.xml`).
- **Google Gson**: `loginData.json` dosyasını okumak için kullanılan JSON kütüphanesi.
- **Apache Maven**: Proje bağımlılık ve derleme yönetimi.

---

## Mimari ve Dizin Yapısı

```text
src/
├── main/
│   ├── java/
│   │   ├── core/                           # Çekirdek yardımcı sınıflar ve dinleyiciler
│   │   │   ├── ConfigReader.java           # Ortam ve çalışma konfigürasyonlarını okur
│   │   │   ├── ExecutionListener.java      # Selenium olaylarını dinler ve elementleri vurgular
│   │   │   └── JsonDataReader.java         # JSON test verilerini okur
│   │   └── pages/                          # Page Object Model sınıfları
│   │       ├── alerts_frames_windows/      # JS Alert, Confirm, Prompt sayfaları
│   │       │   └── JavaScriptAlertsPage.java
│   │       ├── auth/                       # Giriş, çıkış ve şifre sıfırlama sayfaları
│   │       │   ├── ForgotPasswordPage.java
│   │       │   ├── LoginPage.java
│   │       │   └── SecureAreaPage.java
│   │       ├── base/                       # Sayfaların türediği ortak ata sınıf
│   │       │   └── BasePage.java
│   │       ├── elements/                   # Temel web elementleri sayfaları
│   │       │   ├── AddRemoveElementsPage.java
│   │       │   ├── BrokenImagesPage.java
│   │       │   ├── CheckboxesPage.java
│   │       │   ├── DropdownPage.java
│   │       │   ├── InputsPage.java
│   │       │   └── TyposPage.java
│   │       └── interactions/               # Fare, klavye ve ağ etkileşimi sayfaları
│   │           ├── ContextMenuPage.java
│   │           ├── HorizontalSliderPage.java
│   │           ├── HoversPage.java
│   │           ├── KeyPressesPage.java
│   │           ├── RedirectLinkPage.java
│   │           └── StatusCodesPage.java
│   └── resources/
│       └── config.properties               # Dinamik ortam ve çalışma ayarları
└── test/
    ├── java/
    │   ├── base/                           # Testlerin türediği ortak temel sınıf
    │   │   └── BaseTest.java               # Driver kurulumu, listener dekorasyonu ve temizlik
    │   └── tests/                          # Test senaryolarının bulunduğu paketler
    │       ├── alerts_frames_windows/
    │       │   └── JavaScriptAlertsTest.java
    │       ├── auth/
    │       │   ├── ForgotPasswordTest.java
    │       │   └── LoginTest.java
    │       ├── elements/
    │       │   ├── AddRemoveElementsTest.java
    │       │   ├── BrokenImagesTest.java
    │       │   ├── CheckboxesTest.java
    │       │   ├── DropdownTest.java
    │       │   ├── InputsTest.java
    │       │   └── TyposTest.java
    │       └── interactions/
    │           ├── ContextMenuTest.java
    │           ├── HorizontalSliderTest.java
    │           ├── HoversTest.java
    │           ├── KeyPressesTest.java
    │           ├── RedirectLinkTest.java
    │           └── StatusCodesTest.java
    └── resources/
        ├── loginData.json                  # Kimlik doğrulama test verileri
        └── testng.xml                      # Regresyon test süiti yapılandırması
```

---

## Test Edilen Tüm Senaryolar

Projede **14 test sınıfı** altında toplam **33 farklı test senaryosu** bulunmaktadır. Her birinin amacı aşağıda açıklanmıştır:

### 1. Kimlik Doğrulama (Auth)

#### `LoginTest.java`
1. **`testSuccessfullogin()`**
  - **Amaç:** Doğru kullanıcı adı ve doğru şifre ile sisteme başarıyla giriş yapılabildiğini doğrulamak.
2. **`testValidUsernameInvalidPassword()`**
  - **Amaç:** Doğru kullanıcı adı ve yanlış şifre girildiğinde sistemin giriş izni vermediğini doğrulamak.
3. **`invalidUsernameValidPassword()`**
  - **Amaç:** Yanlış kullanıcı adı ve doğru şifre girildiğinde sistemin hata verdiğini doğrulamak.
4. **`testInvalidLogin()`**
  - **Amaç:** Hem kullanıcı adı hem de şifre yanlış olduğunda sistemin uygun hata mesajını döndüğünü doğrulamak.
5. **`testPasswordIsMasked()`**
  - **Amaç:** Güvenlik gereği şifre alanına girilen karakterlerin gizlenip gizlenmediğini kontrol etmek.
6. **`testLogoutAndBackNavigationSecurity()`**
  - **Amaç:** Güvenli alandan çıkış yapıldıktan sonra tarayıcının "Geri" butonuna basıldığında oturumun tekrar açılmadığını (güvenlik açığı olmadığını) doğrulamak.

#### `ForgotPasswordTest.java`
7. **`testRetrivePasswordRedirect()`**
  - **Amaç:** Şifre kurtarma formunun e-posta girildikten sonra kullanıcıyı doğru adrese yönlendirdiğini kontrol etmek.

---

### 2. Arayüz Elementleri (Elements)

#### `AddRemoveElementsTest.java`
8. **`testAddSingleElement()`**
  - **Amaç:** "Add Element" butonuna bir kez basıldığında DOM'a 1 adet silme butonu eklendiğini doğrulamak.
9. **`testAddMultipleAndRemoveElements()`**
  - **Amaç:** Birden fazla element eklenebildiğini ve aralarından birinin silinebildiğini doğrulamak.
10. **`testRemoveAllElements()`**
  - **Amaç:** Eklenen element silindiğinde listede hiç buton kalmadığını doğrulamak.

#### `BrokenImagesTest.java`
11. **`testDetectBrokenImages()`**
  - **Amaç:** Sayfadaki tüm görselleri tarayarak yüklenemeyen (kırık / 404) resimleri tespit etmek.

#### `CheckboxesTest.java`
12. **`testDefaultCheckboxesStates()`**
  - **Amaç:** Checkbox'ların sayfa ilk açıldığındaki varsayılan durumlarını kontrol etmek.
13. **`testToggleCheckboxes()`**
  - **Amaç:** Kutulara tıklandığında mevcut durumlarının tersine döndüğünü (toggle) doğrulamak.

#### `DropdownTest.java`
14. **`testSelectByVisibleText()`**
  - **Amaç:** Açılır menüden kullanıcının ekranda gördüğü metne göre seçim yapmak.
15. **`testSelectByValueAttribute()`**
  - **Amaç:** Açılır menüden HTML `value` niteliğine göre seçim yapmak.
16. **`testSelectByIndex()`**
  - **Amaç:** Açılır menüden sıra numarasına (indekse) göre seçim yapmak.

#### `InputsTest.java`
17. **`testValidNumberInput()`**
  - **Amaç:** Sayısal alana geçerli bir rakam girilebildiğini doğrulamak.
18. **`testInvalidLettersInput()`**
  - **Amaç:** `type="number"` olan bir alana harf girilmesinin engellendiğini kontrol etmek.
19. **`testKeyboardArrowKeys()`**
  - **Amaç:** Klavye yön tuşlarıyla sayısal değerin artırılıp azaltılabildiğini simüle etmek.

#### `TyposTest.java`
20. **`testParagraphTextSpelling()`**
  - **Amaç:** Dinamik olarak yazım hatası barındırabilen paragraf metninin doğruluğunu kontrol etmek.
---

### 3. Kullanıcı Etkileşimleri (Interactions)

#### `ContextMenuTest.java`
21. **`testContextMenuAlert()`**
  - **Amaç:** Belirtilen kutuya sağ tıklandığında JavaScript Alert açıldığını ve metnini doğrulamak.

#### `HorizontalSliderTest.java`
22. **`testSliderToSpecificValue()`**
  - **Amaç:** Kaydırma çubuğunun klavye tuşlarıyla hedef değere getirilebildiğini doğrulamak.

#### `HoversTest.java`
23. **`testHoverUser1()`**
  - **Amaç:** İlk profil resminin üzerine fare ile gelindiğinde gizli kartın görünür hale geldiğini kontrol etmek.
24. **`testHoverAllUsers()`**
  - **Amaç:** Döngü kullanarak sayfadaki 3 profilin tamamını tek testte doğrulamak.

#### `KeyPressesTest.java`
25. **`testPressEnterKey()`**
  - **Amaç:** `ENTER` tuşuna basıldığında sistemin tuşu doğru algıladığını doğrulamak.
26. **`testPressBackspaceKey()`**
  - **Amaç:** `BACK_SPACE` tuşuna basıldığında doğru algılandığını doğrulamak.
27. **`testPressCtrlKey()`**
  - **Amaç:** `CONTROL` tuşuna basıldığında doğru algılandığını doğrulamak.
28. **`testPressAlphabetKey()`**
  - **Amaç:** Standart harf tuşuna (`"A"`) basıldığında sistemin tepkisini doğrulamak.

#### `RedirectLinkTest.java`
29. **`testRedirectionPath()`**
  - **Amaç:** Yönlendirme linkine tıklandıktan sonra tarayıcının beklenen sayfaya ulaştığını kontrol etmek.

#### `StatusCodesTest.java`
30. **`testAllStatusCodesViaHttpConnection()`**
  - **Amaç:** Sayfadaki durum kodlarının arayüz dışındaki gerçek HTTP yanıt kodlarını (200, 301, 404, 500) doğrulamak.

---

### 4. Alert Yönetimi (Alerts)

#### `JavaScriptAlertsTest.java`
31. **`testAcceptJsAlert()`**
  - **Amaç:** Tek butonlu standart alert penceresini onaylamak.
32. **`testDismissJsConfirm()`**
  - **Amaç:** Onay/İptal seçenekli alert penceresini iptal etmek.
33. **`testTypeIntoJsPrompt()`**
  - **Amaç:** Kullanıcıdan metin girişi bekleyen prompt alert penceresine veri göndermek.

---

## Projeyi Çalıştırma

Tüm testleri konsoldan çalıştırmak için:
```bash
mvn clean test
```

Tek bir test sınıfını çalıştırmak için:
```bash
mvn test -Dtest=LoginTest
```

TestNG XML süitini çalıştırmak için IDE üzerinden `src/test/resources/testng.xml` dosyasına sağ tıklayıp **Run** diyebilir ya da `pom.xml` üzerinden süiti tetikleyebilirsiniz.

### Temel Ayarlar (`config.properties`)
Testlerin hedef adresini veya bekleme sürelerini değiştirmek için `src/main/resources/config.properties` dosyası kullanılabilir:
```properties
browser=chrome
baseUrl=http://localhost:7080/
implicitWait=10
pageLoadTimeout=30

# Görsel izleme modu (true: açık, false: kapalı)
visualMode=True
visualSleepMs=500
```
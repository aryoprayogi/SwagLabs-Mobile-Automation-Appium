package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class LoginPage {
    // 1. Deklarasi variabel driver untuk berinteraksi dengan aplikasi Android
    private AndroidDriver driver;
    private WebDriverWait wait;

    // 2. Daftar Locator (Elemen UI) pada halaman login
    // Menggunakan AppiumBy.accessibilityId karena elemen memiliki atribut content-desc
    private By usernameField = AppiumBy.accessibilityId("test-Username");
    private By passwordField = AppiumBy.accessibilityId("test-Password");
    private By loginBtn = AppiumBy.accessibilityId("test-LOGIN");
    private By errorMessage = By.xpath("//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView");

    // 3. Constructor
    // Berfungsi untuk menerima instance 'driver' dari BaseTest agar bisa digunakan di class ini
    public LoginPage(AndroidDriver driver){
        this.driver = driver;
        // Setting stopwatch maksimal 10 detik
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // 4. Kumpulan Method (Aksi) yang dapat dilakukan oleh user di halaman login
    public void inputUsername(String username){
        //Tunggu maksimal 10 detik sampai kolom username MUNCUL, lalu input teks
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    public void inputPassword(String password){
        //Tunggu maksimal 10 detik sampai kolom password MUNCUL, lalu input teks
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickLogin(){
        // Tunggu maksimal 10 detik sampai tombol login BISA DIKLIK, lalu klik
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    // 5. Method untuk melakukan scroll layar ke bagian paling bawah
    public void scrollDownToCredentials(){
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(1)"));
    }

    public void scrollUpToTop(){
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(1)"));
    }

    public String getErrorMessage(){
        // 1. Tunggu elemen TextView muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

        // 2. Kasih waktu 1 detik buat animasi
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // 3. Baru ambil teksnya pakai .getAttribute("text)
        String text = driver.findElement(errorMessage).getAttribute("text");
        System.out.println("[DEBUG] Teks error yang ditangkap: '" + text + "'");

        return text;
    }


}

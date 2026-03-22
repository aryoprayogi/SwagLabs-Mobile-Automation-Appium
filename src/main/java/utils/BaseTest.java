package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    // Inisialisasi AndroidDriver secara static agar dapat diakses oleh Page Object dan Test Class
    public static AndroidDriver driver;


    @BeforeMethod
    public void setUp(){
        System.out.println("[INFO] Memulai inisialisasi sesi Appium... ");

        //Konfigurasi Desired Capabilities menggunakan UiAutomator2Options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Samsung A14 5G");
        options.setUdid("R9CW302FZVT"); //UDID: Unique Device Identifier -> didapat dari adb devices di cmd


        // Lokasi file apk untuk di install jika di HP belum terinstall
        options.setApp("C:\\QA_Automation_Workspace\\sauce-labs.apk");
        // Jika sudah terinstall, gunakan appPackage dan appActivity untuk membuka aplikasi yang sudah ter-install (Fast Launch)
        options.setAppPackage("com.swaglabsmobileapp"); //appPackage -> ID unik aplikasi
        options.setAppActivity("com.swaglabsmobileapp.MainActivity"); //AppActivity = Layar Aplikasi; MainActivity = Halaman paling depan / Splash Screen.

        try {
            // Menghubungkan script Java dengan Appium Server lokal di port 4723
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

            // Implicit Wait: Menunggu maksimal 10 detik jika elemen UI tidak langsung ditemukan
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e){
            System.out.println("[INFO] Gagal terhubung ke Appium Server. Pastikan server aktif");
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        // Validasi status pengujian (passed/failed) dan eksekusi pengambilan screenshot
        if (ITestResult.FAILURE == result.getStatus()){
            System.out.println("[ERROR] VALIDASI GAGAL: Terdeteksi kegagalan pada skenario. Mengambil screenshot bukti... ");
            takeScreenshot(result.getName() + "_FAILED");
        }
        else if (ITestResult.SUCCESS == result.getStatus()){
            System.out.println("[INFO] VALIDASI SUKSES: Skenario berjalan sesuai ekspektasi. Mengambil screenshot bukti... ");
            takeScreenshot(result.getName() + "_PASSED");
        }

        // Mengakhiri sesi Appium dan menutup aplikasi pada perangkat
        if(driver != null){
            driver.quit();
            System.out.println("[INFO] Sesi Appium diakhiri. Aplikasi ditutup.");
            System.out.println("---------------------------------------------------");
        }
    }



    // Fungsi utilitas untuk mengambil screenshot dan menyimpannya ke dalam direktori project
    public void takeScreenshot(String testName){
        // Membuat format penamaan file menggunakan timestamp agar unik (Contoh: LoginTest_PASSED_20260319_101530.png)
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";

        // Mengeksekusi perintah pengambilan screenshot dari perangkat Android
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try{
            // Menyalin file screenshot dari memori sementara ke folder "Screenshots/" di root project
            FileUtils.copyFile(screenshot, new File("Screenshots/" + fileName));
            System.out.println("[INFO] Screenshot berhasil disimpan di: Screenshots/" + fileName);
        }catch (IOException e){
            System.out.println("[ERROR] Gagal menyimpan file screenshot.");
            e.printStackTrace();
        }
    }

}
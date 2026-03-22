package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

//Internal Project Import
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "TC-01: Verify login fails with a locked-out user")
    public void testLockedOutUser(){
        System.out.println("[INFO] Executing TC-01: Negative Login - Locked Out User");

        // Panggil halaman Login
        LoginPage loginPage = new LoginPage(driver);

        // Scroll Layar ke bawah
        System.out.println("[INFO] Melakukan scroll layar ke bawah...");
        loginPage.scrollDownToCredentials();

        // Scroll Balik lagi ke atas buat isi form
        System.out.println("[INFO] Melakukan scroll balik ke atas...");
        loginPage.scrollUpToTop();

        // Aksi 1: Masukkan username dan password
        System.out.println("[INFO] Mengisi username dan password...");
        loginPage.inputUsername("locked_out_user");
        loginPage.inputPassword("secret_sauce");

        // Aksi 2: Klik tombol Login
        loginPage.clickLogin();

        // Validasi: Ambil pesan error dan bandingkan dengan ekspektasi
        System.out.println("[INFO] Memvalidasi pesan error...");
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Sorry, this user has been locked out.";

        // Assertion
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "[ERROR] VALIDASI GAGAL: Pesan error tidak sesuai");
        System.out.println("[INFO] VALIDASI SUKSES: Pesan error akun terblokir sesuai.");
    }


    @Test(priority = 2, description = "TC-02: Verify login fails with invalid credentials")
    public void testInvalidCredentials(){
        System.out.println("[INFO] Executing TC-02: Negative Login - Invalid Password");

        // Panggil halaman Login
        LoginPage loginPage = new LoginPage(driver);

        // Scroll layar ke bawah
        System.out.println("[INFO] Melakukan scroll layar ke bawah");
        loginPage.scrollDownToCredentials();

        // Scroll Balik lagi ke atas buat isi form
        System.out.println("[INFO] Melakukan scroll balik ke atas...");
        loginPage.scrollUpToTop();

        // Aksi 1: Masukan username benar dan password salah
        System.out.println("[INFO] Mengisi username dan password yang salah...");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("wrong_password");

        // Aksi 2: Klik tombol Login
        loginPage.clickLogin();

        // Validasi: Ambil pesan error dan bandingkan dengan ekspektasi
        System.out.println("[INFO] Memvalidasi pesan error...");
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Username and password do not match any user in this service.";

        // Assertion
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "[ERROR] VALIDASI GAGAL: Pesan error tidak sesuai!");
        System.out.println("[INFO] VALIDASI SUKSES: Pesan error salah password sesuai.");
    }
}

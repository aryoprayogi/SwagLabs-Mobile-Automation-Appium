package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

// Import kelas POM dan BaseTest
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class CartTest extends BaseTest {

    @Test(priority = 1, description = "TC-03: Verify user can add 2 items to the cart")
    public void testAddTwoItemsToCart(){
        System.out.println("--------------------");
        System.out.println("[INFO] Executing TC-03: Positive Cart - Menambahkan 2 Barang");

        // Tahap 1 (PRASYARAT -> Proses Autentikasi / Login)
        // Menginisialisasi objek LoginPage untuk melakukan proses login terlebih dahulu
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("[INFO] Melakukan proses login dengan akun standard_user...");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();


        // Tahap 2: Aksi Utama (Interaksi Home Page)
        // Menginisialisasi objek HomePage setelah berhasil dialihkan ke halaman utama
        HomePage homePage = new HomePage(driver);

        System.out.println("[INFO] Memilih produk untuk dimasukkan ke dalam keranjang...");
        homePage.addBackpackToCart();
        homePage.addBikeLightToCart();

        // Tahap 3: Assertion
        System.out.println("[INFO] Memvalidasi perubahan angka pada badge keranjang...");
        String actualCartCount = homePage.getCartBadgeText();
        String expectedCartCount = "2";

        // Memverifikasi apakah teks angka di keranjang sesuai dengan ekspektasi (2 barang)
        Assert.assertEquals(actualCartCount, expectedCartCount, "[ERROR] VALIDASI GAGAL: Jumlah barang pada keranjang tidak sesuai!");

        System.out.println("[INFO] VALIDASI SUKSES: Badge keranjang menunjukkan angka 2");
    }
}

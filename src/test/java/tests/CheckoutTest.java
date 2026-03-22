package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class CheckoutTest extends BaseTest {

    @Test(priority = 1, description = "TC-05: Verify error message when First Name is empty during checkout")
    public void testCheckoutWithoutFirstName(){
        System.out.println("--------------------");
        System.out.println("[INFO] Executing TC-05: Negative Checkout - Form First Name Kosong");

        // Tahap 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();

        // Tahap 2: Navigasi ke Keranjang (Melalui Home Page)
        HomePage homePage = new HomePage(driver);
        homePage.addBackpackToCart();
        homePage.addBikeLightToCart();
        homePage.clickCartIcon();

        // Tahap 3: Proses Checkout (Form Kosong)
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckoutButton();

        // Sengaja TIDAK memanggil inputFirstName() untuk memicu error
        checkoutPage.inputLastName("David");
        checkoutPage.inputPostalCode("12345");
        checkoutPage.clickContinueButton();

        // Tahap 4: Validasi
        System.out.println("[INFO] Memvalidasi pesan error pada halaman Checkout...");
        String actualErrorMessage = checkoutPage.getErrorMessage();
        String expectedErrorMessage = "First Name is required";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "[ERROR] VALIDASI GAGAL: Pesan error tidak sesuai dengan ekspektasi");
        System.out.println("[INFO] VALIDASI SUKSES: Pesan error wajib mengisi First Name muncul dengan benar.");
    }
}

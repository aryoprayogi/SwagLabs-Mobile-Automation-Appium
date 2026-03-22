package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class EndToEndTest extends BaseTest {

    @Test(priority = 1, description = "TC-06: End-to-End Shopping Flow with Price Calculation")
    public void testEndtoEndCheckout(){
        System.out.println("--------------------");
        System.out.println("[INFO] Executing TC-06: End-to-End Shopping");

        // Tahap 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();

        // ======================================================
        // Tahap 2: Beli sebanyak 2 Barang
        HomePage homePage = new HomePage(driver);
        homePage.addBackpackToCart();
        homePage.addBikeLightToCart();
        homePage.clickCartIcon();

        // ======================================================
        // Tahap 3: Isi Form Checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckoutButton();
        checkoutPage.inputFirstName("QA");
        checkoutPage.inputLastName("Master");
        checkoutPage.inputPostalCode("12345");
        checkoutPage.clickContinueButton();

        // ======================================================
        // Tahap 4: Validasi PERHITUNGAN HARGA
        System.out.println("[INFO] Mengambil data harga dari layar...");
        String rawItemTotal = checkoutPage.getItemTotalText(); //Cth: "Item total: $39.98"
        String rawTax = checkoutPage.getTaxText(); //Cth: "Tax: $3.20"
        String rawGrandTotal = checkoutPage.getGrandTotalText(); //Cth: "Total: $43.18"

        // Manipulasi Data: Membuang teks huruf, sisakan hanya angka nya, lalu ubah tipe data nya menjadi tipe data Double
        double itemTotal = Double.parseDouble(rawItemTotal.replace("Item total: $", ""));
        double tax = Double.parseDouble(rawTax.replace("Tax: $", ""));
        double actualGrandTotal = Double.parseDouble(rawGrandTotal.replace("Total: $", "")); //simpan nilai aktual nya, untuk nanti di assert

        // Rumus ekspektasi: Harga Barang + Pajak
        double expectedGrandTotal = itemTotal + tax;

        System.out.println("[INFO] Memvalidasi perhitungan: $" + itemTotal + " $" + tax + "= $" + expectedGrandTotal);

        //Assert dengan batas toleransi desimal 0.01
        Assert.assertEquals(actualGrandTotal, expectedGrandTotal, 0.01, "[ERROR] VALIDASI GAGAL: Perhitungan Total Harga salah!");
        System.out.println("[INFO] VALIDASI SUKSES: Sistem menghitung harga total dengan akurat");


        // ======================================================
        // Tahap 5: Selesaikan Pesanan
        checkoutPage.scrollToFinishAndClick();

        System.out.println("[INFO] Memvalidasi pesan sukses akhir...");
        String actualMessage = checkoutPage.getCompleteOrderMessage();
        String expectedMessage = "THANK YOU FOR YOU ORDER";

        Assert.assertEquals(actualMessage, expectedMessage, "[ERROR] VALIDASI GAGAL: Pesan sukses tidak muncul!");
        System.out.println("[INFO] VALIDASI SUKSES: Pesan sukses tampil di layar, END-TO-END TESTING BERHASIL");
    }
}

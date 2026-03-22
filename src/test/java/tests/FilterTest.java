package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class FilterTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(FilterTest.class);

    @Test(priority = 1, description = "TC-04: Verify user can sort items by Price (low to high)")
    public void testSortPriceLowToHigh(){
        System.out.println("--------------------");
        System.out.println("[INFO] Executing TC-04: Positive Dropdown - Sortir Harga Termurah");

        // Tahap 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();

        // Tahap 2: Buka Home Page & Sortir
        HomePage homePage = new HomePage(driver);
        homePage.clickFilterButton();
        homePage.selectPriceLowToHigh();

        // Tahap 3: Validasi
        System.out.println("[INFO] Memvalidasi apakah urutan pertama adalah produk termurah...");
        String actualFirstItem = homePage.getFirstItemTitle();
        String expectedFirstItem = "Sauce Labs Onesie";

        Assert.assertEquals(actualFirstItem, expectedFirstItem, "[ERROR] VALIDASI GAGAL: Barang pertama bukan " + expectedFirstItem + "!");
        System.out.println("[INFO] VALIDASI SUKSES: Produk termurah (" + expectedFirstItem + ") berada di urutan pertama");
    }
}

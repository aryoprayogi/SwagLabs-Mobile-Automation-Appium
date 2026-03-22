package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    // 1. Locators (Menggunakan Xpath Indexing karena content-desc setiap tombol add to cart kembar)
    private By backpackAddToCartBtn = By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]");
    private By bikeLightAddToCartBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]");
    private By cartBadge = By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']//android.widget.TextView");
    private By filterBtn = AppiumBy.accessibilityId("test-Modal Selector Button");
    private By priceLowToHighOption = By.xpath("//android.widget.TextView[@text='Price (low to high)']");
    private By firstItemTitle = By.xpath("(//android.widget.TextView[@content-desc='test-Item title'])[1]");
    private By cartIconBtn = AppiumBy.accessibilityId("test-Cart");

    // 2. Constructor
    public HomePage(AndroidDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 3. Actions
    public void addBackpackToCart(){
        System.out.println("[INFO] Klik Add to Cart: Sauce Labs Backpack");
        wait.until(ExpectedConditions.elementToBeClickable(backpackAddToCartBtn)).click();
    }

    public void addBikeLightToCart(){
        System.out.println("[INFO] Klik Add to Cart: Sauce Labs Bike Light");
        wait.until(ExpectedConditions.elementToBeClickable(bikeLightAddToCartBtn)).click();
    }

    public String getCartBadgeText(){
        System.out.println("[INFO] Mengambil angka dari badge keranjang...");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public void clickFilterButton(){
        System.out.println("[INFO] Menekan tombol filter (Dropdown)...");
        wait.until(ExpectedConditions.elementToBeClickable(filterBtn)).click();
    }

    public void selectPriceLowToHigh(){
        System.out.println("[INFO] Memilih opsi sortir 'Price (low to high)'...");
        wait.until(ExpectedConditions.elementToBeClickable(priceLowToHighOption)).click();
    }

    public String getFirstItemTitle(){
        System.out.println("[INFO] Mengambil nama produk pada urutan pertama...");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstItemTitle)).getText();
    }

    public void clickCartIcon(){
        System.out.println("[INFO] Menekan ikon Keranjang...");
        wait.until(ExpectedConditions.elementToBeClickable(cartIconBtn)).click();
    }
}

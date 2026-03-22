package pages;

import com.beust.ah.A;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    // 1. Daftar Locator
    private By checkoutBtn = AppiumBy.accessibilityId("test-CHECKOUT");
    private By firstNameField = AppiumBy.accessibilityId("test-First Name");
    private By lastNameField = AppiumBy.accessibilityId("test-Last Name");
    private By postalCodeField = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private By continueBtn = AppiumBy.accessibilityId("test-CONTINUE");
    private By errorMessage = By.xpath("//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView");

    //Locator untuk TC-06 (End to End Scenario hingga succes checkout)
    private By itemTotalTxt = By.xpath("//android.widget.TextView[starts-with(@text, 'Item total:')]");
    private By taxTxt = By.xpath("//android.widget.TextView[starts-with(@text, 'Tax:')]");
    private By grandTotalTxt = By.xpath("//android.widget.TextView[starts-with(@text, 'Total:')]");

    private By finishBtn = AppiumBy.accessibilityId("test-FINISH");
    private By completeMessage = By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]");

    // 2. Constructor
    public CheckoutPage(AndroidDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 3. Aksi (Method)
    public void clickCheckoutButton(){
        System.out.println("[INFO] Menggeser layar ke bawah untuk mencari tombol 'CHECKOUT'...");

        //Scroll ke bawah dengan UiScrollable
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"test-CHECKOUT\"))"));

        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public void inputFirstName(String firstName){
        System.out.println("[INFO] Mengisi kolom First Name...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        System.out.println("[INFO] Mengisi kolom Last Name...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
    }

    public void inputPostalCode(String postalCode){
        System.out.println("[INFO] Mengisi kolom Zip/Postal Code...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).sendKeys(postalCode);
    }

    public void clickContinueButton(){
        System.out.println("[INFO] Menekan tombol 'CONTINUE'...");
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public String getErrorMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

        String text = driver.findElement(errorMessage).getAttribute("text");
        System.out.println("[DEBUG] Teks error yang ditangkap: '" + text + "'");
        return text;
    }

    public String getItemTotalText(){
        System.out.println("[INFO] Menggeser layar ke bawah untuk melihat rincian harga...");

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"Item total:\"))"));

        System.out.println("[INFO] Mengambil nilai 'Item total'...");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalTxt)).getText();
    }

    public String getTaxText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(taxTxt)).getText();
    }

    public String getGrandTotalText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(grandTotalTxt)).getText();
    }

    public void scrollToFinishAndClick(){
        System.out.println("[INFO] Menggeser layar ke bawah untuk mencari tombol 'FINISH'...");
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"test-FINISH\"))"));

        System.out.println("[INFO] Menekan tombol 'FINISH'...");
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public String getCompleteOrderMessage(){
        System.out.println("[INFO] Menunggu halaman konfirmasi pesanan...");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeMessage)).getText();
    }
}

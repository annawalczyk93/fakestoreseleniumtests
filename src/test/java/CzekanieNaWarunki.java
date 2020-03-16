import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CzekanieNaWarunki {
    WebDriver driver;
    WebDriverWait wait;
    By cookieConsentBar = By.cssSelector("a[class'='dismiss-link']");
    By pilatesGroup = By.cssSelector("a[href'='pilates']");
    By product = By.cssSelector("li.post-61");
    By addToCartButton = By.cssSelector("button[name='add-to-cart']");
    By goToCartButton = By.cssSelector("a.cart-contens");
    String correctCoupon = "";

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl");

        wait = new WebDriverWait(driver, 10);

    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();

    }
    @Test
    public void infoOfElements(){}}
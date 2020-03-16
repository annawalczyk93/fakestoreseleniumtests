import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;


public class KilkaOkienPrzegladarki {
    WebDriver driver;
    WebDriverWait wait;
    By demoStoreNoticeDismiss = By.cssSelector("a[class='dismiss-link']");
    By pilatesGroup = By.cssSelector("a[href='pilates']");
    By product = By.cssSelector("li.post-61");

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl");
        wait = new WebDriverWait(driver, 5);
       // driver.findElement(demoStoreNoticeDismiss).click();
        driver.findElement(pilatesGroup).click();
        driver.findElement(product).click();
    }

    @Test
    public void removeFromWishListTest() {
    By addToWishList = By.cssSelector(".add_to_wishlist");
    driver.findElement(addToWishList).click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(addToWishList)); //link dodany do listy życzeń nam zniknie
        By wishListLink = By.cssSelector("#menu-item-248");
        driver.findElement(wishListLink).click();
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        windows.remove(parentWindow);
        String wishListWindow = windows.iterator().next();
        driver.switchTo().window(wishListWindow);
        By removeFromWishList = By.cssSelector(".remove_from_wishlist");
        driver.findElement(removeFromWishList).click();
        final By emptyWishList = By.cssSelector("td.wishlist-empty"); //"nie dodano zadnych produktow do listy zyczen''
        wait.until(ExpectedConditions.presenceOfElementLocated(emptyWishList));

        Assertions.assertDoesNotThrow(()->wait.until(ExpectedConditions.presenceOfElementLocated(emptyWishList)),
                "WishList is not empty");
    }

    @AfterEach
    public void close() {

        driver.close();
        driver.quit();
    }
}

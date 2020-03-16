import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;


public class InteractingWithWebElements {

    // implicity wait - zainicjalizowane raz działa dla każdego findElements pod driverem. gdy nie znajdzie elementu w zadanym czasie - NoSuchElementException.
    // pageLoadTimeout - gdy strona nie załaduje się w podanym czasie, rzuci timeoutException.

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));

    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();

    }
                //klikanie całego scenariusza testowego:
    @Test
    public void testZooniverse() {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
       driver.navigate().to("https://www.zooniverse.org/");

        driver.findElement(By.cssSelector("button[value='sign-in']")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("hasłotestowe");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // lub zamaist driver.findElement(By.cssSelector("button[type='submit']")).submit(); to
        //driver.findElement(By.cssSelector("form")).submit();
        //driver.findElement(By.cssSelector("button[type='submit']")).clear();


        //driver.findElement(By.cssSelector("input[name='login']")).sendKeys(Keys.CONTROL,"a");

        Assertions.assertEquals("MALAMI", driver.findElement(By.cssSelector("span[class='account-bar'] strong")).getText(),
                "Username is not correct. The user was probably not logged in.");
    }
}
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class Ramki {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.nasa.gov/");
    }

    @Test
    public void frameExample() {
        WebElement frame = driver.findElement(By.cssSelector("iframe#twitter-widget-0"));
        driver.switchTo().frame(frame); //numeruje się od 0, driver.switchTo().frame(0);
        //driver.switchTo().defaultContent();
        driver.switchTo().parentFrame(); //namierza logo NASA
        driver.findElement(By.cssSelector("div.navabr-header>a.logo"));
    }

        @AfterEach
        public void close() {

            driver.close();
            driver.quit();
        }
    }


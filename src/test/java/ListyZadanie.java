import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ListyZadanie{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product-category/windsurfing/");
        wait = new WebDriverWait(driver, 5);
    }
    @Test
    public void FilterAndSortDesc() {
        WebElement dropdownElement  = driver.findElement(By.cssSelector("[class='orderby']"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue("price-desc");
        WebElement spanWithPrice = driver.findElement(By.cssSelector("ul[class='products columns-3']>li:first-child>a:first-child>span>span"));
    Assertions.assertEquals("5 399,00 zł", spanWithPrice.getText());
    }

    @AfterEach
    public void close() {

        driver.close();
        driver.quit();
    }
}
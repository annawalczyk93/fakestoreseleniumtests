package KlasyObiektyPola;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class ZadanieRamki {
    WebDriver driver;
    By demoStoreBar = By.cssSelector("a.class'='dismiss-link']");

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-ramek/");
        driver.findElement(demoStoreBar).click();
    }

    @Test
    public void mainPageButtonDisableTest() {
        driver.switchTo().frame("main-frame");
        WebElement mainPage = driver.findElement(By.cssSelector("input[name='main-page']"));
        Assertions.assertFalse(mainPage.isEnabled(), "Main page button is not disabled.");
    }

    @Test
    public void imageLinkTest() {
        driver.switchTo().frame("main-frame");
        driver.switchTo().frame("image"); // tego troszke nie rozumiem,czemu piszemy jedno po drugim
        WebElement mainPAgeLink = driver.findElement(By.xpath(".//img[@alt='Wakacje']/.."));
        Assertions.assertEquals("https://fakestore.testelka.pl/", mainPAgeLink.getAttribute("href"));
    }

    @Test
    public void mainPAgeButtonEnabledTest() {
        driver.switchTo().frame("main-frame")
                .switchTo().frame("image")
                .switchTo().frame(0); // pierwsza i jedyna ramka pod poprzedzającą jej ramką
        WebElement mainPAgeButton = driver.findElement(By.cssSelector("a.button"));
        Assertions.assertTrue(mainPAgeButton.isEnabled(), "Main PAge Button is not enabled.");
    }

    @Test
    public void logoDisplayedTest() {
        driver.switchTo().frame("main-frame")
                .switchTo().frame("image")
                .switchTo().frame(0);
        WebElement mainPAgeButton = driver.findElement(By.cssSelector("a.button"));
        mainPAgeButton.click();
        driver.switchTo().parentFrame()
                .switchTo().parentFrame();
        WebElement climbingButton = driver.findElement((By.cssSelector("a[name='climbing']")));
        climbingButton.click();
        WebElement logo = driver.findElement(By.cssSelector("img.custom-logo"));
        Assertions.assertTrue(logo.isDisplayed(), "Logo is not displayed");
    }

    @AfterEach
    public void close() {

        driver.close();
        driver.quit();
    }
}

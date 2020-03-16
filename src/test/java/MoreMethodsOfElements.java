import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MoreMethodsOfElements {
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
    @Test
    public void infoOfElements(){

        driver.navigate().to("https://fakestore.testelka.pl/");

    WebElement element = driver.findElement(By.cssSelector("#masthead"));
    String text = element.getText();
    String attribute = element.getAttribute("role");
    String cssValue = element.getCssValue("background-color");
    String tag = element.getTagName();
    Point location = element.getLocation();
    Dimension size = element.getSize();
    Rectangle locationAndSize = element.getRect();
    boolean isDisplayed = element.isDisplayed();
    boolean isSelected  = element.isSelected();
    boolean isEnabled = element.isEnabled();

    }
}





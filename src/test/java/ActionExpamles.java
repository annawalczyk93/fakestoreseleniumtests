import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionExpamles {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://jqueryui.com/selectable/#default");
        wait = new WebDriverWait(driver, 5);
        actions = new Actions(driver);
    }

    @Test
    public void clickExample() {
        Actions actions = new Actions(driver);

        //  actions.moveByOffset(488,380).click().build().perform();

        driver.switchTo().frame(0);
        List<WebElement> listElements = driver.findElements(By.cssSelector("#selectable>li"));
        WebElement firstElement = listElements.get(0);
        actions.click(firstElement).build().perform();
    }
    @Test
    public  void doubleClickExample() {
    driver.navigate().to("https://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
    //actions.moveByOffset(330,173).doubleClick().build().perform();

        WebElement box = driver.findElement(By.cssSelector("#box"));
        actions.doubleClick(box).build().perform();
    }

    @AfterEach
    public void close() {

        driver.close();
        driver.quit();
    }
}
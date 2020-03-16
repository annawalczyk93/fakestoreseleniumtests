import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FIndingObjects {
    WebDriver driver;

    //inicjalizacja Drivera (beforeeach i afterEach) zeby po każdym wykonanym teście zamykała się sesja:
    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("Http://wikipedia.pl");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findingElementById() {
        WebElement searchField = driver.findElement(By.id("searchInput"));
        driver.findElement(By.name("search"));
        driver.findElement(By.className("searchButton"));
        //driver.findElement(By.className("external text"));
        int numberOfImages = driver.findElements(By.tagName("img")).size(); // można np sprawdzić liczbę buttonów
        System.out.println(numberOfImages);
    }
    @Test
    public void findingElementByLinkText(){
        driver.findElement(By.linkText("Wikisłownik"));
        driver.findElement(By.partialLinkText("redagować"));
    }
}
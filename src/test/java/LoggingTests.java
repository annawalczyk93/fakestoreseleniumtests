import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoggingTests {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();

    }

    @Test
    public void LoggingWithIncorrectData() {
        WebElement userNameInput = driver.findElement(By.id("username"));
        userNameInput.sendKeys("username");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("password");
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        WebElement error = driver.findElement(By.cssSelector("[class='woocommerce-error']"));
        Assertions.assertTrue(error.isDisplayed(), "non visible element with error");
        WebElement messageError = driver.findElement(By.cssSelector("[class='woocommerce-error']>li>strong"));
        Assertions.assertEquals("BŁĄD", messageError.getText(), "tekst jest inny niż oczekiwany");
    }

    @Test
    public void LogginWithCorrectData(){
        this.Login("annawiktoriawalczyk","password=123^&");
        WebElement mojeKonto = driver.findElement(By.cssSelector("[class='entry-title']"));
        Assertions.assertEquals("Moje konto",mojeKonto.getText(), "wszystko źle" );

    }

    private void Login(String username, String password){
        WebElement userNameInput = driver.findElement(By.id("username"));
        userNameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        //WebDriverWait waiter = new WebDriverWait(driver, 5, 1000);

    }



        //annawiktoriawalczyk
        //password=123^&



}
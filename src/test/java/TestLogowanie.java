import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestLogowanie {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @Test
    public void LogIn() {
        zalogujSie("annawiktoriawalczyk", "password=123^&");

        WebElement entryTitle = driver.findElement(By.cssSelector("h1[class='entry-title']"));
        Assertions.assertEquals(true, entryTitle.isDisplayed());
        Assertions.assertEquals("Moje konto", entryTitle.getText());
        WebElement displyedUserName = driver.findElement(By.cssSelector("p>strong:nth-of-type(1)"));
        Assertions.assertEquals("annawiktoriawalczyk", displyedUserName.getText());
    }
    @Test
    public void loginFailed (){
        zalogujSie("annaw", "/password=123^&");
    }

    public void zalogujSie(String login, String password){
        WebElement inputDoLogowania = driver.findElement(By.id("username")); //.sendKeys('annawiktoriawalczyk');
        inputDoLogowania.sendKeys(login);
        WebElement inputDoHasla = driver.findElement(By.id("password"));
        inputDoHasla.sendKeys(password);
        WebElement buttonDoLogowania = driver.findElement(By.name("login"));
        buttonDoLogowania.click();
    }

    @AfterEach
    public void close() {

        driver.close();
        driver.quit();

    }

}

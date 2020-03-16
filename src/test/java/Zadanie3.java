import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.GregorianCalendar;

public class Zadanie3 {
    WebDriver driver;

    @BeforeEach
    public void driverSetUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
        Thread.sleep(2000);
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void cookiesExercise() throws InterruptedException {
        driver.manage().getCookies(); //pobieranie wszystkich ciasteczek
        Assertions.assertEquals(4, driver.manage().getCookies().size(), "Number of cookies is now what expected. ");

        Cookie newCookie = new Cookie("test_cookie", "test_value", ".wikipedia.org", "/",
                new GregorianCalendar(2019, 12, 18).getTime(), true, true);
        driver.manage().addCookie(newCookie);

        Assertions.assertEquals(5,driver.manage().getCookies().size() , "Number of cookies is not what expected.");
    }
}

//<input type="search" name="search" placeholder="Przeszukaj Wikipedię" title="Przeszukaj Wikipedię [alt-shift-f]" accesskey="f" id="searchInput" tabindex="1" autocomplete="off">
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanko2 {
    WebDriver driver;
    //inicjalizacja Drivera (beforeeach i afterEach) zeby po każdym wykonanym teście zamykała się sesja:
    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }
    @Test
    public void testIfLanguageChanged(){
        //1. przejdź na stronę http://wikipedia.pl
        driver.navigate().to("http://wikipedia.pl");
        //2.Napisz trzy sercje:
        //a. porównaj tytuł strony z oczekiwanym;
        String expectedPolishTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(expectedPolishTitle, driver.getTitle(), "Page title is not: " + expectedPolishTitle);
        Assertions.assertTrue(driver.getTitle().contains("wolna encyklopedia"), "Page title does not contain 'wolna encyklopedia" + expectedPolishTitle);
        //b. porównaj URL strony z oczekiwanym;

        String expectedPolishURL = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        Assertions.assertEquals(expectedPolishURL, driver.getCurrentUrl(), "Current URL is not: " + expectedPolishURL );
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna"), "Current page URL does :" + expectedPolishURL);

        //c. znajdź w konsoli deweloperskiej (F12) w zakładce Elements jakiś fragment źródła strony, któy mówi o tym w jakiej
        String language = "lang=\"pl\"";
        Assertions.assertTrue(driver.getPageSource().contains(language), "Page source does not contain" + language);

        //3. zmień język strony na hiszpański (By.cssSelector("a[title='hiszpański']")).
        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        //4. Napisz trzy sercje:
        //a. porównaj tytuł strony z oczekiwanym;
        //b. porównaj URL strony z oczekiwanym;
        //c. znajdź w konsoli deweloperskiej (F12) w zakładce Elements jakiś fragment strony, który mówi o tym w jakiej

        String expectedSpanishTitle = "Wikipedia, la enciclopedia libre";
        Assertions.assertEquals(expectedSpanishTitle, driver.getTitle(), "Page title is not: " + expectedSpanishTitle);
        String expectedSpanishURL = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        Assertions.assertEquals(expectedSpanishURL, driver.getCurrentUrl(), "Current URL is not " + expectedSpanishURL);
        String spanishLanguage = "lang=\"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(spanishLanguage), "PAge source does not contain" + spanishLanguage);
    }
}

//* Assertions.notEquals
//Assertions.assertEquals (spróbować się wnawigować do wikipedia pl ale w innej wersji jezykowej)

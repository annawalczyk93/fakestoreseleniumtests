import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie1 {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(); // ta linijka odpowiada za otwieranie się przeglądarki
        driver.manage().window().setSize(new Dimension(1295, 730));
    }
    @AfterEach
    public void driverQuit(){
        // zamyka okno przeglądarki:
        driver.close();
        //zamyka sesje:
        driver.quit();
    }
    @Test
    public void getCurrentURLExample(){
        String googleUrl = "https://www.google.pl/";
        driver.navigate().to("https://google.pl"); // testowanie przekierowania, czy mechanizm działa:
        Assertions.assertEquals(googleUrl, driver.getCurrentUrl(),"Current URL is not:" + googleUrl);
    }

    @Test
    public void getTitleExample(){
        String googleTitle = "Google";
        driver.navigate().to("https://google.pl");
        Assertions.assertEquals(googleTitle, driver.getTitle(), "Page title is not: " + googleTitle);
    }

    @Test
    public void navigationZadanie() {
        //otwiera strone glowna wikipedi:
        driver.navigate().to("http://wikipedia.pl");
        // otwiera stronę główną nasa:
        driver.navigate().to("http://www.nasa.gov");
        //cofnie się do strony wikipedii, uzwyając nawigacji wstecz:
        driver.navigate().back();
        // potwierdź że driver jest na stronie Wikipedii: porównaj (Assertions.assertEquals ()) tytul strony z oczekiwanym:
        String wikiTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(wikiTitle, driver.getTitle(), "The title of the page is not:" + wikiTitle);
        //przejdź do strony NASA:
        driver.navigate().forward();
        //Potwierdź że driver jest na stronie NASA: porówjnaj tytuł strony z oczekiwanym:
        String nasaTitle = "NASA";
        Assertions.assertEquals(nasaTitle, driver.getTitle(), "The title of the page is not: " + nasaTitle);

    }
}
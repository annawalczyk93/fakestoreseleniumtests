import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class NavigationAndClosing {

    WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(); // ta linijka odpowiada za otwieranie się przeglądarki
        driver.manage().window().setSize(new Dimension(1295, 730));
    }
    @AfterEach
    public void driverQuit(){
        driver.quit();
    }

    @Test
    public void getMethod() {
        driver.get("http://google.pl");
    }

    @Test
    public void navigate() {
//        URL googleURL = null;
//        try{
//            googleURL = new URL ("http://google.pl");
//        } catch (MalformedURLException e){
//            e.printStackTrace(); // opcja, która też działa jesli chcemy poprzez stringa nawigować do danej strony
//        }
        driver.navigate().to("http://google.pl");
        driver.navigate().to("http://www.amazon.com"); // otworzy żądaną stronę
        driver.navigate().back(); // spowrotem wróci do google
        driver.navigate().forward(); // znów na amazonie
        driver.navigate().refresh(); // odświezy się
    }
}
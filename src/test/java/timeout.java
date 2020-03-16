import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class timeout {

    // implicity wait - zainicjalizowane raz działa dla każdego findElements pod driverem. gdy nie znajdzie elementu w zadanym czasie - NoSuchElementException.
    // pageLoadTimeout - gdy strona nie załaduje się w podanym czasie, rzuci timeoutException.
 WebDriver driver;

 @BeforeEach
    public void driverSetup(){
     System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
     driver = new ChromeDriver();
     driver.manage().window().setSize(new Dimension(1295, 730));
     driver.manage().window().setPosition(new Point(10, 40));

 }

 @AfterEach
    public void driverQuit(){
     driver.close();
     driver.quit();

 }

 @Test
    public void test (){

     driver.navigate().to("https://www.dunckelfeld.de/");
     driver.findElement(By.xpath(".//h2[@style='margin-left: 0px;']"));
 }
}

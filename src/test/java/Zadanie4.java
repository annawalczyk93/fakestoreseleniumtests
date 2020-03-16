import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie4 {
    @Test
    public void FindElements() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");

        driver.findElement(By.id("woocommerce-product-search-field-0"));
        driver.findElement(By.id("username"));
        driver.findElement(By.id("password"));
        driver.findElement(By.name("login"));
        driver.findElement(By.id("rememberme"));
        driver.findElement(By.partialLinkText("pamiętasz"));
        driver.findElement(By.linkText("Żeglarstwo"));

        driver.close();
        driver.quit();
    }
    @Test
    public void CSSSelector(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-fragmenty-wartosci-atrybutow/");

        //driver.findElements("By.cssSelector"));
       // driver.findElement(By.cssSelector("[id='twotabsearchtextbox']"));


        driver.close();
        driver.quit();
    }

}

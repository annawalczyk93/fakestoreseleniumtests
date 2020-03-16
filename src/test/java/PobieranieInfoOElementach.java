import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PobieranieInfoOElementach {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));

    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();

    }

    //klikanie całego scenariusza testowego:
    @Test
    public void testSklepu() {

        driver.navigate().to("https://fakestore.testelka.pl/metody-na-elementach/");

        Assertions.assertEquals(false, driver.findElement(By.cssSelector("input[type='button']")).isEnabled(),
                "Button \'Strona Glowna\' is not disabled.");

        Assertions.assertEquals(false, driver.findElement(By.cssSelector("a[value='żeglarstwo']")).isDisplayed(),
                "button is displayed");


       List<WebElement> listaPrzyciskow = driver.findElements(By.cssSelector("a[class='button']"));
        for (WebElement button : listaPrzyciskow) {
            Assertions.assertEquals("rgba(245, 233, 101, 1)", button.getCssValue("background-color"));
        }


        WebElement selectedCheckBox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement notselectedCheckBox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement selectedRadioButton = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement notselectedRadioButton = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));

        Assertions.assertTrue(selectedCheckBox.isSelected(), "this checkbox is not selected");
        Assertions.assertTrue(selectedRadioButton.isSelected(), "this radiobutton is not selected");
        Assertions.assertFalse(notselectedCheckBox.isSelected(), "this checkbox is selected");
        Assertions.assertFalse(notselectedRadioButton.isSelected(), "this notselectedRadiobutton is selected");


        //potwierdź, że wszystkie elementy z klasą button, są elementami o tagu a;
       List<WebElement> buttonAsClass = driver.findElements(By.cssSelector("[class='button']"));
       for (WebElement element: buttonAsClass){
           String tagName = element.getTagName();
           String expected = "a";
           Assertions.assertEquals(expected,tagName);
       }



    }


}





/*        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://www.zooniverse.org/");

        driver.findElement(By.cssSelector("button[value='sign-in']")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("hasłotestowe");
        driver.findElement(By.cssSelector("button[type='submit']")).click();*/
// lub zamaist driver.findElement(By.cssSelector("button[type='submit']")).submit(); to
//driver.findElement(By.cssSelector("form")).submit();
//driver.findElement(By.cssSelector("button[type='submit']")).clear();}


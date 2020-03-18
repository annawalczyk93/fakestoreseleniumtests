package TestProjectTestelka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestProject1 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/80/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");
        wait = new WebDriverWait(driver, 20);
    }

    @Test //użytkownik ma możliwość dodania wybranej wycieczki do koszyka ze strony tej wycieczki,
    public void addElement() throws InterruptedException {
        //znajduję wybraną wycieczkę
        WebElement chooseTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:first-of-type"));

        //zapisanie kwoty jaka trzeba zapłącić za wycieczkę żeby porównać później z wartością jaka będzie w koszyku
        WebElement chosenTripPriceElement = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a>span"));
        String chosenTripPrice = chosenTripPriceElement.getText();

        // klikam w wybrana wycieczke
        chooseTrip.click();

        //button dodaj do koszyka i kilkam w niego
        WebElement addToBasket = driver.findElement(By.cssSelector("button[name='add-to-cart']"));

        //moja metoda do zeskrolowania przegladarki zeby mozna bylo kliknac w element
        centerWindowOnElement(addToBasket);
        addToBasket.click();

        //ZNAJDZ ikonke koszyka -> poczekaj az bedzie mozliwa do klikniecia i kliknij w nia
        Thread.sleep(10000);
        By findCartSelector = By.cssSelector("[class='cart-contents']");
        WebElement findCart = driver.findElement(findCartSelector);
        wait.until(ExpectedConditions.presenceOfElementLocated(findCartSelector));
        findCart.click();

        // driver.navigate().to("https://fakestore.testelka.pl/koszyk/");
        WebElement spanWithPrice = driver.findElement(By.cssSelector("td[class*='product-price']>span"));
        WebElement quantityInput = driver.findElement(By.cssSelector("td[class*='product-quantity']>div>input"));
        Assertions.assertEquals("1", quantityInput.getAttribute("value"));
        Assertions.assertEquals(chosenTripPrice, spanWithPrice.getText());
    }


    @Test
    public void category() { //użytkownik ma możliwość dodania wybranej wycieczki do koszyka ze strony kategorii,

        WebElement chooseCategory = driver.findElement(By.cssSelector("[class*='storefront-product-categories']>div>ul>li[class*='first']"));

        WebElement chosenCategoryPriceElement = driver.findElement(By.cssSelector("ul[class*='products']>li[class*='386']>a>span>:first-of-type"));
        String chosenCategoryPrice = chosenCategoryPriceElement.getText();
        chooseCategory.click(); // klikam w nią

        WebElement chooseWindsurfing = driver.findElement(By.cssSelector("ul[class*='products']>li:first-of-type>a[class*='button']"));
        centerWindowOnElement(chooseWindsurfing);
        chooseWindsurfing.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul[class*='products']>li:first-of-type>a[class*='wc']"))).click();

        //ide sobie do strony koszyka, gdzie potwierdzam .navigate().to("https://fakestore.testelka.pl/koszyk/");
        WebElement spanWithPriceWindsurfing = driver.findElement(By.cssSelector("td[class*='product-price']>span"));
        WebElement quantityInputWindsurfing = driver.findElement(By.cssSelector("td[class*='product-quantity']>div>input"));
        Assertions.assertEquals("1", quantityInputWindsurfing.getAttribute("value"));
        Assertions.assertEquals(chosenCategoryPrice, spanWithPriceWindsurfing.getText());
    }

    @Test
    public void tenTrips() {
        //kilkam w wybrana wycieczke na stronie głównej
        WebElement trip = driver.findElement(By.cssSelector("ul>li[class*='product type-product post-391']>a:first-of-type"));
        trip.click();

        //ustawiam 10 wycieczek i przechodze do koszyka
        WebElement quantity = driver.findElement(By.name("quantity"));
        quantity.clear();
        quantity.sendKeys("10");
        WebElement submitButton = driver.findElement(By.cssSelector("button[class*='single_add_to_cart']"));
        submitButton.click();

        driver.navigate().to("https://fakestore.testelka.pl/koszyk/");

        //sprawdzam czy jest 10 wycieczek dodanych
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td[class*='product-quantity']>div>input")));
        WebElement quantityOfTrips = driver.findElement(By.cssSelector("td[class*='product-quantity']>div>input"));
        Assertions.assertEquals("10", quantityOfTrips.getAttribute("value"));
    }

    @Test
    public void tenTripsSeparate() {

        WebElement firstTrip = driver.findElement(By.cssSelector("ul>li[class*='product type-product post-391']>a:first-of-type"));
        firstTrip.click();

        WebElement quantity = driver.findElement(By.name("quantity"));
        quantity.clear();
        quantity.sendKeys("8");
        WebElement submitButton = driver.findElement(By.cssSelector("button[class*='single_add_to_cart']"));
        submitButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("\".woocommerce-message>.button\"")));
        //  driver.findElement(By.className("custom-logo")).click();
        driver.navigate().to("https://fakestore.testelka.pl");

        WebElement secondtTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:first-of-type"));
        secondtTrip.click();

        WebElement quantitySecond = driver.findElement(By.name("quantity"));
        quantitySecond.clear();
        quantitySecond.sendKeys("2");
        WebElement submitButtonSecond = driver.findElement(By.cssSelector("button[class*='single_add_to_cart']"));
        submitButtonSecond.click();

        driver.navigate().to("https://fakestore.testelka.pl/koszyk/");

        List<WebElement> quantitiesOfTrips = new ArrayList<>();
        quantitiesOfTrips = driver.findElements(By.cssSelector("td[class*='product-quantity']>div>input"));
        int suma = 0;
        for (WebElement quantityWebElement : quantitiesOfTrips) {
            String quantityString = quantityWebElement.getAttribute("value");
            int quantityInt = Integer.parseInt(quantityString);
            suma = suma + quantityInt;
        }
        Assertions.assertEquals(10, suma);
    }

    @Test
    public void changeNumberOfProducts() {
        //użytkownik ma możliwość zmiany ilości wybranej wycieczki (pojedynczej pozycji) na stronie koszyka

        WebElement addChoosenTripToCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:last-of-type"));
        centerWindowOnElement(addChoosenTripToCart);
        addChoosenTripToCart.click();//wybieram wycieczkę ze strony głownej kilkając w 'dodaj do koszyka

        WebElement showCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a[class*='added_to']"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click(); //przechodzę do koszyka i zmieniam ilość wycieczek z 1 na 2

        // WebElement price = driver.findElement(By.cssSelector("form[class*='woocommerce-cart-form'] table[class*='shop']>tbody td:last-of-type>span[class*='woocommerce-Price-amount']"));
        // String test = price.getText();
        WebElement quantity = driver.findElement(By.cssSelector("[class='input-text qty text']"));
        quantity.clear();
        quantity.sendKeys("2"); //zmieniam ilość z 1 na 2
        WebElement updateBasket = driver.findElement(By.name("update_cart"));
        updateBasket.click();

        WebElement quantityUpdated = driver.findElement(By.cssSelector("[class='input-text qty text']"));
        Assertions.assertEquals("2", quantityUpdated.getAttribute("value"));
    }

    @Test
    public void removeFromBasket() {
        //użytkownik ma możliwość usunięcia wycieczki na stronie koszyka (całej pozycji),
        //1.dodać produkt i zobaczyć koszyk 2.usunać produkt z koszyka 3.sprawdzić asercją czy koszyk jest pusty

        WebElement addTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:last-of-type"));
        centerWindowOnElement(addTrip);
        addTrip.click();
        WebElement showCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a[class*='added_to']"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click();

        WebElement quantity = driver.findElement(By.cssSelector("[class='input-text qty text']"));
        quantity.clear();

        WebElement emptyBasket = driver.findElement(By.cssSelector("[class='input-text qty text']"));
        Assertions.assertEquals("", emptyBasket.getAttribute("value"));
    }

    @Test
    public void errorMessage() {
        //użytkownik jest informowany o błędach w formularzu na stronie płatności poprzez odpowiednie komunikaty,
        //dodaje wycieczkę, przechodzę do koszyka, z koszyka do zamowienia lub nawiguję bezpośrednio do zamowienia,
        // wypełniam dane, czekam nakomunikat z błędem, lokalizuje go, potwierdzam

        WebElement addTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:last-of-type"));
        centerWindowOnElement(addTrip);
        addTrip.click();
        WebElement showCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a[class*='added_to']"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click();

        driver.navigate().to("https://fakestore.testelka.pl/zamowienie");


        By loadingIcon = By.cssSelector(".blockOverlay");

        if(driver.findElements(loadingIcon).size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        }

        //przełączenie się na ramkę
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("[name='__privateStripeFrame8']")));
        WebElement cardNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div>span>input:first-of-type")));

       // WebElement cardNumberInput = driver.findElement(By.cssSelector("div>span>input:first-of-type"));
       // centerWindowOnElement(cardNumberInput);
        cardNumberInput.sendKeys("6565 7656 4564 6546");

        driver.switchTo().defaultContent();
        By errorMessageLocator = By.cssSelector("[class*='stripe-error']>li");
        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageLocator));

        WebElement errorMessage = driver.findElement(errorMessageLocator);
        Assertions.assertEquals("Numer karty nie jest prawidłowym numerem karty kredytowej.", errorMessage.getText());
    }

    @Test
    public void loggIntoAccount() {
        //użytkownik ma możliwość zalogowania się na stronie płatności i dokonać płatności jako zalogowany użytkownik
        driver.navigate().to("https://fakestore.testelka.pl/zamowienie/");
        WebElement showLogin = driver.findElement(By.cssSelector("[class='showlogin']"));
        showLogin.click();



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


    @Test
    public void createAccount() {
        //użytkownik ma możliwość założenia konta na stronie płatności i dokonać jednocześnie płatności,
    }

    @Test
    public void buyWithoutCreatedAccount() {
        //użytkownik ma możliwość dokonania zakupu bez zakładania konta,
    }

    @Test
    public void showOrder() {
        //użytkownik, który posiada konto może zobaczyć swoje zamówienia na swoim koncie,
    }

    @Test
    public void afterPlacingTheOrder() {
        //użytkownik po dokonaniu zamówienia może zobaczyć podsumowanie, które zawiera numer zamówienia, poprawną datę, kwotę, metodę płatności, nazwę i ilość zakupionych produktów.
    }


    private void centerWindowOnElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    @AfterEach
    public void close() {
        driver.close();
        driver.quit();
    }
}


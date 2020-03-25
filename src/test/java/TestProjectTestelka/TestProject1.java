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
import java.util.UUID;
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
    public void userIsInformedAboutErrorsInFormOnThePaymentPageThroughAppropriateMessages() {
        //dodaje wycieczkę, przechodzę do koszyka, z koszyka do zamowienia lub nawiguję bezpośrednio do zamowienia,
        // wypełniam dane, czekam na komunikat z błędem, lokalizuje go, potwierdzam

        WebElement addTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:last-of-type"));
        centerWindowOnElement(addTrip);
        addTrip.click();
        WebElement showCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a[class*='added_to']"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click();

        driver.navigate().to("https://fakestore.testelka.pl/zamowienie");

        By loadingIcon = By.cssSelector(".blockOverlay");

        if (driver.findElements(loadingIcon).size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        }
        //przełączenie się na ramkę
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("[name='__privateStripeFrame8']")));
        WebElement cardNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div>span>input:first-of-type")));

        cardNumberInput.sendKeys("6565 7656 4564 6546");

        driver.switchTo().defaultContent();
        By errorMessageLocator = By.cssSelector("[class*='stripe-error']>li");
        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageLocator));

        WebElement errorMessage = driver.findElement(errorMessageLocator);
        Assertions.assertEquals("Numer karty nie jest prawidłowym numerem karty kredytowej.", errorMessage.getText());
    }

    @Test
    public void userHasTheOptionOfLoggingIntoPaymentPageAndMakingPaymentAsLoggedInUser() {

        WebElement addTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:last-of-type"));
        centerWindowOnElement(addTrip);
        addTrip.click();
        WebElement showCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a[class*='added_to']"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click();

        driver.navigate().to("https://fakestore.testelka.pl/zamowienie/");
        WebElement showLogin = driver.findElement(By.cssSelector("[class='showlogin']"));
        showLogin.click();

        logInto("annawiktoriawalczyk", "password=123^&");
        WebElement loginClick = driver.findElement(By.cssSelector("[name='login']"));
        loginClick.click();
        //płacę jako zalogowany uzytkownik

        //asercja
    }

    //metoda żeby się zalogować na stronę, tj wprowadzić dane do logowania
    private void logInto(String login, String password) {

        WebElement inputLogIn = driver.findElement(By.id("username"));
        inputLogIn.sendKeys(login);
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys(password);
        WebElement logInButton = driver.findElement(By.name("login"));
        logInButton.click();
    }

    @Test
    public void userCanCreateAnAccountOnThePaymentPageAndMakePaymentsAtTheSameTime() throws InterruptedException {
        //dodaj do koszyka, idz do strony zamowienia, wypelnij pola, zaznacz kilki, nr karty 4000000000003220 0225 123,
        // potwierdz asercją z numerem zamowienia (czyli zamowienie istnieje)

        WebElement addTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:last-of-type"));
        centerWindowOnElement(addTrip);
        addTrip.click();
        WebElement showCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a[class*='added_to']"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click();

        driver.navigate().to("https://fakestore.testelka.pl/zamowienie");
        Thread.sleep(3000);
        fillForm();
        fillCardDetails();
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("[id='createaccount']")).click();
        fillField(By.cssSelector("[name='account_password']"), "123frytkI");

        WebElement termsPoint = driver.findElement(By.cssSelector("[name='terms']"));
        centerWindowOnElement(termsPoint);
        termsPoint.click();
        WebElement placeOrder = driver.findElement(By.cssSelector("[id='place_order']"));
        placeOrder.click();

        WebElement completeOrder = driver.findElement(By.cssSelector("[id='place_order']"));
        centerWindowOnElement(completeOrder);
        completeOrder.click();

        By loadingIcon = By.cssSelector(".blockOverlay");

        if (driver.findElements(loadingIcon).size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        }

        WebElement orderConfirmation = driver.findElement(By.cssSelector("[class*='hankyou-order-received']"));
        Assertions.assertTrue(orderConfirmation.isDisplayed());
    }

    private void fillForm() {
        By nameField = By.cssSelector("[name=billing_first_name]");
        By lastName = By.cssSelector("[name=billing_last_name]");
        By countryDropdownList = By.cssSelector("[id='billing_country']");
        By streetField = By.cssSelector("[name='billing_address_1']");
        By cityField = By.cssSelector("[name='billing_city']");
        By postCode = By.cssSelector("[name='billing_postcode']");
        By phoneField = By.cssSelector("[name='billing_phone']");
        By emailField = By.cssSelector("[name='billing_email']");
        By stateDropDownList = By.cssSelector("[name='billing_state']");


        fillField(nameField, "Darek");
        fillField(lastName, "Glupi");
        selectFromDropdown(countryDropdownList, "AU");
        fillField(streetField, "Ulicowa 11");
        fillField(cityField, "Miasto");
        fillField(postCode, "12345");
        fillField(phoneField, "605789123");
        String uuid = UUID.randomUUID().toString();
        String email = uuid + "@onet.pl";
        fillField(emailField, email);
        selectFromDropdown(stateDropDownList, "VIC");
    }

    private void fillField(By selector, String value) {
        driver.findElement(selector).sendKeys(value);
    }

    private void fillCardDetails() throws InterruptedException {
        By cardNumber = By.cssSelector("div>span>input:first-of-type");
        By expiryDateField = By.cssSelector("[name='exp-date']");
        By cvvField = By.cssSelector("[name='cvc']");

        By cardFrameLocator = By.cssSelector("[name='__privateStripeFrame8']");
        By expiryDateFrameLocator = By.cssSelector("[name='__privateStripeFrame9']");
        By cvvFieldFrameLocator = By.cssSelector("[name='__privateStripeFrame10']");

        fillElementInFrame(cardFrameLocator, cardNumber, "4242424242424242");
        fillElementInFrame(expiryDateFrameLocator, expiryDateField, "223");
        fillElementInFrame(cvvFieldFrameLocator, cvvField, "431");
    }

    private void fillElementInFrame(By frameLocator, By elementLocator, String value) throws InterruptedException {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt((frameLocator)));
        slowType(value, elementLocator);
    }

    private void selectFromDropdown(By selector, String value) {
        Select dropdown = new Select(driver.findElement(selector));
        dropdown.selectByValue(value);
    }

    private void slowType(String value, By locator) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        for (int i = 0; i < value.length(); i++) {
            String cyfra = Character.toString(value.charAt(i));
            element.sendKeys(cyfra);
            //  Thread.sleep(1000);
        }
    }

    @Test
    public void userCanMakeAPurchaseWithoutCreatingAnAccount() throws InterruptedException {

        WebElement addTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:last-of-type"));
        centerWindowOnElement(addTrip);
        addTrip.click();
        WebElement showCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a[class*='added_to']"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click();

        driver.navigate().to("https://fakestore.testelka.pl/zamowienie");
        Thread.sleep(3000);
        fillForm();
        fillCardDetails();
        driver.switchTo().defaultContent();
        WebElement termsPoint = driver.findElement(By.cssSelector("[name='terms']"));
        centerWindowOnElement(termsPoint);
        termsPoint.click();
        WebElement placeOrder = driver.findElement(By.cssSelector("[id='place_order']"));
        placeOrder.click();

        WebElement completeOrder = driver.findElement(By.cssSelector("[id='place_order']"));
        centerWindowOnElement(completeOrder);
        completeOrder.click();

        By loadingIcon = By.cssSelector(".blockOverlay");

        if (driver.findElements(loadingIcon).size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        }
        WebElement orderConfirmation = driver.findElement(By.cssSelector("[class*='hankyou-order-received']"));
        Assertions.assertTrue(orderConfirmation.isDisplayed());
    }

    @Test
    public void userWhoHasAnAccountCanViewTheirOrdersInIheirAccount() throws InterruptedException {

        WebElement addTrip = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a:last-of-type"));
        centerWindowOnElement(addTrip);
        addTrip.click();
        WebElement showCart = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a[class*='added_to']"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click();

        driver.navigate().to("https://fakestore.testelka.pl/zamowienie");
        Thread.sleep(3000);
        fillForm();
        Thread.sleep(3000);
        fillCardDetails();
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("[id='createaccount']")).click();
        fillField(By.cssSelector("[name='account_password']"), "123frytkI");

        WebElement termsPoint = driver.findElement(By.cssSelector("[name='terms']"));
        centerWindowOnElement(termsPoint);
        termsPoint.click();
        WebElement placeOrder = driver.findElement(By.cssSelector("[id='place_order']"));
        placeOrder.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='hankyou-order-received']")));

        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/zamowienia");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='woocommerce-orders-table__row woocommerce-orders-table__row--status-processing order']")));
        WebElement orderConfirmation = driver.findElement(By.cssSelector("[class='woocommerce-orders-table__row woocommerce-orders-table__row--status-processing order']"));

        Assertions.assertTrue(orderConfirmation.isDisplayed());
    }

    @Test
    public void afterPlacingOrderUserCanSeeASummaryThatContainsPurchasedDetails() throws InterruptedException {

        WebElement addTrip = driver.findElement(By.cssSelector("li[class*='pilates']:first-of-type>a:nth-of-type(2)"));
        centerWindowOnElement(addTrip);

        String tripName = driver.findElement(By.cssSelector("li[class*='pilates']:first-of-type h2")).getText();
        String tripPrice = driver.findElement(By.cssSelector("li[class*='pilates']:first-of-type ins>span")).getText();
        //String tripAmount = driver.findElement(By.cssSelector("[class*='storefront-recent-products']>div>ul>li[class*='first']>a>span")).getText();

        addTrip.click();
        WebElement showCart = driver.findElement(By.cssSelector("li[class*='pilates']:first-of-type>a:nth-of-type(3)"));
        wait.until(ExpectedConditions.elementToBeClickable(showCart));
        showCart.click();

        driver.navigate().to("https://fakestore.testelka.pl/zamowienie");
        Thread.sleep(3000);
        String tripQuantity = driver.findElement(By.cssSelector("[class='product-quantity']")).getText();
        Boolean paymentMethodCreditCard = driver.findElement(By.cssSelector("[id=\"payment_method_stripe\"]")).isSelected();

        Thread.sleep(3000);
        fillForm();
        Thread.sleep(3000);
        fillCardDetails();
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("[id='createaccount']")).click();
        fillField(By.cssSelector("[name='account_password']"), "123frytkI");

        WebElement termsPoint = driver.findElement(By.cssSelector("[name='terms']"));
        centerWindowOnElement(termsPoint);
        termsPoint.click();
        WebElement placeOrder = driver.findElement(By.cssSelector("[id='place_order']"));
        placeOrder.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='hankyou-order-received']")));

        WebElement orderNumber = driver.findElement(By.cssSelector("[class='woocommerce-order-overview__order order']"));
        WebElement correctData = driver.findElement(By.cssSelector("[class='woocommerce-order-overview__date date']"));
        WebElement amount = driver.findElement(By.cssSelector("[class='woocommerce-order']>ul>li[class*='total']>strong>span"));
        WebElement paymentMethod = driver.findElement(By.cssSelector("[class='woocommerce-order-overview__payment-method method']"));
        WebElement nameOfProduct = driver.findElement(By.cssSelector("[class='woocommerce-table__product-name product-name']>a"));
        WebElement quantityOfProducts = driver.findElement(By.cssSelector("[class='product-quantity']"));

        Assertions.assertTrue(orderNumber.isDisplayed(), "zamówienie nie ma numeru zamówienia");
        Assertions.assertEquals(tripPrice, amount.getText());
        Assertions.assertEquals(tripName, nameOfProduct.getText());
       // Assertions.assertEquals(paymentMethodCreditCard, paymentMethod.isSelected());

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


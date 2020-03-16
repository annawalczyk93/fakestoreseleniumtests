package AccessModifiers1;

public class BasicPage {
    String title;
    String url;
    String username;

    void goTo(String pageUrl){
        System.out.println("Going to page .");
    }
    void searchForProduct(String product){
        System.out.println("Searching for product " + product);
    }
}

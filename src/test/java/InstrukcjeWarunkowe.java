import org.junit.jupiter.api.Test;

public class InstrukcjeWarunkowe {
    @Test
    public void ifExample() {
        int liczba = 88;

        if (liczba > 100) {
            System.out.println("Liczba " + liczba + " jest większa od 100");
            liczba++;
        } else if (0 <= liczba && liczba <= 100) {
            System.out.println("Liczba " + liczba + " jest większa lub równa 0 i mniejsza lub równa 100");

            liczba = 100;
        } else {
            System.out.println("Liczba " + liczba + " jest liczbą ujemną.");
        }

        System.out.println("Koniec testu. Liczba jest równa " + liczba);
    }
    @Test
    public void switchExample(){
        int liczba = 0;
        switch (liczba){
            case 0:
                System.out.println("Liczba jest równa 0");
                break;
            case 1:
                System.out.println("Liczba jest równa 1");
                break;
            case 2:
                System.out.println("Liczba jest równa 2");
                break;
            default:
                System.out.println("Liczba jest nieprawidłowa");
        }
    }
}

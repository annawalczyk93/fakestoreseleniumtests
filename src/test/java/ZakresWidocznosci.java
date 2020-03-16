import org.junit.jupiter.api.Test;

public class ZakresWidocznosci {
    int liczba1 = 5;
    int liczba2 = 4;
    int suma = liczba1 + liczba2;
    int liczba3;

    @Test
    public void wypiszLiczbe() {

        int liczbWMetodzie = 2;

        for (int i = 0; i < 10; i++) {
            System.out.println("Zmienna liczba1 jest równa: " + liczba1 + ". Wartość i wynosi: " + i);
            int loknaSuma = liczba1 + i;
            if (i >= 5) {
                String komunikat = "i jest większa lub równe 5.";
                System.out.println(komunikat);
            }
        }


    }

}

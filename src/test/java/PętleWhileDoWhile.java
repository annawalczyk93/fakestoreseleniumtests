import org.junit.jupiter.api.Test;

import java.util.Random;

public class PętleWhileDoWhile {
    @Test
    public void Whileexamples() {
        int silnia5 = 1 * 2 * 3 * 4 * 5;
        int silnia4 = 1 * 2 * 3 * 4;
        int silnia0 = 1;
        int silnia6 = obliczsilnie(6);
        //czyToMojaLiczba(4);
        obliczsilnie(6);
    }

    private int obliczsilnie(int liczba) {
        int silnia = 1;
        int i = 1;

        if (liczba > 1) while (i <= liczba) {
            silnia = silnia * i;
            i++;

        }
        return silnia;
    }
/*
    private void czyToMojaLiczba(int liczba) {
        int licznik=0;
        Random generator = new Random();

            System.out.println("Liczba nie jest równa " + liczba);
            licznik++;
        }
        while(generator.nextInt(5)!=liczba);


        while (generator.nextInt(5)!=liczba) {
            System.out.println("Liczba nie jest równa " + liczba);
            licznik++;
        }
        System.out.println("Wylosowało moją liczę. Pętla wykonała się "+ licznik + "razy");
    }*/
}

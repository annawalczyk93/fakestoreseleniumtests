package KlasyObiektyPola;

import org.junit.jupiter.api.Test;

public class PodstawyTest {
    @Test
    public void podstawyTest() {

        int pierwszaLiczba = 12;
        int drugaLiczba = 14;
        dodaj(pierwszaLiczba, drugaLiczba);
        odejmowanie(pierwszaLiczba, drugaLiczba);
        mnozenie(pierwszaLiczba, drugaLiczba);
        dzielenie(pierwszaLiczba, drugaLiczba);
        potegowanie(pierwszaLiczba);
        liczbaDoPotegi(2,10);

    }

    void dodaj(int liczba1, int liczba2) {
        int wynik = liczba1 + liczba2;
        System.out.println(liczba1 + "+" + liczba2 + "=" + wynik);
    }

    void odejmowanie(int liczba1, int liczba2) {
        int wynik = liczba1 - liczba2;
        System.out.println(liczba1 + "-" + liczba2 + "=" + wynik);
    }

    void mnozenie(int liczba1, int liczba2) {
        int wynik = liczba1 * liczba2;
        System.out.println(liczba1 + "*" + liczba2 + "=" + wynik);
    }

    void dzielenie(int liczba1, int liczba2){
        int wynik = liczba1/liczba2;
        System.out.println(liczba1+"/"+liczba2+"="+wynik);
    }
    void potegowanie (int liczba1){
        int wynik = liczba1*liczba1;
        System.out.println(liczba1+"^2"+"="+wynik);
    }
    void liczbaDoPotegi (int liczba1, int potega){
        int wynik = 1;
        for (int i=0; i<potega; i++){
            wynik = wynik*liczba1;
        }
        System.out.println(liczba1 + "do potegi" + potega + "="+ wynik);
    }
}

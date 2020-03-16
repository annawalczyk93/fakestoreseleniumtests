package KlasyObiektyPola;

import org.junit.jupiter.api.Test;

public class OperacjeNaObiektach {
    @Test
    public void obiektyTest() {
        Czlowiek obiektTypuCzlowiek = new Czlowiek();
        obiektTypuCzlowiek.przedstawSie();
        obiektTypuCzlowiek.imie="Darek";
        obiektTypuCzlowiek.wiek=30;
        obiektTypuCzlowiek.przedstawSie();

        Czlowiek innyObiektTypuCzlowiek = new Czlowiek();
        innyObiektTypuCzlowiek.imie="Darlos";
        innyObiektTypuCzlowiek.przedstawSieZNazwiskiem(obiektTypuCzlowiek.imie);
    }
}

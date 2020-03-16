package KlasyObiektyPola;

public class Czlowiek {
    public String imie ="nie mam imienia";
    public int wiek = 4;

    public void przedstawSie (){
        System.out.println("mam na imie "+ imie + " mam "+ wiek + " lat.");
    }
    public void przedstawSieZNazwiskiem (String nazwisko){
        System.out.println("nazywam sie  "+imie + nazwisko+" i mam " + wiek +" lat.");
    }
}

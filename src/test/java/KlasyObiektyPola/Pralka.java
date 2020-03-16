package KlasyObiektyPola;

public class Pralka {
    //POLA

public int program;
public boolean isPralkaOn;
public boolean isDoorOpen;

    //METODY
    public void zmianaProgramu (int numerProgramu) {
        program = numerProgramu;
    }
    public void wlaczenie (){
        isPralkaOn = !isPralkaOn;
    }
    public void otworzDrzwi (){
        if(!isDoorOpen)
            isDoorOpen = true;
    }

    public void zamknijDrzwi(){
        isDoorOpen = false;
    }
}

package TestelkaKursJavyDlaTesterow;

public class KlasyObiektyPola {
    //deklarujemy zmienne i inicjalizujemy je:
    private String starName = "sun";
    private String starType = "yellow drawl";
    private int numberOfPlanets = 8;

    public String getStarName() { //ta metoda już nie jest prywatna
        return starName;
    }

    public void setNumberOfPlanets(int number) {
        numberOfPlanets = number;
    }
    public int getNumberOfPlanets (){
        return numberOfPlanets;
    }
    public void printNumberOfPlanets(){
        System.out.println("Number of planets:  "+ numberOfPlanets);
    }
}

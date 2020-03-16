package KlasyObiektyPola;

public class PlanetarySystem {

    //POLA
    private String starName = "sun";
    private String starType = "yellow dwarf";
    private int numberOfPlanets = 8;

    // METODY
    public String getStarName(){
            return starName;
        }

    public void setNumberOfPlanets(int number) {
            numberOfPlanets = number;
    }
    public int getNumberOfPlanets(){
            return numberOfPlanets;
    }
    public void printNumberOfPlanets(){
            System.out.println("Number of planets is: " + getNumberOfPlanets());
    }
}

package TestelkaKursJavyDlaTesterow;

import org.junit.jupiter.api.Test;

public class PlanetarySystemTests { //to jest klasa testowa
    //tutaj będziemy używać obiektu klasy PlanetarySystem
    //tworzymy metodę testową żebyśmy mogli potem to wyprintować na konsolę, yey:

    @Test
    public void firstTest(){ //to jest metoda testowa
        //tworzę zmienną typu planetary system ,czyli tworzę obiekt planetary system:

        KlasyObiektyPola solarSystem = new KlasyObiektyPola();
        System.out.println("star name is: " + solarSystem.getStarName());
        System.out.println("Number of planets : " + solarSystem.getNumberOfPlanets());
        solarSystem.setNumberOfPlanets(7);
        System.out.println("Number of planets : " + solarSystem.getNumberOfPlanets());

        KlasyObiektyPola anotherSystem = new KlasyObiektyPola();
        anotherSystem.printNumberOfPlanets();
        anotherSystem.setNumberOfPlanets(5);
        anotherSystem.printNumberOfPlanets();
    }
}

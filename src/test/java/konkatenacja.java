import org.junit.jupiter.api.Test;

public class konkatenacja {
    @Test
    public void example6() {
        String[] planety = new String[8];

        planety[0] = "Merkury";
        planety[1] = "Wenus";
        String planetaPierwsza = planety[0];
    }

    @Test
    public void example7() {
        String[] planety = {"Maerkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran", "Neptun"};
        String czwartaplaneta = planety[3];

    }
}




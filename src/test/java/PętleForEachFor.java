import org.junit.jupiter.api.Test;

public class PętleForEachFor<silnia> {

        @Test
        public void Whileexamples() {
            int silnia5 = 1 * 2 * 3 * 4 * 5;
            int silnia4 = 1 * 2 * 3 * 4;
            int silnia0 = 1;
            int silnia6 = obliczsilnie(6);
            obliczsilnie(6);
        }

        private int obliczsilnie(int liczba) {
            int silnia = 1;

            if(liczba>1){
                for(int i = 2; i<=liczba;i++){
                silnia = silnia*i;
                }
            }
            return silnia;
            }

            @Test
    public void forEachExample(){
            String[] planety = new String[]{"Merkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran", "Neptun"};
            String tekst = "Planety naszego układu słonecznego to:";

            for(String planeta : planety){
                tekst = tekst + "\n" + planeta;

            }
            System.out.println(tekst);
            }
}

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
    private static List<Card> deck;
    public GameState(){

    }

    public static void initialiseState()  {
        try {
            deck = createDeck();
        }catch (IOException e ){
            System.out.println("Searching for file in ");
            e.printStackTrace();
        }
    }
    private static ArrayList createDeck() throws IOException {
        ArrayList<Card> tempDeck = new ArrayList<>();
        File f = new File("res/riskCards.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readline = "";
        while ((readline = b.readLine()) != null) {     //loops through each line in the text file until there is no more content
            String region = readline.split("-")[0]; //each line of the file consits of {region} - {unit type} so we split on a regex of '-'
            switch (readline.trim().split("- ")[1]) {
                case "Canon":
                    tempDeck.add(new Card(region, UnitType.CANON));
                    break;
                case "Horse":
                    tempDeck.add(new Card(region, UnitType.HORSE));
                    break;
                case "Soldier":
                    tempDeck.add(new Card(region, UnitType.SOLDIER));
            }
        }
        Collections.shuffle(tempDeck);  //shuffles deck randomly
        return tempDeck;
    }
}

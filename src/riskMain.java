import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum UnitType {CANNON, CAVALRY, INFANTRY }
enum Player {USER, AI, NEUTRAL}
public class riskMain {
    private static List<Card> deck ;
    public static void main(String[] args) throws IOException{
        try {
            deckCreator();
        }catch(IOException e){
            e.printStackTrace();
        }
        for(Card c : deck){
            System.out.println(c.getRegion());
        }
        territoryAssigner(Player.USER);
    }
    private static void territoryAssigner(Player player){
        int i =
        for ()
    }
    private static void deckCreator()throws IOException{    //reads riskCards.txt file and creates risk playing card objects
        deck = new ArrayList<>();
        File f = new File("res/riskCards.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readline = "";
        while ((readline = b.readLine()) != null) {     //loops through each line in the text file until there is no more content
            String region = readline.split("-")[0]; //each line of the file consits of {region} - {unit type} so we split on a regex of '-'
            System.out.println(region);
            switch (readline.split("-")[1]) {
                case "CANNON":
                    deck.add(new Card(region, UnitType.CANNON));
                    break;
                case "Cavalry":
                    deck.add(new Card(region, UnitType.CAVALRY));
                    break;
                case "Infantry":
                    deck.add(new Card(region, UnitType.INFANTRY));
            }
        }
        Collections.shuffle(deck);  //shuffles deck randomly
    }

}

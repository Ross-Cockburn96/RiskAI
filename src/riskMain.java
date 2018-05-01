import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    }
    private static void deckCreator()throws IOException{
        deck = new ArrayList<>();
        File f = new File("res/riskCards.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readline = "";
        while ((readline = b.readLine()) != null) {
            String region = readline.split("-")[0];
            System.out.println(region);
            String unit = readline.split("-")[1];
            deck.add(new Card(region, unit));
        }
        Collections.shuffle(deck);
    }

}

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
    private static void deckCreator()throws IOException{    //reads riskCards.txt file and creates risk playing card objects
        deck = new ArrayList<>();
        File f = new File("res/riskCards.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readline = "";
        while ((readline = b.readLine()) != null) {     //loops through each line in the text file until there is no more content
            String region = readline.split("-")[0]; //each line of the file consits of {region} - {unit type} so we split on a regex of '-'
            System.out.println(region);
            String unit = readline.split("-")[1];
            deck.add(new Card(region, unit));   //creates new card data type and adds it to deck
        }
        Collections.shuffle(deck);  //shuffles deck randomly
    }

}

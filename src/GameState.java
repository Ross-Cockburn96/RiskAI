import java.io.*;
import java.util.*;

public class GameState {
    private static List<Card> deck;
    public static Map<String, Region> regions ;
    private static Map<String, Continent> continents;

    public GameState(){
    }
    public static void initialiseState()  {
        try {
            deck = createDeck();
        }catch (IOException e ){
            System.out.println("Searching for file in ");
            e.printStackTrace();
        }
        createRegions();
        createContinents();
    }

    private static void createRegions(){
        regions = new HashMap<>();
        for (Card c : deck){
            regions.put(c.getRegionName(), new Region(c.getRegionName()));
        }
    }
    private static void createContinents(){
        continents = new HashMap<>();
        HashMap<String, Region> australia = new HashMap<>();
        HashMap<String, Region> africa = new HashMap<>();
        HashMap<String, Region> europe = new HashMap<>();
        HashMap<String, Region> asia = new HashMap<>();
        HashMap<String, Region> n_America = new HashMap<>();
        HashMap<String, Region> s_America = new HashMap<>();
        for (Card c : deck){
            switch (c.getContinent()){
                case "Australia":
                    australia.put(c.getRegionName(), regions.get(c.getRegionName()));
                    break;
                case "Africa":
                    africa.put(c.getRegionName(), regions.get(c.getRegionName()));
                    break;
                case "Europe":
                    europe.put(c.getRegionName(), regions.get(c.getRegionName()));
                    break;
                case "Asia":
                    asia.put(c.getRegionName(), regions.get(c.getRegionName()));
                    break;
                case "N_America":
                    n_America.put(c.getRegionName(), regions.get(c.getRegionName()));
                    break;
                case "S_America":
                    s_America.put(c.getRegionName(), regions.get(c.getRegionName()));
            }
        }
        continents.put("Australia", new Continent(australia));
        continents.put("Europe", new Continent(europe));
        continents.put("Asia", new Continent(asia));
        continents.put("Africa", new Continent(africa));
        continents.put("N_America", new Continent(n_America));
        continents.put("S_America", new Continent(s_America));

    }
    private static ArrayList createDeck() throws IOException {
        ArrayList<Card> tempDeck = new ArrayList<>();
        File f = new File("res/riskCards.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readline = "";
        while ((readline = b.readLine()) != null) {     //loops through each line in the text file until there is no more content
            String region = readline.trim().split("-")[0]; //each line of the file consits of {region} - {unit type} so we split on a regex of '-'
            String continent = readline.trim().split("-")[2];
            switch (readline.trim().split("- ")[1]) {
                case "Canon":
                    tempDeck.add(new Card(region, UnitType.CANON, continent));
                    break;
                case "Horse":
                    tempDeck.add(new Card(region, UnitType.HORSE, continent));
                    break;
                case "Soldier":
                    tempDeck.add(new Card(region, UnitType.SOLDIER, continent));
            }
        }
        Collections.shuffle(tempDeck);  //shuffles deck randomly
        return tempDeck;
    }
}

import java.io.*;
import java.util.*;

public class GameState {
    private static List<Card> deck;
    private  Map<String, Region> regions ;
    private  Map<String, Continent> continents;
    private static final String[] continentNames = new String[]{"Australia", "Europe", "Asia", "Africa", "N_America", "S_America"};


    public void initialiseState()  {
        try {
            createContinentsAndRegions();
        }catch (IOException e ){
            System.out.println("Searching for file in ");
            e.printStackTrace();
        }
        assignTerritoriesToPlayers();    //this method deals the cards to the players assigning them initial ownership of regions
        deck.add(new Card(null,UnitType.WILDCARD, null));
        deck.add(new Card(null, UnitType.WILDCARD, null));
        Collections.shuffle(deck);
    }
    private void createContinentsAndRegions() throws IOException{
        regions = new HashMap<>();
        continents = new HashMap<>();
        for (String s : continentNames) {
            HashMap<String, Region>regionsInCont = new HashMap<>();
            File f = new File("res/" + s + "Cards.txt");    //opens appropriate text file that contains continent's regions
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readline = "";
            while ((readline = b.readLine()) != null) {     //loops through each line in the text file until there is no more content
                String region = readline.split(",")[0]; //splits the contents to get region name
                String continent = readline.split(",")[2]; //splits the contents to get the continent name
                Region temp = new Region(region);
                regions.put(region,temp);
                regionsInCont.put(region, temp);
            }
            continents.put(s,new Continent(regionsInCont));
        }
    }
    public static void createDeck() throws IOException {
        ArrayList<Card> tempDeck = new ArrayList<>();
        File f = new File("res/AllRiskCards.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readline = "";
        while ((readline = b.readLine()) != null) {     //loops through each line in the text file until there is no more content
            String region = readline.split(",")[0]; //each line of the file consits of {region} - {unit type} so we split on a regex of '-'
            String continent = readline.split(",")[2];
            switch (readline.split(",")[1]) {
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
        deck =  tempDeck;
    }
    private void assignTerritoriesToPlayers(){
        int counter =0;
        while(counter < 42) {
            for (Player p : Player.values()) {
                try{
                    Card card = deck.get(counter);
                    regions.get(card.getRegionName()).setOwner(p);
                    counter++;
                }catch(IndexOutOfBoundsException e){
                    break;
                }

            }
        }
    }

}

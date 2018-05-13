import java.io.*;
import java.util.*;

public class GameState {
    private static List<Card> deck;
    private  Map<String, Region> regions ;
    private  Map<String, Continent> continents;
    private static  Map<String, Integer> continentReinforcementValues ;
    private Map<PlayerEnum, Player> players;
    public static final String[] continentNames = new String[]{"Australia", "Europe", "Asia", "Africa", "N_America", "S_America"};

    public void initialiseState()  {
        addContinentValues();
        try {
            createContinentsAndRegions();
        }catch (IOException e ){
            System.out.println("Searching for file in ");
            e.printStackTrace();
        }
        createPlayers();
        assignTerritoriesToPlayers();    //this method deals the cards to the players assigning them initial ownership of regions
        deck.add(new Card(null,UnitType.WILDCARD, null));
        deck.add(new Card(null, UnitType.WILDCARD, null));
        Collections.shuffle(deck);
    }
    private void addContinentValues(){
        continentReinforcementValues = new HashMap<>();
        continentReinforcementValues.put("Australia", 2);
        continentReinforcementValues.put("Europe", 5);
        continentReinforcementValues.put("Asia", 7 );
        continentReinforcementValues.put("Africa", 3);
        continentReinforcementValues.put("N_America", 5);
        continentReinforcementValues.put("S_America", 2);
    }
    private void createPlayers(){
        players = new HashMap<>();
        players.put(PlayerEnum.USER, new User());
        players.put(PlayerEnum.AI, new AI());
        players.put(PlayerEnum.NEUTRAL, new Neutral());
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
                Region temp = new Region(region);
                regions.put(region,temp);
                regionsInCont.put(region, temp);
            }
            System.out.println(regionsInCont.size());
            continents.put(s,new Continent(regionsInCont, continentReinforcementValues.get(s)));
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
            for (PlayerEnum p : PlayerEnum.values()) {
                try{
                    Card card = deck.get(counter);
                    regions.get(card.getRegionName()).setOwner(p);
                    players.get(p).addOwnedRegion(regions.get(card.getRegionName()));
                    counter++;
                }catch(IndexOutOfBoundsException e){
                    break;
                }

            }
        }
    }
    public Continent getContinent(String continentName){
        return continents.get(continentName);
    }
    public Player getPlayer(PlayerEnum player){
        return players.get(player);
    }
}

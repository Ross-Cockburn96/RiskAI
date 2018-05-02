import java.util.HashMap;

public class GameTest {
    public GameTest(){
        start();
    }
    private void start(){
        HashMap<Subject, Player> continentRegions = new HashMap<>();
        Region japan = new Region("Japan"); //initialises 3 regions. These are subjects
        Region china = new Region("China");
        Region congo = new Region("Congo");
        //for the purposes of this demo japan, china, and congo exist as a continent
        continentRegions.put(japan,Player.NEUTRAL); //populate a hashmap with region --> owner values
        continentRegions.put(china,Player.AI);
        continentRegions.put(congo,Player.USER);

        HashMap<Subject, Player> continent2Regions = new HashMap<>();
        Region siam = new Region("Siam"); //initialises 2 regions. These are subjects
        Region egypt = new Region("Egypt");
        //for the purposes of this demo siam and egypt exist as a continent
        continent2Regions.put(siam, Player.USER);   //populate a hashmap with region --> owner values
        continent2Regions.put(egypt, Player.USER);

        ContinentObserver continent = new ContinentObserver(continentRegions);  //creates an observer called "continent" with first hashmap
        ContinentObserver continnent2 = new ContinentObserver(continent2Regions); //creates a 2nd observer called "continent2" with second hashmap
        japan.setOwner(Player.AI);  //player AI has taken ownership of japan, change the state of japan. (japans observers will be notified - in this case "continent")
        congo.setOwner(Player.AI); //player AI has taken ownership of congo. Same applies
        egypt.setOwner(Player.NEUTRAL); //player Neutral has taken ownership of egypt. this time when the state changes, it is continent2 that is notified because that is the continent that egypt belongs to.


    }
}

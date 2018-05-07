import java.util.HashMap;

public class Continent implements Observer {
    private Player owner;

    //static used as counter
    private static int observerIDTracker = 0;
    private int observerID;
    private HashMap<Region, Player> regionOwner;   //this is a map of the regions within the continent and the player that owns each region

    public Continent(HashMap<Region, Player> regions ){  //in order to create a continent a hashmap must be supplied containing the regions that exist in the continent and the player that owns each region
        regionOwner = regions;
        this.observerID = ++ observerIDTracker;
        System.out.println("new observer " + this.observerID);
        for (Subject s : regionOwner.keySet()){ //each region in the continent must register this observer
            s.register(this);
        }
    }
    @Override
    public void update(Region region, Player ownership) {  //called when one of the observed regions' states has changed
        regionOwner.put(region,ownership);
        printRegionOwners();
    }
    private void printRegionOwners(){
        System.out.println(regionOwner.keySet() + " values " + regionOwner.values());
    }

}

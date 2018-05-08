import java.util.HashMap;

public class Continent implements Observer {
    private Player owner;

    //static used as counter
    private static int observerIDTracker = 0;
    private int observerID;
    private HashMap<String, Region> regions;

    public Continent(HashMap<String, Region> regions ){  //in order to create a continent a hashmap must be supplied containing the regions that exist in the continent and the player that owns each region
        this.regions = regions;
        this.observerID = ++ observerIDTracker;
        System.out.println("new observer " + this.observerID);
        for (Subject s : regions.values()){ //each region in the continent must register this observer
            s.register(this);
        }
    }
    @Override
    public void update(Region region, Player ownership) {  //called when one of the observed regions' states has changed
        regions.get(region).setOwner(ownership);
        printRegionOwners();
    }
    private void printRegionOwners(){
        System.out.println(regions.values());
    }

}
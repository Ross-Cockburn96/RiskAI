import java.util.ArrayList;
import java.util.HashMap;

public class Continent implements Observer {
    private PlayerEnum owner;

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
    public void update(Region region) {  //called when one of the observed regions' states has changed
        System.out.println("Region " + region + " is now owned by " + region.getOwner());
    }

    @Override
    public int getObserverID() {
        return observerID;
    }

    public int getObserverId(){
        return observerID;
    }
    public ArrayList<Region> getRegionsInContinent(){
        return new ArrayList<>(regions.values());
    }
}

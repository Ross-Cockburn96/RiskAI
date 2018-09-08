import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Continent implements Observer {
    private PlayerEnum owner = null;
    private int worth;

    //static used as counter
    private static int observerIDTracker = 0;
    private int observerID;
    private HashMap<String, Region> regions;

    public Continent(HashMap<String, Region> regions, int value ){  //in order to create a continent a hashmap must be supplied containing the regions that exist in the continent and the player that owns each region
        this.regions = regions;
        this.worth = value;
        this.observerID = ++ observerIDTracker;
        System.out.println("new observer " + this.observerID);
    }
    public void registerWithRegions(){
        for (Subject s : regions.values()){
            s.register(this);
        }
    }
    @Override
    public void update(Region region) {  //called when one of the observed regions' states has changed
        System.out.println("Region " + region + " is now owned by " + region.getOwner());
        owner = continentIsOwned(region);
    }

    @Override
    public int getObserverID() {
        return observerID;
    }

    public ArrayList<Region> getRegionsInContinent(){
        return new ArrayList<>(regions.values());
    }
    private PlayerEnum continentIsOwned(Region reg){
        if(owner != null){ //if the continent was previously owned before update was called, then it is guaranteed to not be owned now
            return null;
        }else{
            PlayerEnum potentialOwner = reg.getOwner(); //for a continent to be owned all regions must be owned by same player. So takes the first region's owner to compare to the other regions
            for (Region r: regions.values()) {
                if (r.getOwner() == null){
                    continue;
                }
                if(!(r.getOwner().equals(potentialOwner))){
                    return null;
                }
            }
            return potentialOwner;
        }
    }
    public PlayerEnum getOwner(){
        return owner;
    }
    public int getReinforcementValue(){
        return worth;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*This class represents regions on the game board that make up continents
it implements the Subject interface as it may be observed by any observer that is interested in the state of this region
an example of such an observer is a continent.
 */

public class Region implements Subject{
    private List<Observer> observers;
    private PlayerEnum owner;
    private String name;
    private ArrayList<Region> neighbours = new ArrayList<>();
    private List<UnitType> occupyingUnits = new ArrayList<>(Arrays.asList(UnitType.SOLDIER));
    private int regionStrength = 1; //total unit value of the occupying units, starts at one because a starts with a soldier occupying

    public Region(String name){
        observers = new ArrayList<>();  //contains list of objects that are observing this Region
        this.name = name;
    }
    public void addNeighbourRegions(ArrayList<Region> neighbours){
        this.neighbours = neighbours;
        System.out.println("Region " + this + " has neighbours " + this.neighbours);
    }
    public ArrayList<Region> getNeighbours(){
        return neighbours;
    }
    @Override
    public void register(Observer o) {  //this method is called when an observer is constructed.
        observers.add(o);
        System.out.println("observer " + o.getObserverID() + " registered to region " + name);
    }

    @Override
    public void unregister(Observer o) {    //in the case where an observer is no longer interested in the state of this region
        int observerIndex = observers.indexOf(o);
        System.out.println("deleting observer " + observerIndex);
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {  //whenever this region's state changes in any way all registered observers will be updated
        for (Observer o : observers){
            o.update(this);
        }
    }
    public void addUnits(ArrayList<UnitType> units){
        regionStrength += unitNormaliser(units);
    }
    public void addUnits(int strength){
        regionStrength += strength;
    }
    public void removeUnits(int strength){
        regionStrength -= strength;
    }
    public void removeUnits(ArrayList<UnitType> units){
        regionStrength -= unitNormaliser(units);
    }
    private int unitNormaliser(ArrayList<UnitType> units){
        int total = 0;
        for(UnitType type : units){
            total += type.getWorth();
        }
        return total;
    }
    public void setOwner(PlayerEnum owner){ //change the state of this region by changing what player owns it
        this.owner = owner;
        notifyObserver();
    }
    @Override
    public String toString(){
        return name;
    }

    public PlayerEnum getOwner(){
        return owner;
    }
    public int getRegionStrength(){
        return regionStrength;
    }
    public ArrayList<UnitType> getUnits(){
        return RegionStrengthCalculator(this.regionStrength);
    }
    private ArrayList<UnitType> RegionStrengthCalculator(int regionStrength){
        ArrayList<UnitType> unitsToReturn = new ArrayList<>();
        int canonsInRegion = (int)Math.floor(regionStrength/10);
        for(int i = 0; i < canonsInRegion; i ++){
            unitsToReturn.add(UnitType.CANON);
        }
        regionStrength -= canonsInRegion*10;
        int horsesInRegion = (int)Math.floor(regionStrength/5);
        for(int i = 0; i < horsesInRegion; i++){
            unitsToReturn.add(UnitType.HORSE);
        }
        regionStrength -= horsesInRegion*5;
        for(int i = 0; i<regionStrength; i++){
            unitsToReturn.add(UnitType.SOLDIER);
        }
        Collections.reverse(unitsToReturn);
        return unitsToReturn;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*This class represents regions on the game board that make up continents
it implements the Subject interface as it may be observed by any observer that is interested in the state of this region
an example of such an observer is a continent.
 */

public class Region implements Subject{
    private List<Observer> observers;
    private Player owner;
    private String name;
    private List<UnitType> occupyingUnits = new ArrayList<>(Arrays.asList(UnitType.SOLDIER));
    public Region(String name){
        observers = new ArrayList<>();  //contains list of objects that are observing this Region
        this.name = name;
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
            o.update(this, owner);
        }
    }
    public void addUnits(ArrayList<UnitType> units){
        occupyingUnits.addAll(units);
    }
    public void removeUnit(ArrayList<UnitType> units){
        occupyingUnits.removeAll(units);
    }
    public void setOwner(Player owner){ //change the state of this region by changing what player owns it
        this.owner = owner;
        System.out.println("observer is " + observers.get(0).getObserverID());
        notifyObserver();
    }
    @Override
    public String toString(){
        return name;
    }
    public Player getOwner(){
        return owner;
    }
}

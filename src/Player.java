import java.util.ArrayList;

public class Player implements Observer {
    private ArrayList<Region> ownedRegions;
    private PlayerEnum whoAmI;
    private static int observerIDTracker = 0;
    public Player(ArrayList<Region> ownedRegions, PlayerEnum player){
        this.ownedRegions = ownedRegions;
        this.whoAmI = player;
        observerIDTracker++;
        System.out.println("New Player created called " + player + " id No. "+ observerIDTracker);
        for(Subject s : ownedRegions){
            s.register(this);
        }
    }
    @Override
    public void update(Region region) {
        if(!(region.getOwner()==whoAmI )){
            region.unregister(this);
            ownedRegions.remove(region);
        }
    }

    @Override
    public int getObserverID() {
        return 0;
    }
}

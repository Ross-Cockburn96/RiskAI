import java.util.ArrayList;

public class User  extends Player{
    private ArrayList<Continent> ownedContinents;
    public User(ArrayList<Region> ownedRegions){
        super(ownedRegions, PlayerEnum.USER);
    }
    public User(){
        super(PlayerEnum.USER);
    }
    @Override
    public void startTurn(GameState state) {
        System.out.println("turn started for User");
        int reinforcements = reinforcementCalc(state);
        System.out.println("You have " + reinforcements + " reinforcements and " + ownedRegions.size() + " Regions");
        System.out.println("You own continents: " + ownedContinents.toString());

    }
    /*1. A player takes new armies equal in number to one third of the regions they own. (Fractions disregarded, minimum of 3 armies
    2. A player also receives extra armies for every continent they control*/
    private int reinforcementCalc(GameState state){
        ownedContinents = new ArrayList<>();
        int reinforcementsAvailable =0;
        int minimumReinforcementsFromRegion = 3;
        for(String s : GameState.continentNames){
            Continent continent = state.getContinent(s);
            if(continent.getOwner() == null){
                continue;
            }
            if (continent.getOwner().equals(super.whoAmI)){ //if the player owns the continent then they get the reinforcements associated with that continent
                reinforcementsAvailable += continent.getReinforcementValue();
                ownedContinents.add(continent);
            }
        }
        int reinforcementsFromRegions = Math.floorDiv(super.ownedRegions.size(),3); //calculates a 3rd of the players regions, disregarding fractions
        if(reinforcementsFromRegions < minimumReinforcementsFromRegion){
            reinforcementsAvailable += minimumReinforcementsFromRegion;
        }else{
            reinforcementsAvailable += reinforcementsFromRegions;
        }
        return reinforcementsAvailable;
    }
}

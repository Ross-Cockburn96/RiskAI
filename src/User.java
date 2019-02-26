import java.util.ArrayList;
import java.util.Scanner;

public class User  extends Player{
    private ArrayList<Continent> ownedContinents;
    private GameState state;
    private GameStateMediator mediator;

    public User(){
        super(PlayerEnum.USER);
    }
    @Override
    public void startTurn(GameState state, GameStateMediator mediator) {
        this.state = state;
        this.mediator = mediator;
        System.out.println("turn started for User");
        reinforcementPhase();
        combatPhase();

    }
    private void reinforcementPhase(){
        boolean finished = false;
        int reinforcements = reinforcementCalc();
        System.out.println("You own continents: " + ownedContinents.toString() + " and " + ownedRegions.size()+ " Regions");

        while(!finished){
            for (Region r : ownedRegions.values()){
                System.out.println("You own region " + r + " with " + r.getUnits() + " units" );
            }
            System.out.println("You have " + reinforcements + " reinforcements");
            System.out.println("name a region to reinforce and the type of unit to reinforce with eg Europe Soldier");
            Scanner scanner = new Scanner(System.in);
            String region = scanner.next();
            String unitType = scanner.next();
            UnitType chosenUnit = UnitType.valueOf(unitType.toUpperCase());
            if (reinforcements - unitConversion(chosenUnit) < 0){
                System.out.println("Not enough reinforcements!");
                continue;
            }else{
                reinforcements = reinforcements - unitConversion(chosenUnit);
            }
            System.out.println("Reinforcing region " + region + " with " + unitType +" units");
            new ReinforceAction(mediator, ownedRegions.get(region), chosenUnit).execute();
            System.out.println("are you finished with your reinforcement phase? yes or no");
            String input = scanner.next();
            if(input.equals("yes")){
                finished = true;
            }
        }
    }
    private void combatPhase(){
        boolean finished = false;
        System.out.println("Combat Phase: to find out a region's neighbours query it ");
        while (!finished){
            for (Region r : ownedRegions.values()){
                System.out.println("You own region " + r + " with " + r.getUnits() + " units" );
            }
            System.out.println("what region would you like to query?");
            Scanner scan = new Scanner(System.in);
            String region = scan.next();
            System.out.println("Region " + region + " has neighbours " + ownedRegions.get(region).getNeighbours());
            System.out.println("Do you want to query another Region? ");
            if (scan.next().equals("yes")){
                continue;
            }
            System.out.println("What region would you like to attack and from where format {origin} {destination}");
            String origin = scan.next();
            String destination = scan.next();
            System.out.println("What Units would you like to attack with. list as {Unit} {Unit}... finish with 'end'");
            ArrayList<UnitType> attackingUnits = new ArrayList<>();
            String input = scan.next();
            while(!input.equals("end")){
                attackingUnits.add(UnitType.valueOf(input.toUpperCase()));
                input = scan.next();
            }
            //new AttackAction(mediator, ownedRegions.get(origin),state.getRegions().get(destination),attackingUnits).execute();
            System.out.println("would you like to do another attack move? (yes/no)");
            String response = scan.next();
            if(response.equals("no")){
                finished = true;
            }
        }
    }
    //converts horses and canons to common denomination of soldier
    private int unitConversion(UnitType unit){
        switch (unit){
            case SOLDIER:
                return 1;
            case HORSE:
                return 5;
            case CANON:
                return 10;
        }

        return 0;
    }
    /*1. A player takes new armies equal in number to one third of the regions they own. (Fractions disregarded, minimum of 3 armies
    2. A player also receives extra armies for every continent they control*/
    private int reinforcementCalc(){
        ownedContinents = new ArrayList<>();
        int reinforcementsAvailable =0;
        int minimumReinforcementsFromRegion = 3;
        for(String s : GameState.continentNames){
            Continent continent = state.getContinent(s);
            if(continent.getOwner() == null){
                continue;
            }
            if (continent.getOwner().equals(whoAmI)){ //if the player owns the continent then they get the reinforcements associated with that continent
                reinforcementsAvailable += continent.getReinforcementValue();
                ownedContinents.add(continent);
            }
        }
        int reinforcementsFromRegions = Math.floorDiv(ownedRegions.size(),3); //calculates a 3rd of the players regions, disregarding fractions
        if(reinforcementsFromRegions < minimumReinforcementsFromRegion){
            reinforcementsAvailable += minimumReinforcementsFromRegion;
        }else{
            reinforcementsAvailable += reinforcementsFromRegions;
        }
        return reinforcementsAvailable;
    }
}

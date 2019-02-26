import java.util.*;

public class GameStateMediator implements Mediator {

    private boolean successful = false;
    @Override
    public void reinforce(Region r, int strengthOfUnits){
    }

    @Override
    public void move(Region origin, Region destination, int strengthOfUnits) {
        //checks if move is valid then changes region strength's appropriately
        if ((origin.getNeighbours().contains(destination)) && (origin.getRegionStrength() - strengthOfUnits >= 1)&&(origin.getOwner().equals(destination.getOwner()))){
            origin.removeUnits(strengthOfUnits);
            destination.addUnits(strengthOfUnits);
        }else{
            System.out.println("Not a valid move action!");
        }
    }
    @Override
    public void attack(Region origin, Region desination, int  strengthOfUnits) {
        if((origin.getNeighbours().contains(desination))&&(origin.getRegionStrength() - strengthOfUnits >=1)&&(!origin.getOwner().equals(desination.getOwner()))){
            successful = true;
            int[] noOfDiceUsed = noOfDiceCalculator(strengthOfUnits, desination.getRegionStrength());
            int[] losses = attackResult(noOfDiceUsed[0],noOfDiceUsed[1]);
            origin.removeUnits(losses[0]);
            desination.removeUnits(losses[1]);
        }else{
            System.out.println("Not a valid attack action!");
        }
        if (desination.getRegionStrength() == 0){
            desination.setOwner(origin.getOwner());
            System.out.println("Player " + origin.getOwner() + " has taken region " + desination);
        }
    }
    //takes the strength of the attacking army and calculates the amount of dice the attacker and the defender gets to throw.
    //returns a 1d int array of 2 elements. The first element is the attacker's dice(1-3) and the second is the defender's (1-2)
    private int[] noOfDiceCalculator(int attackStrength, int defendStrength){
        int noOfOffenceDice;
        int noOfDefenceDice;
        if (attackStrength == 1 || attackStrength == 2){
            noOfOffenceDice = attackStrength;
        }else{
            noOfOffenceDice = 3;
        }
        if (defendStrength == 1){
            noOfDefenceDice = defendStrength;
        }else{
            noOfDefenceDice = 2;
        }
        return new int[]{noOfOffenceDice, noOfDefenceDice};
    }
    private int[] attackResult(int offenceDiceNo, int defenceDiceNo) throws IndexOutOfBoundsException{
        Random diceThrow = new Random();
        int attackerLosses = 0;
        int defenderLosses = 0;
        ArrayList<Integer> attackDiceRolls = new ArrayList<>(offenceDiceNo);
        ArrayList<Integer> defenceDiceRolls = new ArrayList<>(defenceDiceNo);
        for (int i = 0 ; i < offenceDiceNo; i++){
            attackDiceRolls.add(diceThrow.nextInt(7));
            System.out.println("attacker rolled a " + attackDiceRolls.get(i));

        }
        for (int i = 0; i < defenceDiceNo; i++){
            defenceDiceRolls.add(diceThrow.nextInt(7));
            System.out.println("defender rolled a " + defenceDiceRolls.get(i));
        }
        Collections.sort(attackDiceRolls, Collections.reverseOrder());
        Collections.sort(defenceDiceRolls, Collections.reverseOrder());
        int i = 0;
        boolean complete = false;
        while(!complete) {
            try {
                if (defenceDiceRolls.get(i) < attackDiceRolls.get(i)) {
                    defenderLosses++;
                } else {
                    if (defenceDiceRolls.get(i) > attackDiceRolls.get(i)) {
                        attackerLosses++;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                complete = true;
            }
            i++;
        }

        System.out.println("Result is attacker losses: " + attackerLosses + " defender losses " + defenderLosses);
        return new int[]{attackerLosses, defenderLosses};
    }
    public boolean isSuccessful(){
        return successful;
    }
}

import java.util.ArrayList;

public class GameStateMediator implements Mediator {
    @Override
    public void reinforce(Region r, ArrayList<UnitType> units) {
        r.addUnits(units);
    }


    @Override
    public void move(Region origin, Region destination, ArrayList<UnitType> units) {
        origin.removeUnits(units);
        destination.addUnits(units);
    }

    @Override
    public void attack(Region origin, Region desination, ArrayList<UnitType> units) {
        //call attack calculation methods
    }
}

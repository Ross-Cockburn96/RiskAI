import java.util.ArrayList;

public class GameStateMediator implements Mediator {
    @Override
    public void Reinforce(Region r, ArrayList<UnitType> units) {
        r.addUnits(units);
    }

    @Override
    public void Move(Region origin, Region destination) {

    }

    @Override
    public void attack(Region origin, Region desination, ArrayList<UnitType> units) {

    }
}
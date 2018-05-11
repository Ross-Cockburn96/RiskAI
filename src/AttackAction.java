import java.util.ArrayList;

public class AttackAction implements PlayerAction {

    private GameStateMediator m;
    private Region origin;
    private Region destination;
    private ArrayList<UnitType> units;

    public AttackAction(GameStateMediator m, Region origin, Region destination, ArrayList<UnitType> units){
        this.m = m;
        this.origin = origin;
        this.destination = destination;
        this.units = units;
    }

    @Override
    public void execute() {
        m.attack(origin,destination,units);
    }
}

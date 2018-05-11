import java.util.ArrayList;
import java.util.List;

public class MoveAction implements PlayerAction {

    private GameStateMediator m;
    private Region origin;
    private Region destination;
    private ArrayList<UnitType> units;

    public MoveAction(GameStateMediator m, Region origin, Region destination, ArrayList<UnitType> units){
        this.m =m;
        this.origin = origin;
        this.destination = destination;
        this.units = units;
    }
    @Override
    public void execute() {
        m.move(origin, destination, units);
    }
}

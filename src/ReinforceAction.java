import java.util.ArrayList;
import java.util.Arrays;

public class ReinforceAction implements PlayerAction {
    private GameStateMediator m;
    private Region r;
    private ArrayList<UnitType> units;
    public ReinforceAction(GameStateMediator m, Region r, ArrayList<UnitType> units){
        this.m = m;
        this.units = units;
        this.r = r;
    }
    public ReinforceAction(GameStateMediator m, Region r, UnitType unit){ //for the case where user is reinforcing a region with a single unit type
        this.m = m;
        this.units = new ArrayList<>(Arrays.asList(unit));
        this.r = r;
    }
    @Override
    public void execute() {
        m.reinforce(r, units);
    }

}

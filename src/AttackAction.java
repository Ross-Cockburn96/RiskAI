import java.util.ArrayList;

public class AttackAction implements PlayerAction {

    private GameStateMediator m;
    private Region origin;
    private Region destination;
    private int unitStrength;
    private boolean valid = false;

    public AttackAction(GameStateMediator m, Region origin, Region destination, int strengthOfUnits){
        this.m = m;
        this.origin = origin;
        this.destination = destination;
        this.unitStrength = strengthOfUnits;

    }

    @Override
    public void execute() {
       m.attack(origin,destination,unitStrength);
    }
}

import java.util.ArrayList;

public interface Mediator {
    void reinforce(Region r, ArrayList<UnitType> unit);
    void move(Region origin, Region destination, ArrayList<UnitType> units);
    void attack(Region origin, Region desination, ArrayList<UnitType> units);
}

import java.util.ArrayList;

public interface Mediator {
    void Reinforce(Region r, ArrayList<UnitType> unit);
    void Move(Region origin, Region destination);
    void attack(Region origin, Region desination, ArrayList<UnitType> units);
}

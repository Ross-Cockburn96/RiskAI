import java.util.ArrayList;

public interface Mediator {
    void reinforce(Region r, int strength);
    void move(Region origin, Region destination, int strength);
    void attack(Region origin, Region destination, int  strength);
}

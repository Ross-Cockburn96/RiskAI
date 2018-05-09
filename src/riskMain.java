import java.io.IOException;

enum UnitType {CANON, HORSE, SOLDIER, WILDCARD}
enum Player {USER, AI, NEUTRAL, DONKEY}
public class riskMain {

    public static void main(String[] args) {
        try {
            GameState.createDeck();
        }catch (IOException e){
            e.printStackTrace();
        }
        GameState state = new GameState();
        state.initialiseState();
    }

}

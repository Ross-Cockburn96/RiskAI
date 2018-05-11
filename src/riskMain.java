import java.io.IOException;
import java.util.ArrayList;

enum UnitType {CANON, HORSE, SOLDIER, WILDCARD}
enum PlayerEnum {USER, AI, NEUTRAL}
public class riskMain {
    private static Boolean gameOver = false;
    public static void main(String[] args) {
        try {
            GameState.createDeck();
        }catch (IOException e){
            e.printStackTrace();
        }
        GameState state = new GameState();
        state.initialiseState();
        GameStateMediator mediator = new GameStateMediator();
        startGame(state);
    }
    private static void startGame(GameState state ){
        while (!gameOver){

        }
    }
}

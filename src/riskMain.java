import java.io.IOException;

enum UnitType {CANON, HORSE, SOLDIER, WILDCARD}
enum PlayerEnum {USER, AI, NEUTRAL}
public class riskMain {
    private static boolean gameFinished = false;
    public static void main(String[] args) {
        try {
            GameState.createDeck();
        }catch (IOException e){
            e.printStackTrace();
        }
        GameState state = new GameState();
        state.initialiseState();
        while (!gameFinished){
            for (PlayerEnum p : PlayerEnum.values()){
                state.getPlayer(p).startTurn(state);
            }
            gameFinished = true;
        }
    }

}

import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.io.IOException;

enum UnitType {CANON(10), HORSE(5), SOLDIER(1), WILDCARD(0);
    private final int worth;
    UnitType(int worth){
        this.worth = worth;
    }
    public int getWorth(){
        return this.worth;
    }
}


enum PlayerEnum {USER, AI, NEUTRAL}
public class riskMain {
    private static boolean gameFinished = false;
    public static void main(String[] args) {
        GameState state = new GameState();
        GameStateMediator mediator = new GameStateMediator();
        state.initialiseState();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI.Menu(1600 ,1062);
            }
        });
        while (!gameFinished){
            for (PlayerEnum p : PlayerEnum.values()){
                state.getPlayer(p).startTurn(state, mediator);
            }
            gameFinished = true;
        }
    }

}

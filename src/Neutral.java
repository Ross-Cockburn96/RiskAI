public class Neutral extends Player {
    public Neutral(){
        super(PlayerEnum.NEUTRAL);
    }
    @Override
    public void startTurn(GameState state, GameStateMediator mediator) {
        System.out.println("turn started for Neutral");
    }
}

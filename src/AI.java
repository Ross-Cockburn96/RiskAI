public class AI extends Player {
    public AI(){
        super(PlayerEnum.AI);
    }
    @Override
    public void startTurn(GameState state, GameStateMediator mediator) {
        System.out.println("turn started for AI");
    }
}

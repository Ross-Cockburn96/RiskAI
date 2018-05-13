public class AI extends Player {
    public AI(){
        super(PlayerEnum.AI);
    }
    @Override
    public void startTurn(GameState state) {
        System.out.println("turn started for AI");
    }
}

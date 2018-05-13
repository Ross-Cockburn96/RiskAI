public class Neutral extends Player {
    public Neutral(){
        super(PlayerEnum.NEUTRAL);
    }
    @Override
    public void startTurn(GameState state) {
        System.out.println("turn started for Neutral");
    }
}

package CLI;

public class GameState {

    private Player player;


    public GameState(Player player){
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

package CLI;

import model.GamePlayer;
import model.Hero;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameState {


    private GamePlayer freind;
    private GamePlayer enemy;
//    private ArrayList<card>friendDeck;
//    private ArrayList<card> enemyDeck;
//    private ArrayList<card> enemyHand;
//    private ArrayList<card> friendHand;
//    private ArrayList<Minion> friendGround;
//    private ArrayList<Minion> enemyGround;
//    private Hero friendHero;
//    private Hero enemyHero;


    private Player player;
    private boolean startGame=false;
    private Timer timer;
   private boolean turn = false;

    public GameState() {
//        friendDeck=new ArrayList<>();
//        enemyDeck=new ArrayList<>();
//        enemyHand=new ArrayList<>();
//        friendHand=new ArrayList<>();
//        friendGround=new ArrayList<>();
//        enemyGround=new ArrayList<>();

        if (startGame) {
            timer = new Timer();
            updateTime();
        }
    }

    public GamePlayer getFreind() {
        return freind;
    }

    public void setFreind(GamePlayer freind) {
        this.freind = freind;
    }

    public GamePlayer getEnemy() {
        return enemy;
    }

    public void setEnemy(GamePlayer enemy) {
        this.enemy = enemy;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void updateTime() {
        // mogeh shorou nobate
        timer.scheduleAtFixedRate(new Ticker(), 0, 1000);
    }



    public GameState(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private class Ticker extends TimerTask {
        int currentTime = 60;

        @Override
        public void run() {
            if (currentTime == 0) {
                turn = !turn;
                updateTime();
                this.cancel();
            }
            currentTime--;
        }
    }
}

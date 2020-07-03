package CLI;

import model.Hero;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameState {

    private ArrayList<card>friendDeck;
    private ArrayList<card> enemyDeck;
    private ArrayList<card> enemyHand;
    private ArrayList<card> friendHand;
    private ArrayList<Minion> friendGround;
    private ArrayList<Minion> enemyGround;
    private Hero friendHero;
    private Hero enemyHero;


    private Player player;
    private boolean startGame=false;
    private Timer timer;
   private boolean turn = false;

    public GameState() {
        friendDeck=new ArrayList<>();
        enemyDeck=new ArrayList<>();
        enemyHand=new ArrayList<>();
        friendHand=new ArrayList<>();
        friendGround=new ArrayList<>();
        enemyGround=new ArrayList<>();
        if (startGame) {
            timer = new Timer();
            updateTime();
        }
    }

    public void updateTime() {
        // mogeh shorou nobate
        timer.scheduleAtFixedRate(new Ticker(), 0, 1000);
    }

    public ArrayList<card> getFriendDeck() {
        return friendDeck;
    }

    public void setFriendDeck(ArrayList<card> friendDeck) {
        this.friendDeck = friendDeck;
    }

    public ArrayList<card> getEnemyDeck() {
        return enemyDeck;
    }

    public void setEnemyDeck(ArrayList<card> enemyDeck) {
        this.enemyDeck = enemyDeck;
    }

    public ArrayList<card> getEnemyHand() {
        return enemyHand;
    }

    public void setEnemyHand(ArrayList<card> enemyHand) {
        this.enemyHand = enemyHand;
    }

    public ArrayList<card> getFriendHand() {
        return friendHand;
    }

    public void setFriendHand(ArrayList<card> friendHand) {
        this.friendHand = friendHand;
    }

    public ArrayList<Minion> getFriendGround() {
        return friendGround;
    }

    public void setFriendGround(ArrayList<Minion> friendGround) {
        this.friendGround = friendGround;
    }

    public ArrayList<Minion> getEnemyGround() {
        return enemyGround;
    }

    public void setEnemyGround(ArrayList<Minion> enemyGround) {
        this.enemyGround = enemyGround;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
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

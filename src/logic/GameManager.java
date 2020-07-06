package logic;

import CLI.GameState;
import model.GamePlayer;
import model.Minion;
import model.card;

public class GameManager {

    private GameState gameState;

    public GameManager(GameState gameState) {
        this.gameState = gameState;
    }


    public void nextRound() {
        gameState.setTurn(!gameState.isTurn());
        setManaInRound();
        drawCardPerRound();

    }

    public void drawCardFromDeck(GamePlayer gamePlayer) {
        card card = gamePlayer.getDeck().remove(0);
        gamePlayer.getHand().add(card);
    }

    public void drawCardFromHand(GamePlayer gamePlayer) {


    }


    private void setManaInRound() {
        if (gameState.getFreind().getMaxManaPerRound() < 10) {
            gameState.getFreind().setMaxManaPerRound(gameState.getFreind().getMaxManaPerRound() + 1);
        }else {
            gameState.getFreind().setMaxManaPerRound(10);
        }
        if (gameState.getEnemy().getMaxManaPerRound() < 10) {
            gameState.getEnemy().setMaxManaPerRound(gameState.getEnemy().getMaxManaPerRound() + 1);
        }
        else {
            gameState.getEnemy().setMaxManaPerRound(10);
        }
        gameState.getFreind().setMana(gameState.getFreind().getMaxManaPerRound());
        gameState.getEnemy().setMana(gameState.getEnemy().getMaxManaPerRound());
    }

    private void drawCardPerRound() {
        if (!gameState.isTurn()) {// nobat friend basheh
            drawCardFromDeck(gameState.getFreind());

        } else if (gameState.isTurn()) {
            drawCardFromDeck(gameState.getEnemy());
        }
    }

    //getter&setter
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}

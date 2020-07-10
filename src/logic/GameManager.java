package logic;

import CLI.GameState;
import CLI.utilities;
import Interfaces.Attackable;
import model.*;
import swing.Controller;

import javax.swing.*;
import java.util.List;

public class GameManager {

    private GameState gameState;
    private Constans constans= Controller.getInstance().getConstants();

    public GameManager(GameState gameState) {
        this.gameState = gameState;
    }


    public void nextRound() {
        gameState.setTurn(!gameState.isTurn());
        setManaInRound();
        drawCardPerRound();

    }

    public void drawCardFromDeck(GamePlayer gamePlayer) {
        if (gamePlayer.getHand().size() < constans.getHandSize()) {
            if (gamePlayer.getDeck().size() > 0) {
                card card = gamePlayer.getDeck().remove(0);
                gamePlayer.getHand().add(card);
                doLog("draw card success",gamePlayer);
            }
            else {
               doLog("deck is low",gamePlayer);
            }
        }
        else {
           doLog(" hand is full",gamePlayer);
        }
    }

    public void drawCardFromHand(GamePlayer gamePlayer,String cardName) {
        if (gamePlayer.getGround().size()< constans.getGroundSize()){
            card card =getCard(cardName,gamePlayer.getHand());
            if (card.getManaCost()<=gamePlayer.getMana()+gamePlayer.getOffCard()){
                gamePlayer.setMana(gamePlayer.getMana()-card.getManaCost());
                //
                doLog("draw "+card.getName()+" to ground",gamePlayer);
            }
            else {
                doLog("no mana for card: "+card,gamePlayer);
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame().getMainpanel(), " you dont have enough mana ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            doLog(" ground is full",gamePlayer);
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame().getMainpanel(), "you can not buy this card ", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void playWeapen(weapen weapen,GamePlayer freind,GamePlayer enemy){
        weapen.accept(new BattlecryVisitor(),freind,enemy,null);
        System.out.println(" now its our weapen"+weapen.toString());
        freind.setMyWeapen(weapen);
        doLog("your weapen is "+weapen.getName(),freind);
    }
    public void attackWithWeapen(GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (freind.getMyWeapen() != null) {
            if (target instanceof Minion) {
                if (((Minion) target).isTaunt()) {
                    freind.getMyWeapen().accept(new ActionVisitor(), freind, enemy, target);

                } else if (findTaunt(enemy.getGround())) {
JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"you cannot attack because we have taunt","playError",JOptionPane.ERROR_MESSAGE);
                }
            }
            if (target instanceof Hero) {

            }
        }
        else {
            // weapen is null;
            doLog("dont have weapen",freind);
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame()," you dont have weapen","ErrorWeapen",JOptionPane.ERROR_MESSAGE);
        }
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

    public void doLog(String log,GamePlayer gamePlayer){
        gameState.getNote().add(gamePlayer.getNameOfPlayer()+log);
        String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
        Controller.myLogger(st1,gamePlayer.getNameOfPlayer()+" "+log+" "+ utilities.time()+"\n",true);
    }


    //search card from its name
    private card getCard(String name, List<card> hand){
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getName().equalsIgnoreCase(name)){
                return hand.get(i);
            }
        }
        return null;
    }
    private boolean findTaunt(List<? extends card> ground){
        for (int i = 0; i < ground.size(); i++) {
            if (ground.get(i).isTaunt()){
                return true;
            }
        }
        return false;
    }
}

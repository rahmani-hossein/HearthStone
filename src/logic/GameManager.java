package logic;

import CLI.GameState;
import CLI.utilities;
import Interfaces.Attackable;
import model.*;
import swing.Controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameManager {

    private GameState gameState;
    private Constans constans = Controller.getInstance().getConstants();

    public GameManager(GameState gameState) {
        this.gameState = gameState;
    }


    public void nextRound() {
        gameState.setTurn(!gameState.isTurn());
        setManaInRound();
        drawCardPerRound();

    }

    private void checkGameState(){
        if (gameState.getFreind().getHero().getHP()<=0){
            gameState.setGameOver(true);
            System.out.println("you lose :///");
        }
        if (gameState.getEnemy().getHero().getHP()<=0){
            gameState.setGameOver(true);
            System.out.println("you win");
        }
        if (gameState.getFreind().getMyWeapen().getDurability()<=0){
            gameState.getFreind().setMyWeapen(null);
        }
        if (gameState.getEnemy().getMyWeapen().getDurability()<=0){
            gameState.getEnemy().setMyWeapen(null);
        }
        // badan beporsam
        for (int i = 0; i < gameState.getFreind().getGround().size(); i++) {
            if (gameState.getFreind().getGround().get(i).getHealth()<=0){
                gameState.getFreind().getGround().get(i).accept(new DeathRattleVisitor(),gameState.getFreind(),gameState.getEnemy(),null);
                gameState.getFreind().getGround().remove(i);
            }
        }
        for (int i = 0; i < gameState.getEnemy().getGround().size(); i++) {
            if (gameState.getEnemy().getGround().get(i).getHealth()<=0){
                gameState.getEnemy().getGround().get(i).accept(new DeathRattleVisitor(),gameState.getEnemy(),gameState.getFreind(),null);
                gameState.getEnemy().getGround().remove(i);
            }
        }
    }

    private void drawCardPerRound() {
        if (!gameState.isTurn()) {// nobat friend basheh
            drawCardFromDeck(gameState.getFreind());

        } else {
            drawCardFromDeck(gameState.getEnemy());
        }
    }

    public void drawCardFromDeck(GamePlayer gamePlayer) {
        if (gamePlayer.getHand().size() < constans.getHandSize()) {
            if (gamePlayer.getDeck().size() > 0) {
                card card = gamePlayer.getDeck().remove(0);
                if (card.getHeroClass().equalsIgnoreCase("neutral") || card.getHeroClass().equalsIgnoreCase(gamePlayer.getHero().getName())) {
                    gamePlayer.getHand().add(card);
                    doLog("draw card success", gamePlayer);
                } else {
                    doLog("this card is not permitted to use", gamePlayer);
                }
            } else {
                doLog("deck is low", gamePlayer);
            }
        } else {
            doLog(" hand is full", gamePlayer);
        }
    }

    public void drawCardFromHand(GamePlayer haveTurn,GamePlayer noTurn, String cardName,Attackable target) {
        if (haveTurn.getGround().size() < constans.getGroundSize()) {
            card card = getCard(cardName, haveTurn.getHand());
            if (card.getManaCost() <= haveTurn.getMana() + haveTurn.getOffCard()) {
                haveTurn.setMana(haveTurn.getMana() - card.getManaCost());
                //
                if (card instanceof weapen){
                    drawWeapen((weapen) card,haveTurn,noTurn);
                }
                if (card instanceof Minion){
                    drawMinion((Minion) card,haveTurn,noTurn,target);
                }
                if (card instanceof spell){
                    playSpell((spell) card,haveTurn,noTurn,target);
                }
                // for curioCollector;
                LinkedList <Minion>linkedList =null;
                linkedList= (LinkedList) getCards(haveTurn.getGround(),"curioCollector");
                if (linkedList.size()>=1){
                    for (int i = 0; i < linkedList.size(); i++) {
                        linkedList.get(i).accept(new BizareVisitor(),haveTurn,noTurn,null);
                    }
                }
                doLog("draw " + card.getName() + " to ground", haveTurn);
            } else {
                doLog("no mana for card: " + card, haveTurn);
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame().getMainpanel(), " you dont have enough mana ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            doLog(" ground is full", haveTurn);
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame().getMainpanel(), "you can not buy this card ", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void drawWeapen(weapen weapen, GamePlayer freind, GamePlayer enemy) {
        weapen.accept(new BattlecryVisitor(), freind, enemy, null);
        System.out.println(" now its our weapen" + weapen.toString());
        freind.setMyWeapen(weapen);
        doLog("your weapen is " + weapen.getName(), freind);
    }

    public void attackWithWeapen(GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (freind.getMyWeapen() != null&&freind.getMyWeapen().getDurability()>=0) {
            if (target instanceof Minion) {
                if (((Minion) target).isTaunt()) {
                    freind.getMyWeapen().accept(new ActionVisitor(), freind, enemy, target);

                } else if (findTaunt(enemy.getGround())) {
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you cannot attack because we have taunt", "playError", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    freind.getMyWeapen().accept(new ActionVisitor(),freind,enemy,target);
                }
            }
            if (target instanceof Hero) {
                if (findTaunt(enemy.getGround())) {
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you cannot attack because we have taunt", "playError", JOptionPane.ERROR_MESSAGE);
                } else {
                    freind.getMyWeapen().accept(new ActionVisitor(), freind, enemy, target);
                }
            }
        } else {
            // weapen is null;
            doLog("dont have weapen", freind);
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), " you dont have weapen", "ErrorWeapen", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void attackWithMinion(Minion minion, GamePlayer freind, GamePlayer enemy, Attackable target){
        if (target instanceof Minion){
            if (((Minion) target).isTaunt()) {
                minion.accept(new ActionVisitor(), freind, enemy, target);

            } else if (findTaunt(enemy.getGround())) {
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you cannot attack because we have taunt", "playError", JOptionPane.ERROR_MESSAGE);
            }
            else {
                minion.accept(new ActionVisitor(),freind,enemy,target);
            }
        }
        if (target instanceof Hero){
            if (findTaunt(enemy.getGround())){
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you cannot attack because we have taunt", "playError", JOptionPane.ERROR_MESSAGE);
            }
            else {
                minion.accept(new ActionVisitor(),freind,enemy,target);
            }
        }
    }

    public void drawMinion(Minion minion, GamePlayer freind, GamePlayer enemy, Attackable target) {
        freind.getHand().remove(minion);
        freind.getGround().add(minion);
        minion.accept(new BattlecryVisitor(), freind, enemy, target);
    }

    public void playSpell(spell spell, GamePlayer freind, GamePlayer enemy, Attackable target) {
        switch (spell.getName()) {
            case "strengthInNumbers":
                // to do
                break;
            case "learnDraconic":
                //to do
                break;
            case "friendlySmith":
                // to do
                break;
            default:
                spell.accept(new BattlecryVisitor(), freind, enemy, target);
        }

    }


    private void setManaInRound() {
        if (gameState.getFreind().getMaxManaPerRound() < 10) {
            gameState.getFreind().setMaxManaPerRound(gameState.getFreind().getMaxManaPerRound() + 1);
        } else {
            gameState.getFreind().setMaxManaPerRound(10);
        }
        if (gameState.getEnemy().getMaxManaPerRound() < 10) {
            gameState.getEnemy().setMaxManaPerRound(gameState.getEnemy().getMaxManaPerRound() + 1);
        } else {
            gameState.getEnemy().setMaxManaPerRound(10);
        }
        gameState.getFreind().setMana(gameState.getFreind().getMaxManaPerRound());
        gameState.getEnemy().setMana(gameState.getEnemy().getMaxManaPerRound());
    }



    //getter&setter
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void doLog(String log, GamePlayer gamePlayer) {
        gameState.getNote().add(gamePlayer.getNameOfPlayer() + log);
        String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() + Controller.getInstance().getGameState().getPlayer().getPassword());
        Controller.myLogger(st1, gamePlayer.getNameOfPlayer() + " " + log + " " + utilities.time() + "\n", true);
    }


    //search card from its name
    private card getCard(String name, List<? extends card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getName().equalsIgnoreCase(name)) {
                return hand.get(i);
            }
        }
        return null;
    }
    private LinkedList<Minion> getCards(List<Minion> ground,String name){
        LinkedList<Minion> cards =new LinkedList<>();
        for (int i = 0; i < ground.size(); i++) {
            if (ground.get(i).getName().equalsIgnoreCase(name)){
                cards.add(ground.get(i));
            }
        }
        return cards;
    }

    private boolean findTaunt(List<? extends card> ground) {
        for (int i = 0; i < ground.size(); i++) {
            if (ground.get(i).isTaunt()) {
                return true;
            }
        }
        return false;
    }
}

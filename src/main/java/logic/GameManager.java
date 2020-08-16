package logic;

import model.GameState;
import CLI.utilities;
import Interfaces.Attackable;
import client.Controller;
import model.*;
import server.ClientHandler;
import server.ConstantsLoader;
import server.ServerConstants;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GameManager {

    private CardManager cardManager = new CardManager();
    private GameState gameState;
    private ServerConstants constans = ConstantsLoader.getInstance().getServerConstants();
    private String txtAddress;
    private FileWriter fileWriter;
    private int numberofRoundClick=0;

    public GameManager(GameState gameState) {
        this.gameState = gameState;
    }


    public void nextRound() {

        setManaInRound();
        drawCardPerRound();
        gameState.setTurn(!gameState.isTurn());
        increseRound();

    }

    private void checkGameState() {
        if (gameState.getFreind().getHero().getHP() <= 0) {
            gameState.setGameOver(true);
            System.out.println("you lose :///");
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you lose:////", "gameover", JOptionPane.INFORMATION_MESSAGE);
        }
        if (gameState.getEnemy().getHero().getHP() <= 0) {
            gameState.setGameOver(true);
            System.out.println("you win");
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you win:))))", "gameover", JOptionPane.INFORMATION_MESSAGE);

        }
        if (gameState.getFreind().getMyWeapen() != null && gameState.getFreind().getMyWeapen().getDurability() <= 0) {
            gameState.getFreind().setMyWeapen(null);
        }
        if (gameState.getEnemy().getMyWeapen() != null && gameState.getEnemy().getMyWeapen().getDurability() <= 0) {
            gameState.getEnemy().setMyWeapen(null);
        }
        // badan beporsam
        for (int i = 0; i < gameState.getFreind().getGround().size(); i++) {
            if (gameState.getFreind().getGround().get(i).getHealth() <= 0) {
                System.out.println(i + " " + gameState.getFreind().getGround().get(i).getName());
                gameState.getFreind().getGround().get(i).accept(new DeathRattleVisitor(), gameState.getFreind(), gameState.getEnemy(), null);
                gameState.getFreind().getGround().remove(i);
            }
        }
        for (int i = 0; i < gameState.getEnemy().getGround().size(); i++) {
            if (gameState.getEnemy().getGround().get(i).getHealth() <= 0) {
                gameState.getEnemy().getGround().get(i).accept(new DeathRattleVisitor(), gameState.getEnemy(), gameState.getFreind(), null);
                gameState.getEnemy().getGround().remove(i);
            }
        }
    }

    private void drawCardPerRound() {
        // nobat friend basheh
        if (!gameState.isTurn()) {
            for (int i = 0; i < gameState.getEnemy().getCardPerRound(); i++) {
                drawCardFromDeck(gameState.getEnemy());
            }

            for (int i = 0; i < gameState.getFreind().getCardPerRound(); i++) {
                drawCardFromDeck(gameState.getFreind());
            }
        }
    }

    public void drawCardFromDeck(GamePlayer gamePlayer) {
        if (gamePlayer.getHand().size() < constans.getHandSize()) {
            if (gamePlayer.getDeck().size() > 0) {
                card card = gamePlayer.getDeck().remove(0);
                if (card.getHeroClass().equalsIgnoreCase("neutral") || card.getHeroClass().equalsIgnoreCase(gamePlayer.getHero().getName())) {
                    if (card instanceof Minion) {
                        gamePlayer.getHand().add(cardManager.createM(card.getName()));
                    }
                    if (card instanceof spell) {
                        gamePlayer.getHand().add(cardManager.createS(card.getName()));
                    }
                    if (card instanceof weapen) {
                        gamePlayer.getHand().add(cardManager.createW(card.getName()));
                    }

                    doLog("draw card success", gamePlayer);
                } else {
                    doLog("this card is not permitted to use", gamePlayer);
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), card.getName() + " is not permitted", "permittedError", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                doLog("deck is low", gamePlayer);
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "deck doesnt have any card", "deckError", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            doLog(" hand is full", gamePlayer);
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "hand is full", "handError", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void drawCardFromHand(GamePlayer haveTurn, GamePlayer noTurn, card card, Attackable target) {
        if (haveTurn.getGround().size() < constans.getGroundSize()) {

            if (card.getManaCost() + haveTurn.getOffCard() <= haveTurn.getMana()) {
                haveTurn.setMana(haveTurn.getMana() - card.getManaCost() - haveTurn.getOffCard());
                //
                if (card instanceof weapen) {
                    drawWeapen((weapen) card, haveTurn, noTurn);
                }
                if (card instanceof Minion) {
                    drawMinion((Minion) card, haveTurn, noTurn, target);
                }
                if (card instanceof spell) {
                    playSpell((spell) card, haveTurn, noTurn, target);
                }
                // for curioCollector;
                LinkedList<Minion> linkedList = null;
                linkedList = (LinkedList) getCards(haveTurn.getGround(), "curioCollector");
                if (linkedList != null && linkedList.size() >= 1) {
                    for (int i = 0; i < linkedList.size(); i++) {
                        linkedList.get(i).accept(new BizareVisitor(), haveTurn, noTurn, null);
                        checkGameState();
                    }
                }
                doLog("draw " + card.getName() + " to ground", haveTurn);
            } else {
                doLog("no mana for card: " + card, haveTurn);
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), " you dont have enough mana ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            doLog(" ground is full", haveTurn);
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not buy this card ", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }


    public void drawWeapen(weapen weapen, GamePlayer freind, GamePlayer enemy) {
        weapen.accept(new BattlecryVisitor(), freind, enemy, null);
        checkGameState();
        System.out.println(" now its our weapen" + weapen.toString());
        freind.setMyWeapen(weapen);
        freind.getHand().remove(weapen);
        doLog("your weapen is " + weapen.getName(), freind);
    }

    public void attackWithWeapen(GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (freind.getMyWeapen() != null && freind.getMyWeapen().getDurability() >= 0) {
            if (target instanceof Minion) {
                if (((Minion) target).isTaunt()) {
                    freind.getMyWeapen().accept(new ActionVisitor(), freind, enemy, target);
                    checkGameState();
                    doLog(freind.getMyWeapen().getName() + " played", freind);

                } else if (findTaunt(enemy.getGround())) {
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you cannot attack because we have taunt", "playError", JOptionPane.ERROR_MESSAGE);
                } else {
                    freind.getMyWeapen().accept(new ActionVisitor(), freind, enemy, target);
                    checkGameState();
                }
            }
            if (target instanceof Hero) {
                if (findTaunt(enemy.getGround())) {
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you cannot attack because we have taunt", "playError", JOptionPane.ERROR_MESSAGE);
                } else {
                    freind.getMyWeapen().accept(new ActionVisitor(), freind, enemy, target);
                    checkGameState();
                }
            }
        } else {
            // weapen is null;
            doLog("dont have weapen", freind);
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), " you dont have weapen", "ErrorWeapen", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void attackWithMinion(Minion minion, GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (target instanceof Minion) {
            if (((Minion) target).isTaunt()) {
                minion.accept(new ActionVisitor(), freind, enemy, target);
                checkGameState();
                doLog(minion.getName() + " attacked", freind);

            } else if (findTaunt(enemy.getGround())) {
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you cannot attack because we have taunt", "playError", JOptionPane.ERROR_MESSAGE);
            } else {
                minion.accept(new ActionVisitor(), freind, enemy, target);
                checkGameState();
                doLog(minion.getName() + " attacked", freind);
            }
        }
        if (target instanceof Hero) {
            if (findTaunt(enemy.getGround())) {
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you cannot attack because we have taunt", "playError", JOptionPane.ERROR_MESSAGE);
            } else {
                minion.accept(new ActionVisitor(), freind, enemy, target);
                doLog(minion.getName() + " attack " + ((Hero) target).getName(), freind);
                checkGameState();
            }
        }
        minion.setAttackInRound(1);
    }

    public void drawMinion(Minion minion, GamePlayer freind, GamePlayer enemy, Attackable target) {
        freind.getHand().remove(minion);
        freind.getGround().add(minion);
        for (int i = 0; i < freind.getGround().size(); i++) {
            System.out.println(freind.getGround().get(i).getName());
        }
        minion.accept(new BattlecryVisitor(), freind, enemy, target);
        checkGameState();
        doLog(minion.getName() + " drew", freind);
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
                freind.getHand().remove(spell);
                checkGameState();
                doLog(spell.getName() + " played", freind);
        }

    }


    private void setManaInRound() {
            setMaxMana(gameState.getFreind());
            setMaxMana(gameState.getEnemy());
            gameState.getFreind().setMana(gameState.getFreind().getMaxManaPerRound());
            gameState.getEnemy().setMana(gameState.getEnemy().getMaxManaPerRound());
    }

    // for every round
    private void setMaxMana(GamePlayer gamePlayer) {
        if (gamePlayer.getMaxManaPerRound() < 10) {
            gamePlayer.setMaxManaPerRound(gamePlayer.getMaxManaPerRound() + 1);
        } else {
            gamePlayer.setMaxManaPerRound(10);
        }
    }

    private void increseRound() {
        if (gameState.isTurn()) {
            for (int i = 0; i < gameState.getFreind().getGround().size(); i++) {
                gameState.getFreind().getGround().get(i).increaseRound();
                gameState.getFreind().getGround().get(i).setAttackInRound(0);
            }
        } else {
            for (int i = 0; i < gameState.getEnemy().getGround().size(); i++) {
                gameState.getEnemy().getGround().get(i).increaseRound();
                gameState.getEnemy().getGround().get(i).setAttackInRound(0);
            }
        }
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
        myLogger(txtAddress, gamePlayer.getNameOfPlayer() + " " + log + " " + utilities.time() + "\n", true);
    }


    //search card from its name

    private LinkedList<Minion> getCards(List<Minion> ground, String name) {
        LinkedList<Minion> cards = new LinkedList<>();
        for (int i = 0; i < ground.size(); i++) {
            if (ground.get(i).getName().equalsIgnoreCase(name)) {
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

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }

    public void myLogger(String fileName, String write, boolean append) {

        try {
            fileWriter = new FileWriter(fileName, append);
            fileWriter.write(write);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package CLI;

import logic.DeckManager;
import logic.DeckReader;
import logic.HeroCreator;
import model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameMaker {
    // for network faze and also some actions that we dont like to handle in menu.
    private Player player1;
    private Deck enemyDeck;
    private DeckReader deckReader;
    private String deckReaderAddress1;
    private String deck1;
    private GamePlayer freind;
    private GamePlayer enemy;
    private GameState gameState1;
    private String passive1;
    private Player player2;
    private String deck2;
    private String deckReaderAddress2;
    private String passive2;
    private GameState gameState2;




    public DeckReader getDeckReader() {
        return deckReader;
    }

    public void setDeckReader(DeckReader deckReader) {
        this.deckReader = deckReader;
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

    public Player getPlayer() {
        return player1;
    }

    public void setPlayer(Player player) {
        this.player1 = player;
    }

    public String getDeckReaderAddress() {
        return deckReaderAddress1;
    }

    public void setDeckReaderAddress(String deckReaderAddress) {
        this.deckReaderAddress1 = deckReaderAddress;
    }

    public String getDeck() {
        return deck1;
    }

    public void setDeck(String deck) {
        this.deck1 = deck;
    }

    public GameMaker(Player player, String deckReaderAddress, String deck, GameState gameState, String passive) {
        this.player1 = player;
        this.deckReaderAddress1 = deckReaderAddress;
        this.deck1 = deck;
        this.gameState1 = gameState;
        this.passive1 = passive;

    }

    public GameMaker(Player player1,String deckReaderAddress1, String deck1, GameState gameState1, String passive1,Player player2,String deckReaderAddress2, String deck2, GameState gameState2, String passive2 ){
        this.player1 = player1;
        this.deckReaderAddress1 = deckReaderAddress1;
        this.deck1 = deck1;
        this.gameState1 = gameState1;
        this.passive1 = passive1;
        this.player2 = player2;
        this.deckReaderAddress2 = deckReaderAddress2;
        this.deck2 = deck2;
        this.gameState2 = gameState2;
        this.passive2 = passive2;
    }

    public void buildGameState1() {
        if (deckReaderAddress1 != null && deckReaderAddress1.length() >= 2) {
            deckReader = new DeckReader(deckReaderAddress1);
            makeFromDeckReader(gameState1);
        } else {
            make(gameState1);
        }
    }

    public void buildOnlineGameState(){
        if (deckReaderAddress1 != null && deckReaderAddress1.length() >= 2) {
//            deckReader = new DeckReader(deckReaderAddress1);
//            makeFromDeckReader(gameState1);
        } else {
           makeOnlineGameStates(deck1,player1,deck2,player2);
        }
    }
    public void makeOnlineGameStates(String deck1, Player player1,String deck2, Player player2){
        GamePlayer unhandled=online(deck1,player1);
        buildPassive(unhandled,passive1);
        GamePlayer number2= online(deck2,player2);
        buildPassive(number2,passive2);
        gameState1.setFreind(unhandled);
        gameState1.setEnemy(number2);
        gameState2.setFreind(number2);
        gameState2.setEnemy(unhandled);
    }
    private GamePlayer online(String deck, Player player){
        player.setCurrentDeck(findDeck(player,deck));
         return makeGamePlayer(player);
    }
// for training
    public void buildPassive1() {
        buildPassive(gameState1.getFreind(),passive1);
    }
    private void buildPassive(GamePlayer gamePlayer, String passive){
        switch (passive) {
            case "twiceDraw":
                gamePlayer.setCardPerRound(2);
                break;
            case "offCard":
                gamePlayer.setOffCard(-1);
                break;
            case "freePower":
                gamePlayer.setHeroPowerPassive(true);
                break;
            case "manaJump":
                gamePlayer.setMaxManaPerRound(2);
                gamePlayer.setMana(2);
                break;
            case "nurse":
                gamePlayer.setNurse(true);
                break;
        }
    }

    private void makeFromDeckReader(GameState gameState) {
        gameState.setFreind(makeGamePlayer(deckReader.getFriendListCard(), true));
        gameState.setEnemy(makeGamePlayer(deckReader.getEnemyListCard(), false));
    }

    // making gameState gamePlayers when we do'nt have deckReader
    private void make(GameState gameState) {
        player1.setCurrentDeck(findDeck(player1,deck1));
        gameState.setFreind(makeGamePlayer(player1));
        gameState.getFreind().setNameOfPlayer("friend");
        gameState.setEnemy(makeGamePlayer(new DeckManager().buildEnemy("myEnemy")));
        gameState.getEnemy().setNameOfPlayer("enemy");

    }

    private Deck findDeck(Player player,String deck) {
        for (int i = 0; i < player.getAvailableDecks().size(); i++) {
            Deck Mydeck = player.getAvailableDecks().get(i);
            if (Mydeck.getName().equalsIgnoreCase(deck)) {
                return Mydeck;
            }
        }
        return null;
    }

    //maybe we want to use it later too .in coclusion we set it here public.
    public GamePlayer makeGamePlayer(Player player) {
        ArrayList<card> hand = new ArrayList<>();
        LinkedList<Minion> ground = new LinkedList<>();
        ArrayList<card> deck = new ArrayList<>();
        add(player.getCurrentDeck().getMinions(), deck);
        add(player.getCurrentDeck().getSpells(), deck);
        add(player.getCurrentDeck().getWeapens(), deck);
        ArrayList<card>init =new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            card card=deck.remove(0);
            init.add(card);
            System.out.println(card.toString());
        }
        GamePlayer gamePlayer = new GamePlayer(deck, hand, ground, new HeroCreator().createHero(player.getCurrentDeck().getDeckHero().getName()));
        gamePlayer.setInitCard(init);
        gamePlayer.setCup(player.getCurrentDeck().getCup());
        System.out.println("we set successfully "+gamePlayer.getInitCard().size());
        return gamePlayer;
    }

    public GamePlayer makeGamePlayer(Deck currentDeck) {
        ArrayList<card> hand = new ArrayList<>();
        LinkedList<Minion> ground = new LinkedList<>();
        List<card> deck = new ArrayList<>();
        add(currentDeck.getMinions(), deck);
        add(currentDeck.getSpells(), deck);
        add(currentDeck.getWeapens(), deck);
        for (int i = 0; i < 3; i++) {
            card card=deck.remove(0);
            hand.add(card);
        }
        GamePlayer gamePlayer = new GamePlayer(deck, hand, ground, new HeroCreator().createHero(currentDeck.getDeckHero().getName()));
        gamePlayer.setCup(currentDeck.getCup());
        return gamePlayer;
    }

    public GamePlayer makeGamePlayer(List<card> list, boolean friend) {
        ArrayList<card> hand = new ArrayList<>();
        LinkedList<Minion> ground = new LinkedList<>();
        if (!friend) {
            for (int i = 0; i < 3; i++) {
                card card=list.remove(0);
                hand.add(card);
            }
            GamePlayer gamePlayer = new GamePlayer(list, hand, ground, new HeroCreator().createHero("mage"));
            gamePlayer.setNameOfPlayer("enemy");
            return gamePlayer;
        } else {
            ArrayList<card> init = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                card card=list.remove(0);
                init.add(card);
            }
            GamePlayer gamePlayer = new GamePlayer(list, hand, ground, new HeroCreator().createHero("mage"));
            gamePlayer.setInitCard(init);
            System.out.println("we set successfully "+gamePlayer.getInitCard().size());
            gamePlayer.setNameOfPlayer("friend");
            return gamePlayer;
        }
    }

    public void add(List<? extends card> types, List<card> deck) {
        for (int i = 0; i < types.size(); i++) {
            deck.add(types.get(i));
        }
    }
}

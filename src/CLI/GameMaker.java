package CLI;

import logic.DeckManager;
import logic.DeckReader;
import logic.HeroCreator;
import model.Deck;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.List;

public class GameMaker {
    // for network faze and also some actions that we dont like to handle in menu.
    private Player player;
    private Deck enemyDeck;
    private DeckReader deckReader;
    private String deckReaderAddress;
    private String deck;
    private GamePlayer freind;
    private GamePlayer enemy;
    private GameState gameState;

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
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getDeckReaderAddress() {
        return deckReaderAddress;
    }

    public void setDeckReaderAddress(String deckReaderAddress) {
        this.deckReaderAddress = deckReaderAddress;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public GameMaker(Player player, String deckReaderAddress, String deck, GameState gameState) {
        this.player = player;
        this.deckReaderAddress = deckReaderAddress;
        this.deck = deck;
       this.gameState=gameState;
    }
    public void buildGameState(){
        if (deckReaderAddress != null) {
            deckReader = new DeckReader(deckReaderAddress);
            makeFromDeckReader(gameState);
        } else {
            make(gameState);
        }
    }

    private void makeFromDeckReader(GameState gameState) {
        gameState.setFreind(makeGamePlayer(deckReader.getFriendListCard()));
        gameState.setEnemy(makeGamePlayer(deckReader.getEnemyListCard()));
    }

    // making gameState gamePlayers when we do'nt have deckReader
    private void make(GameState gameState) {
        player.setCurrentDeck(findDeck());
        gameState.setFreind(makeGamePlayer(player.getCurrentDeck()));
        gameState.setEnemy(makeGamePlayer(new DeckManager().buildEnemy("myEnemy")));

    }

    private Deck findDeck() {
        for (int i = 0; i < player.getAvailableDecks().size(); i++) {
            Deck Mydeck = player.getAvailableDecks().get(i);
            if (Mydeck.getName().equalsIgnoreCase(deck)) {
                return Mydeck;
            }
        }
        return null;
    }

    //maybe we want to use it later too .in coclusion we set it here public.
    public GamePlayer makeGamePlayer(Deck currentDeck) {
        ArrayList<card> hand = new ArrayList<>();
        ArrayList<Minion> ground = new ArrayList<>();
        ArrayList<card> deck = new ArrayList<>();
        add(currentDeck.getMinions(), deck);
        add(currentDeck.getSpells(), deck);
        add(currentDeck.getWeapens(), deck);
        GamePlayer gamePlayer = new GamePlayer(deck, hand, ground, currentDeck.getDeckHero());
        return gamePlayer;
    }

    public GamePlayer makeGamePlayer(List<card> list) {
        ArrayList<card> hand = new ArrayList<>();
        ArrayList<Minion> ground = new ArrayList<>();
        ArrayList<card> deck = new ArrayList<>();
        GamePlayer gamePlayer = new GamePlayer(deck, hand, ground, new HeroCreator().create("mage"));
        return gamePlayer;
    }

    public void add(ArrayList<? extends card> types, ArrayList<card> deck) {
        for (int i = 0; i < types.size(); i++) {
            deck.add(types.get(i));
        }
    }
}

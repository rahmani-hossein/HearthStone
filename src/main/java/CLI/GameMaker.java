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
    private Player player;
    private Deck enemyDeck;
    private DeckReader deckReader;
    private String deckReaderAddress;
    private String deck;
    private GamePlayer freind;
    private GamePlayer enemy;
    private GameState gameState;
    private String passive;

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

    public GameMaker(Player player, String deckReaderAddress, String deck, GameState gameState, String passive) {
        this.player = player;
        this.deckReaderAddress = deckReaderAddress;
        this.deck = deck;
        this.gameState = gameState;
        this.passive = passive;

    }

    public void buildGameState() {
        if (deckReaderAddress != null && deckReaderAddress.length() >= 2) {
            deckReader = new DeckReader(deckReaderAddress);
            makeFromDeckReader(gameState);
        } else {
            make(gameState);
        }
    }

    public void buildPassive() {
        switch (passive) {
            case "twiceDraw":
                gameState.getFreind().setCardPerRound(2);
                break;
            case "offCard":
                gameState.getFreind().setOffCard(-1);
                break;
            case "freePower":
                gameState.getFreind().setHeroPowerPassive(true);
                break;
            case "manaJump":
                gameState.getFreind().setMaxManaPerRound(2);
                gameState.getFreind().setMana(2);
                break;
            case "nurse":
                gameState.getFreind().setNurse(true);
                break;
        }

    }

    private void makeFromDeckReader(GameState gameState) {
        gameState.setFreind(makeGamePlayer(deckReader.getFriendListCard(), true));
        gameState.setEnemy(makeGamePlayer(deckReader.getEnemyListCard(), false));
    }

    // making gameState gamePlayers when we do'nt have deckReader
    private void make(GameState gameState) {
        player.setCurrentDeck(findDeck());
        gameState.setFreind(makeGamePlayer(player));
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
        System.out.println("we set successfully "+gamePlayer.getInitCard().size());

        gamePlayer.setNameOfPlayer("friend");
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
        gamePlayer.setNameOfPlayer("enemy");
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

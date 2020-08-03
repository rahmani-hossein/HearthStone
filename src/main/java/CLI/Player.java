package CLI;

import model.Deck;
import model.Minion;
import model.spell;
import model.weapen;

import java.util.ArrayList;

public class Player {
    String username;
    String password;
    public int diamond = 80;
    Deck currentDeck;
    private ArrayList<Minion> availableCardsM = new ArrayList<>();
    private ArrayList<weapen> availableCardsW = new ArrayList<>();
    private ArrayList<spell> availableCardsS = new ArrayList<>();
    private ArrayList<Deck> availableDecks = new ArrayList<>();

    public Player(String username, String password, int diamond, Deck currentDeck, ArrayList<spell> availableCardsS, ArrayList<Minion> availableCardsM, ArrayList<weapen> availableCardsW, ArrayList<Deck> availableDecks) {
        this.username = username;
        this.password = password;
        this.diamond = 80;
        this.currentDeck = currentDeck;
        this.availableCardsW = availableCardsW;
        this.availableCardsM = availableCardsM;
        this.availableCardsS = availableCardsS;
        this.availableDecks = availableDecks;
    }

    public Player() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public Deck getCurrentDeck() {
        return currentDeck;
    }

    public void setCurrentDeck(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    public ArrayList<Minion> getAvailableCardsM() {
        return availableCardsM;
    }

    public void setAvailableCardsM(ArrayList<Minion> availableCardsM) {
        this.availableCardsM = availableCardsM;
    }

    public ArrayList<weapen> getAvailableCardsW() {
        return availableCardsW;
    }

    public void setAvailableCardsW(ArrayList<weapen> availableCardsW) {
        this.availableCardsW = availableCardsW;
    }

    public ArrayList<spell> getAvailableCardsS() {
        return availableCardsS;
    }

    public void setAvailableCardsS(ArrayList<spell> availableCardsS) {
        this.availableCardsS = availableCardsS;
    }

    public ArrayList<Deck> getAvailableDecks() {
        return availableDecks;
    }

    public void setAvailableDecks(ArrayList<Deck> availableDecks) {
        this.availableDecks = availableDecks;
    }
}

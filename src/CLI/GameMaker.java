package CLI;

import logic.DeckReader;
import model.Deck;

public class GameMaker {
    // for network faze and also some actions that we dont like to handle in menu.
    private Player player;
    private DeckReader deckReader;
    private String deckReaderAddress;
    private String deck;

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

    public GameMaker(Player player, String deckReaderAddress, String deck) {
        this.player = player;
        this.deckReaderAddress = deckReaderAddress;
        this.deck = deck;
        deckReader=new DeckReader(deckReaderAddress);
        make();
    }

    private void make(){
       player.setCurrentDeck(findDeck());

    }

    private Deck findDeck(){
        for (int i = 0; i < player.getAvailableDecks().size(); i++) {
            Deck Mydeck=player.getAvailableDecks().get(i);
            if (Mydeck.getName().equalsIgnoreCase(deck)){
                return Mydeck;
            }
        }
        return null;
    }
}

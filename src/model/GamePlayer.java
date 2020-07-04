package model;

import java.util.ArrayList;

public class GamePlayer {

    private ArrayList<card> deck;
    private ArrayList<card> hand;
    private ArrayList<Minion> ground;
    private Hero hero;


    public GamePlayer(ArrayList<card> deck, ArrayList<card> hand, ArrayList<Minion> ground, Hero hero) {
        this.deck = deck;
        this.hand = hand;
        this.ground = ground;
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public ArrayList<card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<card> deck) {
        this.deck = deck;
    }

    public ArrayList<card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<card> hand) {
        this.hand = hand;
    }

    public ArrayList<Minion> getGround() {
        return ground;
    }

    public void setGround(ArrayList<Minion> ground) {
        this.ground = ground;
    }
}

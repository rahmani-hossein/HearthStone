package model;

import java.util.ArrayList;

public class GamePlayer {

    private ArrayList<card> deck;
    private ArrayList<card> hand;
    private ArrayList<Minion> ground;
    private Hero hero;
    private int mana=1;
    private int maxManaPerRound=1;
    private int cardPerRound=1;
    private int offCard=0;// badan -1 mishe
    private boolean nurse=false;
    private boolean heroPowerPassive=false;
    private int heroPowerUse=1;



    public GamePlayer(ArrayList<card> deck, ArrayList<card> hand, ArrayList<Minion> ground, Hero hero) {
        this.deck = deck;
        this.hand = hand;
        this.ground = ground;
        this.hero = hero;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCardPerRound() {
        return cardPerRound;
    }


    public int getMaxManaPerRound() {
        return maxManaPerRound;
    }

    public void setMaxManaPerRound(int maxManaPerRound) {
        this.maxManaPerRound = maxManaPerRound;
    }

    public boolean isHeroPowerPassive() {
        return heroPowerPassive;
    }

    public void setHeroPowerPassive(boolean heroPowerPassive) {
        this.heroPowerPassive = heroPowerPassive;
    }

    public void setCardPerRound(int cardPerRound) {
        this.cardPerRound = cardPerRound;
    }

    public int getOffCard() {
        return offCard;
    }

    public void setOffCard(int offCard) {
        this.offCard = offCard;
    }

    public boolean isNurse() {
        return nurse;
    }

    public void setNurse(boolean nurse) {
        this.nurse = nurse;
    }

    public int getHeroPowerUse() {
        return heroPowerUse;
    }

    public void setHeroPowerUse(int heroPowerUse) {
        this.heroPowerUse = heroPowerUse;
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

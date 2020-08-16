package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GamePlayer {
private String nameOfPlayer;
    private List<card> deck;
    private ArrayList<card> hand;
    private LinkedList<Minion> ground;
    private weapen myWeapen=null;
    private Hero hero;
    private int mana=1;
    private int maxManaPerRound=1;
    private int cardPerRound=1;
    private int offCard=0;// badan -1 mishe
    private boolean nurse=false;
    private boolean heroPowerPassive=false;
    private int heroPowerUse=1;
    private ArrayList<card>initCard = new ArrayList<>();
    private int cup;



    public GamePlayer(List<card> deck, ArrayList<card> hand, LinkedList<Minion> ground, Hero hero) {
        this.deck = deck;
        this.hand = hand;
        this.ground = ground;
        this.hero = hero;
    }

    public GamePlayer() {
    }

    public ArrayList<card> getInitCard() {
        return initCard;
    }

    public void setInitCard(ArrayList<card> initCard) {
        this.initCard = initCard;
    }

    public weapen getMyWeapen() {
        return myWeapen;
    }

    public void setMyWeapen(weapen myWeapen) {
        this.myWeapen = myWeapen;
    }

    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public void setNameOfPlayer(String nameOfPlayer) {
        this.nameOfPlayer = nameOfPlayer;
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

    public List<card> getDeck() {
        return deck;
    }

    public void setDeck(List<card> deck) {
        this.deck = deck;
    }

    public ArrayList<card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<card> hand) {
        this.hand = hand;
    }

    public LinkedList<Minion> getGround() {
        return ground;
    }

    public void setGround(LinkedList<Minion> ground) {
        this.ground = ground;
    }

    public int getCup() {
        return cup;
    }

    public void setCup(int cup) {
        this.cup = cup;
    }
}

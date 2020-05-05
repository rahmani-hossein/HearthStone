package model;

import CLI.Administer;
import CLI.utilities;
import logic.CardManager;
import logic.Constans;

import javax.swing.text.Utilities;
import java.util.ArrayList;

public class Deck {
    private String name;
    private Hero deckHero;
    private ArrayList<Minion> minions=new ArrayList<>() ;
    private ArrayList<spell>spells=new ArrayList<>();
    private ArrayList<weapen>weapens=new ArrayList<>();
    private int wins=0;
    private int numbreOfUse=0;
    private String rarest="";
    private double winAverage=0;
    private double manaAverage=0;
    private double costAverage=0;

    public Deck(String name,Hero deckHero, ArrayList<Minion> minions, ArrayList<spell> spells, ArrayList<weapen> weapens) {
        this.name=name;
        this.deckHero = deckHero;
        this.minions = minions;
        this.spells = spells;
        this.weapens = weapens;
    }
    public Deck(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hero getDeckHero() {
        return deckHero;
    }

    public void setDeckHero(Hero deckHero) {
        this.deckHero = deckHero;
    }

    public ArrayList<Minion> getMinions() {
        return minions;
    }

    public void setMinions(ArrayList<Minion> minions) {
        this.minions = minions;
    }

    public ArrayList<spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<spell> spells) {
        this.spells = spells;
    }

    public ArrayList<weapen> getWeapens() {
        return weapens;
    }

    public void setWeapens(ArrayList<weapen> weapens) {
        this.weapens = weapens;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getNumbreOfUse() {
        return numbreOfUse;
    }

    public void setNumbreOfUse(int numbreOfUse) {
        this.numbreOfUse = numbreOfUse;
    }

    public String getRarest() {
        return rarest;
    }

    public void setRarest(String rarest) {
        this.rarest = rarest;
    }

    public double getWinAverage() {
        return winAverage;
    }

    public void setWinAverage(double winAverage) {
        this.winAverage = winAverage;
    }

    public double getManaAverage() {
        return manaAverage;
    }

    public void setManaAverage(double manaAverage) {
        this.manaAverage = manaAverage;
    }

    public void updateDeckInfo(){
        findManaAverage();
        findWinAverage();
        findCostAverage();
        findRarest();
    }
    // average of wins
    private void findWinAverage(){
        if (this.numbreOfUse==0){
            this.winAverage=0;
        }else {
            this.winAverage = (this.wins) / (this.numbreOfUse);
        }

    }
    private void findCostAverage(){
        int sum=findSumCost(minions)+findSumCost(spells)+findSumCost(weapens);
        int num=minions.size()+spells.size()+weapens.size();
        this.costAverage=sum/num;

    }
    private int findSumCost(ArrayList<?extends card> cards){
        int average=0;
        for (card card:cards){
            average+= card.getCost();
        }
        return average;
    }

    private void findManaAverage(){
        int sum=findSumManaCost(minions)+findSumManaCost(spells)+findSumManaCost(weapens);
        int num=minions.size()+spells.size()+weapens.size();
        this.manaAverage=sum/num;
    }
    private int findSumManaCost(ArrayList<?extends card> cards){
        int average=0;
        for (card card:cards){
           average+= card.getManaCost();
        }
        return average;
    }
    private String findRarest(){

        return "";
    }



//it is not comlete
//    private int findNum(ArrayList<?extends card>cards,String name){
//        int number=0;
//        for (card card:cards) {
//            if (card.getName().equals(name)){
//
//            }
//
//        }
//
//    }
    private int rarer(String rarity1,String rarity2){
       if (utilities.firstIndex(CardManager.getRarity(),rarity1)>utilities.firstIndex(CardManager.getRarity(),rarity2)){
           return 1;
       }
       else if (utilities.firstIndex(CardManager.getRarity(),rarity1)==utilities.firstIndex(CardManager.getRarity(),rarity2)){
           return 0;
       }
       else {
           return -1;
       }
    }
}

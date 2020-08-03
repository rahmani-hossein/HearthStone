package model;

import logic.CardManager;

import java.util.ArrayList;
import java.util.Random;

public class Deck implements Comparable<Deck> {
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
    private CardManager cardManager=new CardManager();

    public Deck(String name, Hero deckHero, ArrayList<Minion> minions, ArrayList<spell> spells, ArrayList<weapen> weapens) {
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
        System.out.println(rarest);

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
        if (num==0){
            this.costAverage=0;
        }
        else {
            this.costAverage = sum / num;
        }

    }
    private int findSumCost(ArrayList<?extends card> cards){
        int average=0;
        for (model.card card:cards){
            average+= card.getCost();
        }
        return average;
    }

    private void findManaAverage(){
        int sum=findSumManaCost(minions)+findSumManaCost(spells)+findSumManaCost(weapens);
        int num=minions.size()+spells.size()+weapens.size();
        if (num==0){
            this.manaAverage=0;
        }else {
            this.manaAverage = sum / num;
        }
    }
    private int findSumManaCost(ArrayList<?extends card> cards){
        int average=0;
        for (model.card card:cards){
           average+= card.getManaCost();
        }
        return average;
    }
    private void findRarest() {
        int totalSize = minions.size() + spells.size() + weapens.size();
        card[] myArray=builtList();
        if (totalSize == 0) {
            rarest="";
        }else {
            for (int i = 0; i < myArray.length - 1; i++) {
                if (cardManager.compareTo(myArray[i], myArray[i + 1], this) == 1) {
                    card temp = myArray[i + 1];
                    myArray[i + 1] = myArray[i];
                    myArray[i] = temp;
                }
            }
            rarest = myArray[myArray.length - 1].getName();
        }
    }
    private card[] builtList(){
        ArrayList<card> myCards=new ArrayList<>();
        for (int i = 0; i < minions.size(); i++) {
            myCards.add(minions.get(i));
        }
        for (int i = 0; i < spells.size(); i++) {
            myCards.add(spells.get(i));
        }
        for (int i = 0; i < weapens.size(); i++) {
            myCards.add(weapens.get(i));
        }
        card[] array=new card[myCards.size()];
        for (int i=0;i<myCards.size();i++){
            array[i]=myCards.get(i);
        }

        return array;
    }

    public double getCostAverage() {
        return costAverage;
    }

    public void setCostAverage(double costAverage) {
        this.costAverage = costAverage;
    }

    @Override
    public int compareTo(Deck o) {
        if (winAverage!=o.getWinAverage()) {
            if (winAverage > o.getWinAverage()) {
                return 1;
            } else {
                return -1;
            }
        }
        else {
            if (wins!=o.getWins()) {
                if (wins > o.getWins()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            else {
                if (numbreOfUse!=o.getNumbreOfUse()){
                    if (numbreOfUse>o.getNumbreOfUse()){
                        return 1;
                    }
                    else {
                        return -1;
                    }
                }
                else {
                    if (costAverage!=o.getCostAverage()){
                        if (costAverage>o.getCostAverage()){
                            return 1;
                        }
                        else {
                            return -1;
                        }
                    }
                    else {
                        Random random=new Random();
                        int rand=random.nextInt(2);
                        if (rand==1){
                            return 1;
                        }
                        else {
                            return -1;
                        }
                    }

                }
            }
        }
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

}

package logic;

import model.*;

import java.util.ArrayList;

public class DeckManager {
    private CardManager cardManager=new CardManager();
    private HeroCreator heroCreator =new HeroCreator();

    public DeckManager(){

    }

    public Deck buildDeck(String name,Hero hero, ArrayList<Minion> availableCardsM, ArrayList<spell> availableCardsS, ArrayList<weapen> availableCardsW) {
        ArrayList<Minion> minions = new ArrayList<>();
        for (int i = 0; i < availableCardsM.size(); i++) {
            minions.add(availableCardsM.get(i));
            minions.add(availableCardsM.get(i));
        }
        ArrayList<spell> spells = new ArrayList<>();
        for (int i = 0; i < availableCardsS.size(); i++) {
            spells.add(availableCardsS.get(i));
            spells.add(availableCardsS.get(i));
        }
        ArrayList<weapen> weapens = new ArrayList<>();
        for (int i = 0; i < availableCardsW.size(); i++) {
            weapens.add(availableCardsW.get(i));
            weapens.add(availableCardsW.get(i));
        }
        Deck deck = new Deck(name, hero, minions, spells, weapens);
        deck.updateDeckInfo();
        return deck;
    }

    public Deck buildEnemy(String enemyName){
        ArrayList<Minion> availableCardsM = new ArrayList<>();
        ArrayList<spell> availableCardsS = new ArrayList<>();
        ArrayList<weapen> availableCardsW = new ArrayList<>();
        ArrayList<Deck> availableDecks = new ArrayList<>();

        availableCardsM.add(cardManager.createM("hotAirballon"));
        availableCardsM.add(cardManager.createM("blazingBattlemage"));
        availableCardsM.add(cardManager.createM("evasiveChimaera"));
        availableCardsS.add(cardManager.createS("malygosFireball"));
        availableCardsW.add(cardManager.createW("arcaniteReaper"));
        availableCardsW.add(cardManager.createW("bloodRazor"));
        availableCardsW.add(cardManager.createW("assassinBlade"));
        availableCardsW.add(cardManager.createW("dragonClaw"));
        availableCardsS.add(cardManager.createS("polymorph"));
        availableCardsS.add(cardManager.createS("fireBlast"));

        Deck deck=buildDeck(enemyName,heroCreator.createHero("mage"),availableCardsM,availableCardsS,availableCardsW);
        return deck;
    }

}

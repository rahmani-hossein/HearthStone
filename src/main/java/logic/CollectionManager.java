package logic;

import model.Player;
import client.Controller;
import model.*;

import java.util.ArrayList;

import static CLI.utilities.contains;

public class CollectionManager {
    Constans constans = Controller.getInstance().getConstants();
    private Player player;
    private CardManager cardManager = new CardManager();
    private HeroCreator heroCreator=new HeroCreator();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String[] getdeck() {
        String[] decks = new String[player.getAvailableDecks().size()];
        for (int i = 0; i < player.getAvailableDecks().size(); i++) {
            decks[i] = player.getAvailableDecks().get(i).getName();
        }
        return decks;
    }

    public CollectionManager(Player player) {
        this.player = player;
    }


    public void changeHero(Deck deck, String newHero) {
        Deck deck1 = null;

        if (allowChangeHero(deck)) {
            Hero hero = makeHero(newHero);
            deck.setDeckHero(hero);
            if (deck.getName().equals(player.getCurrentDeck().getName())) {
                deck1 = player.getCurrentDeck();
                deck1.setDeckHero(hero);
            }

        } else {
            System.out.println(" you can not change hero of this deck");
        }
    }

    public boolean allowChangeHero(Deck deck) {
        for (int i = 0; i < deck.getMinions().size(); i++) {
            if (showType(deck.getMinions().get(i).getName()).equalsIgnoreCase(deck.getDeckHero().getName())) {
                return false;
            }
        }
        for (int i = 0; i < deck.getSpells().size(); i++) {
            if (showType(deck.getSpells().get(i).getName()).equalsIgnoreCase(deck.getDeckHero().getName())) {
                return false;
            }
        }
        for (int i = 0; i < deck.getWeapens().size(); i++) {
            if (showType(deck.getWeapens().get(i).getName()).equalsIgnoreCase(deck.getDeckHero().getName())) {
                return false;
            }
        }
        return true;
    }

    public void deleteDeck(Deck deck) {
        player.getAvailableDecks().remove(deck);
    }

    public void changeDeckName(Deck deck, String name) {
        for (Deck deck1 : player.getAvailableDecks()) {
            if (deck1.getName().equals(deck.getName())) {
                deck1.setName(name);
            }
        }
        deck.setName(name);

    }

    public Hero makeHero(String nameHero) {
        Hero hero1 = null;
        if (nameHero.equalsIgnoreCase("priest")) {
            hero1=heroCreator.createHero("priest");
        } else if (nameHero.equalsIgnoreCase("hunter")) {
            hero1=heroCreator.createHero("hunter");
        } else if (nameHero.equalsIgnoreCase("warlock")) {
            hero1 =heroCreator.createHero("warlock");
        } else if (nameHero.equalsIgnoreCase("rouge")) {
            hero1 = heroCreator.createHero("rouge");

        } else if (nameHero.equalsIgnoreCase("mage")) {
            hero1 = heroCreator.createHero("mage");

        }
        return hero1;
    }

    public Deck makeDeck(String nameDeck, String hero) {
        ArrayList<Minion> minions = new ArrayList<>();
        ArrayList<spell> spells = new ArrayList<>();
        ArrayList<weapen> weapens = new ArrayList<>();
        Hero hero1 = makeHero(hero);
        Deck deck = new Deck(nameDeck, hero1, minions, spells, weapens);
        player.getAvailableDecks().add(deck);
        deck.updateDeckInfo(cardManager);
        return deck;
    }

    public void addToDeck(String name, Deck deck) {
        // Deck deck1 = null;
//        if (player.getCurrentDeck().getName().equals(deck.getName())) {
//            deck1 = player.getCurrentDeck();
//        }
        if (allowAdd(name, deck)) {
            if (cardManager.tellType(name).equalsIgnoreCase("Minion")) {
                Minion minion = cardManager.createM(name);
                deck.getMinions().add(minion);
                deck.updateDeckInfo(cardManager);
            } else if (cardManager.tellType(name).equalsIgnoreCase("spell")) {
                spell spell = cardManager.createS(name);
                deck.getSpells().add(spell);
                deck.updateDeckInfo(cardManager);
            } else if (cardManager.tellType(name).equalsIgnoreCase("weapen")) {
                weapen weapen = cardManager.createW(name);
                deck.getWeapens().add(weapen);
                deck.updateDeckInfo(cardManager);
            }
            // update the current Deck

        }
    }

    public void removeFromDeck(String name, Deck deck) {

        if (cardManager.tellType(name).equalsIgnoreCase("Minion")) {
            Minion minion = cardManager.createM(name);
            deck.getMinions().remove(minion);
            deck.updateDeckInfo(cardManager);
        } else if (cardManager.tellType(name).equalsIgnoreCase("spell")) {
            spell spell = cardManager.createS(name);
            deck.getSpells().remove(spell);
            deck.updateDeckInfo(cardManager);
        } else if (cardManager.tellType(name).equalsIgnoreCase("weapen")) {
            weapen weapen = cardManager.createW(name);
            deck.getWeapens().remove(weapen);
            deck.updateDeckInfo(cardManager);
        }
    }

    public boolean allowAdd(String name, Deck deck) {
        if ((cardManager.number(player, name) >= 0) && (cardManager.number(deck, name) <= 1) && ((showType(name).equalsIgnoreCase("neutral")) || showType(name).equalsIgnoreCase(deck.getDeckHero().getName()))) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> allCards() {
        ArrayList<String> cards = new ArrayList<>();
        for (String string : constans.getCardNames()) {
            cards.add(string);
        }
        return cards;
    }

    public ArrayList<String> searchEngine(String string) {
        ArrayList<String> search = new ArrayList<>();
        for (String string1 : constans.getCardNames()) {
            if (string1.toLowerCase().contains(string)) {
                search.add(string1);
            }
        }
        return search;
    }

    public ArrayList<String> knocked() {
        ArrayList<String> knocked = new ArrayList<>();
        for (String name : constans.getCardNames()) {
            if (isKnocked(name)) {
                knocked.add(name);
            }
        }
        return knocked;
    }

    public boolean isKnocked(String name) {
        if (cardManager.number(player, name) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> DeckCards(String deckName) {
        ArrayList<String> deckNames = new ArrayList<>();
        for (Minion minion : getDeck(deckName).getMinions()) {
            deckNames.add(minion.getName());
        }
        for (model.spell spell : getDeck(deckName).getSpells()) {
            deckNames.add(spell.getName());
        }
        for (model.weapen weapen : getDeck(deckName).getWeapens()) {
            deckNames.add(weapen.getName());
        }
        return deckNames;
    }

    public Deck getDeck(String name) {
        for (Deck deck : player.getAvailableDecks()) {
            if (deck.getName().equalsIgnoreCase(name)) {
                return deck;
            }
        }
        return null;
    }

    public ArrayList<String> findCost(int cost) {
        ArrayList<String> findCosts = new ArrayList<>();
        for (String string : constans.getCardNames()) {
            if (costfinder(string) == cost) {
                findCosts.add(string);
            }
        }
        return findCosts;
    }

    public int costfinder(String name) {
        int cost = constans.getCostsMap().get(name);
        return cost;
    }

    public ArrayList<String> types(String typeName) {
        ArrayList<String> type = new ArrayList<>();
        for (String string : constans.getCardNames()) {
            if (showType(string).equalsIgnoreCase(typeName)) {
                type.add(string);
            }
        }
        return type;
    }

    public ArrayList<String> findHeroClass(String name) {
        ArrayList<String> heroType = new ArrayList<>();
        for (String string : constans.getCardNames()) {
            if (showType(string).equalsIgnoreCase(name)) {
                heroType.add(string);
            }
        }
        return heroType;
    }

    public String showType(String name) {
        if (contains(constans.getPriest(), name)) {
            return "priest";
        } else if (contains(constans.getWarlock(), name)) {
            return "warlock";

        } else if (contains(constans.getMage(), name)) {
            return "mage";
        } else if (contains(constans.getRouge(), name)) {
            return "rouge";
        } else if (contains(constans.getHunter(), name)) {
            return "hunter";
        } else {
            return "neutral";
        }
    }
}

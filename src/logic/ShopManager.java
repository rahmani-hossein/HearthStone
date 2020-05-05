package logic;

import CLI.Administer;
import CLI.Player;
import model.*;

import java.util.ArrayList;

import static CLI.utilities.contains;

public class ShopManager {
    private Player player;
    private CardManager cardManager =new CardManager();

    public ShopManager(Player player ) {
        this.player = player;
    }

    public int seeWallet() {
        return player.getDiamond();
    }

    public void sell(String cardName) {
        if (canSell(cardName)) {
            cardManager.remove(player.getAvailableCardsS(), cardName);
            cardManager.remove(player.getAvailableCardsM(), cardName);
            cardManager.remove(player.getAvailableCardsW(), cardName);
            player.setDiamond(player.getDiamond() + Constans.costsMap.get(cardName));
        }

    }

    public ArrayList<String> showBuyable() {
        ArrayList<String> buyable =new ArrayList<>();
        for ( String  name: Constans.cardNames) {
            if (canBuy(name)){
                buyable.add(name);
            }
        }
        return buyable;
    }

    public ArrayList<String> showSellable(){
        ArrayList<String> sellable =new ArrayList<>();
        for ( weapen weapen  : player.getAvailableCardsW()) {
            if (canSell(weapen.getName())){
                sellable.add(weapen.getName());
            }
        }
        for ( Minion minion  : player.getAvailableCardsM()) {
            if (canSell(minion.getName())){
                sellable.add(minion.getName());
            }
        }
        for ( spell spell  : player.getAvailableCardsS()) {
            if (canSell(spell.getName())){
                sellable.add(spell.getName());
            }
        }
        return sellable;
    }
    public boolean canSell(String cardName) {
        if (cardManager.number(player,cardName)>0) {
            for (Deck deck : player.getAvailableDecks()) {
                if (cardManager.number(deck, cardName) >= 1) {
                    //  System.out.println("cansell:"+cardName);
                    System.out.println("selled");
                    return false;
                }

            }
            return true;
        }
        else {
            return false;
        }
    }

    public void buy(String cardName) {
        if (canBuy(cardName)) {
            addToList(cardName);
            player.setDiamond(player.getDiamond() - Constans.costsMap.get(cardName));
        }
    }

    public void addToList(String name) {
        String type = Constans.types.get(name);
        if (type.equalsIgnoreCase("minion")) {
            player.getAvailableCardsM().add( cardManager.createM(name));
        } else if (type.equalsIgnoreCase("spell")) {
            player.getAvailableCardsS().add( cardManager.createS(name));
        } else if (type.equalsIgnoreCase("weapen")) {
            player.getAvailableCardsW().add( cardManager.createW(name));
        }
    }

    public boolean canBuy(String cardName) {
        if (cardManager.number(player, cardName) == 0) {
            return true;
        }
        return false;
    }


//                    for (Hero hero2 : player.availableHeroes) {
//                        if (administer.number(hero1, sell) >= 1 && administer.number(hero2, sell) >= 1) {
//                            player.diamond += administer.create(sell).getCost();
}

package logic;

import CLI.Administer;
import CLI.Player;
import CLI.utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static CLI.utilities.contains;

public class CardManager {
    ObjectMapper objectMapper = new ObjectMapper();


public CardManager(){

}


    public static String[] getRarity() {
        return rarity;
    }

    public static void setRarity(String[] rarity) {
        CardManager.rarity = rarity;
    }

    static String[] rarity = {"common", "rare", "epic", "legendary"};

public String tellRarity(String name){
    String rar=null;
    System.out.println(name);
   if( tellType(name).equalsIgnoreCase("minion")){
       rar= createM(name).getRarity();
    }
   else if (tellType(name).equalsIgnoreCase("spell")){
       rar=createS(name).getRarity();
   }
   else if (tellType(name).equalsIgnoreCase("weapen")){
       rar=createW(name).getRarity();
   }
   return rar;
}


    public String tellType(String name){
    if (name.equalsIgnoreCase("dreadScale")||name.equalsIgnoreCase("fireHawk")){
        return "minion";
    }
      else  if (contains(Constans.minions,name)){
            return "minion";
        }
        else if (contains(Constans.spells,name)){
            return "spell";
        }
        else if ((contains(Constans.weapens,name))){
            return "weapen";
        }
        else {
            return null;
        }

    }
    public weapen createW(String name)  {
        String st = String.format("Json\\%s.json", name);
        FileReader fileReader ;
        try {
            fileReader = new FileReader(st);

                // objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
               weapen weapen = objectMapper.readValue(fileReader, weapen.class);
                return weapen;
        }catch (IOException e) {
            System.out.println("can not create card ");
                e.printStackTrace();
            }

        return null;
    }
    public spell createS(String name)  {
        String st = String.format("Json\\%s.json", name);
        FileReader fileReader ;
        try {
            fileReader = new FileReader(st);
               spell spell = objectMapper.readValue(fileReader, spell.class);
                return spell;
        }catch (IOException e) {
            System.out.println("can not create card ");
            e.printStackTrace();
        }

        return null;
    }
    public Minion createM(String name)  {
        String st = String.format("Json\\%s.json", name);
        FileReader fileReader;
        try {
            fileReader = new FileReader(st);


               Minion minion = objectMapper.readValue(fileReader, Minion.class);
                return minion;
        }catch (IOException e) {
            System.out.println("can not create card ");
            e.printStackTrace();
        }

        return null;
    }
    public void remove(ArrayList<?extends card> cards,String name){
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equalsIgnoreCase(name)){
                cards.remove(cards.get(i));
                break;
            }
        }
        }



    public int number(Player player, String name) {
        int k = 0;
        if (contains(Constans.minions, name)) {
            for (model.card card : player.getAvailableCardsM()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else if (contains(Constans.spells, name)) {
            for (card card : player.getAvailableCardsS()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else if (contains(Constans.weapens, name)) {
            for (card card : player.getAvailableCardsW()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else {
            k = -1;
        }
        return k;
    }
    public int number(Deck deck, String name) {
        int k = 0;
        if (contains(Constans.minions, name)) {
            for (card card : deck.getMinions()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else if (contains(Constans.spells, name)) {
            for (card card : deck.getSpells()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else if (contains(Constans.weapens, name)) {
            for (card card : deck.getWeapens()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else {
            k = -1;
        }
        return k;
    }
}

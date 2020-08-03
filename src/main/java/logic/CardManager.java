package logic;

import CLI.Player;
import client.Controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static CLI.utilities.contains;

public class CardManager {
    ObjectMapper objectMapper = new ObjectMapper();
    Constans constans = Controller.getInstance().getConstants();
    CardFactory cardFactory = new CardFactory();


    public CardManager() {

    }


    public static String[] getRarity() {
        return rarity;
    }

    public static void setRarity(String[] rarity) {
        CardManager.rarity = rarity;
    }

    public static String[] rarity = {"common", "rare", "epic", "legendary"};

    public String tellRarity(String name) {
        String rar = null;
        System.out.println(name);
        if (tellType(name).equalsIgnoreCase("minion")) {
            rar = createM(name).getRarity();
        } else if (tellType(name).equalsIgnoreCase("spell")) {
            rar = createS(name).getRarity();
        } else if (tellType(name).equalsIgnoreCase("weapen")) {
            rar = createW(name).getRarity();
        }
        return rar;
    }


    public String tellType(String name) {
        if (name.equalsIgnoreCase("dreadScale") || name.equalsIgnoreCase("fireHawk")) {
            return "minion";
        } else if (contains(constans.getMinions(), name)) {
            return "minion";
        } else if (contains(constans.getSpells(), name)) {
            return "spell";
        } else if ((contains(constans.getWeapens(), name))) {
            return "weapen";
        } else {
            return null;
        }

    }

    public weapen createW(String name) {
        String st = String.format("Json/%s.json", name);
        FileReader fileReader;
        try {
            fileReader = new FileReader(st);
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            Map<String, Object> map = objectMapper.readValue(fileReader, typeRef);
            weapen weapen=cardFactory.createWeapen(map);
            return weapen;
        } catch (IOException e) {
            System.out.println("can not create card ");
            e.printStackTrace();
        }

        return null;
    }

    public spell createS(String name) {
        String st = String.format("Json/%s.json", name);
        FileReader fileReader;
        try {
            fileReader = new FileReader(st);
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            Map<String, Object> map = objectMapper.readValue(fileReader, typeRef);
           spell spell =cardFactory.createSpell(map);
           return spell;
        } catch (IOException e) {
            System.out.println("can not create card ");
            e.printStackTrace();
        }

        return null;
    }
//    public <T extends card> T create(String name) {
//        String st = String.format("Json/%s.json", name);
//        FileReader fileReader;
//        try {
//            fileReader = new FileReader(st);
//            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
//            };
//            Map<String, Object> map = objectMapper.readValue(fileReader, typeRef);
//             = cardFactory.createNew(map);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }

    public Minion createM(String name) {
        String st = String.format("Json/%s.json", name);
        FileReader fileReader;
        try {
            fileReader = new FileReader(st);
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            Map<String, Object> map = objectMapper.readValue(fileReader, typeRef);
            Minion minion = cardFactory.createMinion(map);

            return minion;
        } catch (IOException e) {
            System.out.println("can not create card ");
            e.printStackTrace();
        }
        return null;
    }

    public void remove(ArrayList<? extends card> cards, String name) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equalsIgnoreCase(name)) {
                cards.remove(cards.get(i));
                break;
            }
        }
    }


    public int number(Player player, String name) {
        int k = 0;
        if (contains(constans.getMinions(), name)) {
            for (model.card card : player.getAvailableCardsM()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else if (contains(constans.getSpells(), name)) {
            for (model.card card : player.getAvailableCardsS()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else if (contains(constans.getWeapens(), name)) {
            for (model.card card : player.getAvailableCardsW()) {
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
        if (contains(constans.getMinions(), name)) {
            for (model.card card : deck.getMinions()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else if (contains(constans.getSpells(), name)) {
            for (model.card card : deck.getSpells()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else if (contains(constans.getWeapens(), name)) {
            for (model.card card : deck.getWeapens()) {
                if (card.getName().equalsIgnoreCase(name)) {
                    k++;
                }
            }
        } else {
            k = -1;
        }
        return k;
    }

    private int findIndex(String type1) {
        for (int i = 0; i < rarity.length; i++) {
            if (type1.equalsIgnoreCase(rarity[i])) {
                return i;
            }
        }
        System.out.println("cant find the type of card in sorting deck");
        return -1;
    }

    private int findRarer(card card1, card card2) {
        int i = findIndex(card1.getRarity());
        int j = findIndex(card2.getRarity());
        if (i > j) {
            return 1;
        }
        if (i < j) {
            return -1;
        }
        //i==j
        else {
            if (card1.getManaCost() > card2.getManaCost()) {
                return 1;
            }
            if (card2.getManaCost() > card1.getManaCost()) {
                return -1;
            } else {
                if (card1.getType().equalsIgnoreCase(card2.getType())) {
                    Random random = new Random();
                    int rand = random.nextInt(2);
                    return rand;
                } else {
                    if (card1.getType().equalsIgnoreCase("minion")) {
                        return 1;
                    }
                    if (card2.getType().equalsIgnoreCase("minion")) {
                        return -1;
                    } else {
                        if (card1.getType().equalsIgnoreCase("spell")) {
                            return 1;
                        }
                        if (card2.getType().equalsIgnoreCase("spell")) {
                            return -1;
                        } else {
                            if (card1.getType().equalsIgnoreCase("weapen")) {
                                return 1;
                            }
                            if (card2.getType().equalsIgnoreCase("weapen")) {
                                return -1;
                            } else {
                                Random random = new Random();
                                int rand = random.nextInt(2);
                                if (rand == 1) {
                                    return 1;
                                } else {
                                    return -1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public int compareTo(card card1, card card2, Deck deck) {
        if (number(deck, card1.getName()) > number(deck, card2.getName())) {
            return 1;
        } else if (number(deck, card1.getName()) < number(deck, card2.getName())) {
            return -1;
        } else {
            return findRarer(card1, card2);
        }

    }
}

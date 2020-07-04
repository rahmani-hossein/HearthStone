package logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.card;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DeckReader {
    String address;
    ObjectMapper objectMapper;
    Random random = new Random(System.nanoTime());
    private CardManager cardManager = new CardManager();
    private List<card> friendListCard=new ArrayList<>();
    private List<card> enemyListCard=new ArrayList<>();

    public DeckReader(String name) {
        buildDeck(name);
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<card> getFriendListCard() {
        return friendListCard;
    }

    public void setFriendListCard(List<card> friendListCard) {
        this.friendListCard = friendListCard;
    }

    public List<card> getEnemyListCard() {
        return enemyListCard;
    }

    public void setEnemyListCard(List<card> enemyListCard) {
        this.enemyListCard = enemyListCard;
    }

    public CardManager getCardManager() {
        return cardManager;
    }

    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    private void buildDeck(String name) {
        address = String.format("resources/deck/%s.json", name);
        objectMapper = new ObjectMapper();
        FileReader fileReader;
        try {
            fileReader = new FileReader(address);
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            Map<String, Object> map = objectMapper.readValue(fileReader, typeRef);
            List<String> friendList = (List<String>) map.get("friend");
            List<String> enemyList = (List<String>) map.get("enemy");
            getList(friendList,  friendListCard);
            getList(enemyList,  enemyListCard);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToDeck(String nameEkhtiari,List<card> newDeck) {
        switch (nameEkhtiari) {
            case "minion":
                int rand1 = random.nextInt(Constans.getInstance().getMinionEkhtiari().length);
                newDeck.add(cardManager.createM(Constans.getInstance().getMinionEkhtiari()[rand1]));
                break;
            case "spell":
                int rand2 = random.nextInt(Constans.getInstance().getSpellEkhtiari().length);
                newDeck.add(cardManager.createS(Constans.getInstance().getSpellEkhtiari()[rand2]));
                break;
            case "weapen":
                int rand3 = random.nextInt(Constans.getInstance().getWeapenEkhtiari().length);
                newDeck.add(cardManager.createW(Constans.getInstance().getWeapenEkhtiari()[rand3]));
                break;
            default:
                if (Arrays.asList(Constans.getInstance().getMinions()).contains(nameEkhtiari)) {
                    add(nameEkhtiari,newDeck);
                } else if (Arrays.asList(Constans.getInstance().getSpells()).contains(nameEkhtiari)) {
                    add(nameEkhtiari,newDeck);
                } else if (Arrays.asList(Constans.getInstance().getWeapens()).contains(nameEkhtiari)) {
                    add(nameEkhtiari,newDeck);
                }
        }
    }

    // for friend and enemy list building for using method;
    private void getList(List<String> list,List<card>cardList ) {
        for (int i = 0; i < list.size(); i++) {
            addToDeck(list.get(i),cardList);
        }
    }

    private void add(String name,List<card>newDeck) {
        newDeck.add(cardManager.createS(name));
    }
}

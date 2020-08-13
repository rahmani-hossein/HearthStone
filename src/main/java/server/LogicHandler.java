package server;

import CLI.utilities;
import client.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.CardManager;
import logic.CollectionManager;
import logic.PlayerManager;
import logic.ShopManager;
import model.Deck;
import model.Request;
import model.Score;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//for seprating logic and network layer and third layerd design
public class LogicHandler {

    private ClientHandler clientHandler;
    private PlayerManager playerManager;
    private ShopManager shopManager;
    private CollectionManager collectionManager;
    private GameServer gameServer;
    private CardManager cardManager = new CardManager();
    private ObjectMapper objectMapper = new ObjectMapper();

    public LogicHandler(GameServer gameServer, ClientHandler clientHandler) {
        this.gameServer = gameServer;
        playerManager = new PlayerManager(this.gameServer);
        this.clientHandler = clientHandler;
    }


    // due to its only for clientHandler we set it package level;
    void handleBuyable(Request request) {
        ArrayList<String> listBuy = getShopManager().showBuyable();
        String buyableString = null;
        try {
            buyableString = objectMapper.writeValueAsString(listBuy);
            request.setBody(buyableString);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void handleAllCard(Request request) {
        ArrayList<String> allCards = getCollectionManager().allCards();
        String allCardString = null;
        try {
            allCardString = objectMapper.writeValueAsString(allCards);
            request.setBody(allCardString);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you clicked to see allCards cards  " + utilities.time() + "\n", true);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void handleKnocked(Request request) {
        ArrayList<String> knocked = getCollectionManager().knocked();
        String knockedString = null;
        try {
            knockedString = objectMapper.writeValueAsString(knocked);
            request.setBody(knockedString);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you clicked to see knocked cards  " + utilities.time() + "\n", true);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void handleShowCost(Request request) {
        ArrayList<String> showCost = getCollectionManager().findCost(Integer.parseInt(request.getBody()));
        String costString = null;
        try {
            costString = objectMapper.writeValueAsString(showCost);
            request.setBody(costString);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you clicked to see cost of  cards  " + utilities.time() + "\n", true);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void handleShowClass(Request request) {
        String value = request.getBody();
        ArrayList<String> showClass = getCollectionManager().findHeroClass(value);
        String classString = null;
        try {
            classString = objectMapper.writeValueAsString(showClass);
            request.setBody(classString);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you clicked to see " + value + "s cards  " + utilities.time() + "\n", true);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void handleSearch(Request request) {
        String value = request.getBody();
        ArrayList<String> showClass = getCollectionManager().searchEngine(value);
        String classString = null;
        try {
            classString = objectMapper.writeValueAsString(showClass);
            request.setBody(classString);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you search " + value + " for relative  cards  " + utilities.time() + "\n", true);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void handleAdd(Request request) {
        String deckName = request.getParameters().get(0);
        Deck deck = collectionManager.getDeck(deckName);
        String card = request.getParameters().get(1);
        if (collectionManager.allowAdd(card, deck)) {
            collectionManager.addToDeck(card, deck);
            try {
                String player = objectMapper.writeValueAsString(clientHandler.getGameState().getPlayer());
                request.setBody(player);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            myLogger(clientHandler.getTxtAddress(), "you add  " + card + "to " + deckName + " " + utilities.time() + "\n", true);
        } else {
            request.setBody("false");
            myLogger(clientHandler.getTxtAddress(), "you cant add  " + card + "to  " + deckName + " " + utilities.time() + "\n", true);
        }
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
    }

    void handleRemove(Request request) {
        String deckName = request.getParameters().get(0);
        Deck deck = collectionManager.getDeck(deckName);
        String card = request.getParameters().get(1);
        collectionManager.removeFromDeck(card, deck);
        try {
            String player = objectMapper.writeValueAsString(clientHandler.getGameState().getPlayer());
            request.setBody(player);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you remove card  " + card + " from deck " + deckName + " " + utilities.time() + "\n", true);

    }

    void handleChangeName(Request request) {
        String deckOldName = request.getParameters().get(0);
        String newName = request.getParameters().get(1);
        collectionManager.changeDeckName(collectionManager.getDeck(deckOldName), newName);
       setBody(request,clientHandler.getGameState().getPlayer());
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you change name of deck  " + deckOldName + " to " + newName + " " + utilities.time() + "\n", true);

    }
    void handleChangeHero(Request request){
        String deckOldName=request.getParameters().get(0);
        String heroName= request.getParameters().get(1);
        Deck deck1 = collectionManager.getDeck(deckOldName);
        if (collectionManager.allowChangeHero(deck1)) {
            collectionManager.changeHero(deck1, heroName);
            setBody(request,clientHandler.getGameState().getPlayer());
            ArrayList<String> decks= arratToList(collectionManager.getdeck());
            request.setParameters(decks);
            myLogger(clientHandler.getTxtAddress(), "you change hero of deck " + deckOldName + " to " + heroName + " " + utilities.time() + "\n", true);
        }
        else {
            request.setBody("false");
            myLogger(clientHandler.getTxtAddress(), "you  cant change hero of deck " + deckOldName + " to " + heroName + " " + utilities.time() + "\n", true);
        }
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
    }
    void handleMakeDeck(Request request){

      Deck  deck1 = collectionManager.makeDeck(request.getParameters().get(0), request.getParameters().get(1));
        clientHandler.getGameState().getPlayer().getAvailableDecks().add(deck1);
        setBody(request,clientHandler.getGameState().getPlayer());
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you make a deck with name " + request.getParameters().get(0) + " " + utilities.time() + "\n", true);

    }
    void handleRemoveDeck(Request request){
        String delete = request.getBody();
        collectionManager.deleteDeck(collectionManager.getDeck(delete));
        setBody(request,clientHandler.getGameState().getPlayer());
        ArrayList<String> list = new ArrayList<>();
        list.add(delete);
        request.setParameters(list);
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you delete a deck with name " + delete + " " + utilities.time() + "\n", true);

    }
    void handleDeckButton(Request request){
        String name= request.getBody();
        ArrayList<String> parameters= collectionManager.DeckCards(name);
        request.setParameters(parameters);
       setBody(request,collectionManager.getDeck(name));
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you add deckButton" + name + " " + utilities.time() + "\n", true);
    }
    void handleDeckNames(Request request){
        ArrayList<String> parameters= arratToList(collectionManager.getdeck());
        request.setParameters(parameters);
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you get all decks" + utilities.time() + "\n", true);
    }
    void handleTop10(Request request){
        ArrayList<Score> scores=playerManager.getTop10();
        String top10String = null;
        try {
            top10String = objectMapper.writeValueAsString(scores);
            request.setBody(top10String);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    void handleMe(Request request){
        ArrayList<Score> scores=playerManager.getMe(clientHandler.getGameState().getPlayer());
        String top10String = null;
        try {
            top10String = objectMapper.writeValueAsString(scores);
            request.setBody(top10String);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private <A>void setBody(Request request,A Object){
        try {
            String ObjectString = objectMapper.writeValueAsString(Object);
            request.setBody(ObjectString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private ArrayList<String> arratToList(String[]strings){
        ArrayList<String> list=new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
        return list;
    }

    public void myLogger(String fileName, String write, boolean append) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName, append);
            fileWriter.write(write);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ShopManager getShopManager() {
        return shopManager;
    }

    public void setShopManager(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public GameServer getGameServer() {
        return gameServer;
    }

    public void setGameServer(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    public CardManager getCardManager() {
        return cardManager;
    }

    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }
}

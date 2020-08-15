package server;

import CLI.GameMaker;
import CLI.LogicMapper;
import CLI.utilities;
import Interfaces.Attackable;
import client.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.*;
import model.*;
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
    private GameManager gameManager;
    private GameMaker gameMaker;
    private CollectionManager collectionManager;
    private GameServer gameServer;
    private CardManager cardManager = new CardManager();
    private ObjectMapper objectMapper = new ObjectMapper();
    private FileWriter fileWriter;

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

    void handleInformation(Request request) {
        ArrayList<String> infoForHandle = new ArrayList<>();
        String cardName = request.getBody();
        infoForHandle.add(cardName);
        infoForHandle.add("" + gameServer.getServerConstants().getCostsMap().get(cardName));
        infoForHandle.add(gameServer.getServerConstants().getTypes().get(cardName));
        infoForHandle.add(cardManager.tellRarity(cardName));
        request.setParameters(infoForHandle);
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
    }

    void handleInfoCollection(Request request) {
        ArrayList<String> info = new ArrayList<>();
        String cardName = request.getBody();
        info.add(cardName);
        info.add("" + gameServer.getServerConstants().getCostsMap().get(cardName));
        info.add(gameServer.getServerConstants().getTypes().get(cardName));
        info.add(cardManager.tellRarity(cardName));
        request.setParameters(info);
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
    }

    void handleBuyCollection(Request request) {
        String buyNameCollection = request.getBody();
        if (shopManager.canBuy(buyNameCollection)) {
            request.setBody("true");
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you can  buy " + buyNameCollection + "  go to shop " + utilities.time() + "\n", true);

        } else {
            ArrayList<String> parameters = new ArrayList<>();
            parameters.add("you can not buy");
            request.setParameters(parameters);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you can not buy " + buyNameCollection + " " + utilities.time() + "\n", true);
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
        setBody(request, clientHandler.getGameState().getPlayer());
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you change name of deck  " + deckOldName + " to " + newName + " " + utilities.time() + "\n", true);

    }

    void handleChangeHero(Request request) {
        String deckOldName = request.getParameters().get(0);
        String heroName = request.getParameters().get(1);
        Deck deck1 = collectionManager.getDeck(deckOldName);
        if (collectionManager.allowChangeHero(deck1)) {
            collectionManager.changeHero(deck1, heroName);
            setBody(request, clientHandler.getGameState().getPlayer());
            ArrayList<String> decks = arratToList(collectionManager.getdeck());
            request.setParameters(decks);
            myLogger(clientHandler.getTxtAddress(), "you change hero of deck " + deckOldName + " to " + heroName + " " + utilities.time() + "\n", true);
        } else {
            request.setBody("false");
            myLogger(clientHandler.getTxtAddress(), "you  cant change hero of deck " + deckOldName + " to " + heroName + " " + utilities.time() + "\n", true);
        }
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
    }

    void handleMakeDeck(Request request) {

        Deck deck1 = collectionManager.makeDeck(request.getParameters().get(0), request.getParameters().get(1));
        clientHandler.getGameState().getPlayer().getAvailableDecks().add(deck1);
        setBody(request, clientHandler.getGameState().getPlayer());
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you make a deck with name " + request.getParameters().get(0) + " " + utilities.time() + "\n", true);

    }

    void handleBuy(Request request) {
        String buyName = request.getBody();
        if (shopManager.canBuy(buyName)) {

            shopManager.buy(buyName);
            try {
                String playerString = objectMapper.writeValueAsString(clientHandler.getGameState().getPlayer());
                request.setBody(playerString);
                request.setResult(true);
                clientHandler.send(clientHandler.convertRequest(request));
                myLogger(clientHandler.getTxtAddress(), "you buy " + buyName + " " + utilities.time() + "\n", true);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            ArrayList<String> parameters = new ArrayList<>();
            parameters.add("you can not buy");
            request.setParameters(parameters);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you can not buy " + buyName + " " + utilities.time() + "\n", true);
        }
    }

    void handleSell(Request request) {
        String name = request.getBody();
        if (shopManager.canSell(name)) {

            shopManager.sell(name);
            try {
                String playerString = objectMapper.writeValueAsString(clientHandler.getGameState().getPlayer());
                request.setBody(playerString);
                request.setResult(true);
                clientHandler.send(clientHandler.convertRequest(request));
                myLogger(clientHandler.getTxtAddress(), "you sell " + name + " " + utilities.time() + "\n", true);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            ArrayList<String> parameters = new ArrayList<>();
            parameters.add("you can not sell");
            request.setParameters(parameters);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
            myLogger(clientHandler.getTxtAddress(), "you can not sell " + name + " " + utilities.time() + "\n", true);
        }
    }

    void handleSellable(Request request) {
        ArrayList<String> listSell = shopManager.showSellable();
        String scoreBoardString = null;
        try {
            scoreBoardString = objectMapper.writeValueAsString(listSell);
            request.setBody(scoreBoardString);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void handleRemoveDeck(Request request) {
        String delete = request.getBody();
        collectionManager.deleteDeck(collectionManager.getDeck(delete));
        setBody(request, clientHandler.getGameState().getPlayer());
        ArrayList<String> list = new ArrayList<>();
        list.add(delete);
        request.setParameters(list);
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you delete a deck with name " + delete + " " + utilities.time() + "\n", true);

    }

    void handleDeckButton(Request request) {
        String name = request.getBody();
        ArrayList<String> parameters = collectionManager.DeckCards(name);
        request.setParameters(parameters);
        setBody(request, collectionManager.getDeck(name));
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you add deckButton" + name + " " + utilities.time() + "\n", true);
    }

    void handleDeckNames(Request request) {
        ArrayList<String> parameters = arratToList(collectionManager.getdeck());
        request.setParameters(parameters);
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you get all decks" + utilities.time() + "\n", true);
    }

    void handleTop10(Request request) {
        ArrayList<Score> scores = playerManager.getTop10();
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

    void handleMe(Request request) {
        ArrayList<Score> scores = playerManager.getMe(clientHandler.getGameState().getPlayer());
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

    void handleLogout(Request request) {
        try {
            Player player = objectMapper.readValue(request.getBody(), Player.class);
            player.setState("offline");
            gameServer.changeState(player);
            fileWriter.close();
            playerManager.exit(player);
            request.setResult(true);
            clientHandler.setAlive(false);
            clientHandler.send(clientHandler.convertRequest(request));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleDelete(Request request){
        try {
            String givenPassword = request.getParameters().get(0);
            Player player = objectMapper.readValue(request.getBody(), Player.class);
            gameServer.changeState(player);
            fileWriter.close();
            if (playerManager.delete(givenPassword,player)){
                request.setBody("true");
                clientHandler.setAlive(false);
            }
            else {
                request.setBody("false");
            }
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleLogin(Request request) {
        String username = request.getParameters().get(0);
        String password = request.getParameters().get(1);
        boolean isAccount =false;
        if(request.getParameters().get(2).equals("true")){
            isAccount=true;
        }
        System.out.println(isAccount);
        if (playerManager.login(username, password, isAccount)) {
            GameState gameState = playerManager.getCurrentGameState();
            gameState.getPlayer().setState("online");
            clientHandler.setGameState(gameState);
            if (!isAccount) {
                gameServer.getPlayers().add(gameState.getPlayer());
            }
            gameServer.changeState(gameState.getPlayer());
            ShopManager shopManager = new ShopManager(clientHandler.getGameState().getPlayer());
            CollectionManager collectionManager = new CollectionManager(clientHandler.getGameState().getPlayer());
            setShopManager(shopManager);
            setCollectionManager(collectionManager);
            clientHandler.setTxtAddress(String.format("src/main/userText/%s.txt", gameState.getPlayer().getUsername() + gameState.getPlayer().getPassword()));

            try {
                String gameStateString = objectMapper.writeValueAsString(clientHandler.getGameState());
                Request request2 = new Request(request.getToken(), "login", new ArrayList<>(), gameStateString);
                request2.setResult(true);
                clientHandler.send(clientHandler.convertRequest(request2));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println("we login player");
        } else {
            Request request2 = new Request(request.getToken(), "login", new ArrayList<>(), "");
            request2.setResult(false);
            clientHandler.send(clientHandler.convertRequest(request));
        }
    }

    void handleToken(Request request) {
        int token = playerManager.createToken();
        request.setToken(token);
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        System.out.println("we send token");
    }

    void handleTraining(Request request){
        clientHandler.setGameState(getObject(GameState.class,request.getBody()));
        gameManager= new GameManager(clientHandler.getGameState());
        gameMaker=new GameMaker(clientHandler.getGameState().getPlayer(),request.getParameters().get(0),request.getParameters().get(1),clientHandler.getGameState(),request.getParameters().get(2));
        gameMaker.buildGameState1();
        gameMaker.buildPassive1();
       setBody(request,clientHandler.getGameState());
        request.setResult(true);
        clientHandler.send(clientHandler.convertRequest(request));
        myLogger(clientHandler.getTxtAddress(), "you start training game" + " " + utilities.time() + "\n", true);
    }

    void handleDrawHand(Request request) {
       handleDrawAction(request);
        clientHandler.send(clientHandler.convertRequest(request));
    }
    void handleDrawAction(Request request){
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());
        if (gamePacket != null) {
            gameManager.drawCardFromHand(gamePacket.getHasTurn(), gamePacket.getNoTurn(), gamePacket.getCard(), gamePacket.getTarget());
            setBody(request, gameManager.getGameState());
            request.setResult(true);
            myLogger(clientHandler.getTxtAddress(), "you draw  a card from hand" + " " + utilities.time() + "\n", true);
        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);
            request.setResult(false);
        }
    }

    void handleAttackWithMinion(Request request) {
      handleAttackMinionAction(request);
        clientHandler.send(clientHandler.convertRequest(request));
    }
    void handleAttackMinionAction(Request request){
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());
        if (gamePacket != null) {
            gameManager.attackWithMinion((Minion) gamePacket.getCard(), gamePacket.getHasTurn(), gamePacket.getNoTurn(), gamePacket.getTarget());
            setBody(request, gameManager.getGameState());
            request.setResult(true);
            myLogger(clientHandler.getTxtAddress(), "you draw  a card from hand" + " " + utilities.time() + "\n", true);
        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);
            request.setResult(false);
        }
    }

    void handleAttackWeapen(Request request) {
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());
        if (gamePacket != null) {
            gameManager.attackWithWeapen(gamePacket.getHasTurn(), gamePacket.getNoTurn(), gamePacket.getTarget());
            setBody(request, gameManager.getGameState());
            request.setResult(true);
            myLogger(clientHandler.getTxtAddress(), "you attack with weapen" + " " + utilities.time() + "\n", true);
        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);
            request.setResult(false);
        }
        clientHandler.send(clientHandler.convertRequest(request));
    }
    void handleTurn(Request request){
        gameManager.nextRound();
        setBody(request,gameManager.getGameState());
        request.setResult(true);
        myLogger(clientHandler.getTxtAddress(), " next round" + " " + utilities.time() + "\n", true);
        clientHandler.send(clientHandler.convertRequest(request));
    }




    public boolean validForGame(Request request){
      int integer =Integer.parseInt(request.getParameters().get(3));
      if (integer<=0){
          return false;
      }
      return true;
    }

    private <A> A getObject(Class<A> aClass, String jsonValue) {
        try {
            return objectMapper.readValue(jsonValue, aClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <A> void setBody(Request request, A Object) {
        try {
            String ObjectString = objectMapper.writeValueAsString(Object);
            request.setBody(ObjectString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> arratToList(String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
        return list;
    }

    public void myLogger(String fileName, String write, boolean append) {
        //lazy
        if (fileWriter!=null){

        }else {
            try {
                fileWriter = new FileWriter(fileName, append);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            fileWriter.write(write);
            fileWriter.flush();
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

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public GameMaker getGameMaker() {
        return gameMaker;
    }

    public void setGameMaker(GameMaker gameMaker) {
        this.gameMaker = gameMaker;
    }
}

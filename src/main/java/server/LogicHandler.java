package server;

import CLI.utilities;
import client.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.CardManager;
import logic.CollectionManager;
import logic.PlayerManager;
import logic.ShopManager;
import model.Request;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//for seprating logic and network layer and third layerd design
public class LogicHandler {

    private ClientHandler clientHandler;
    private PlayerManager playerManager;
    private ShopManager shopManager;
    private CollectionManager collectionManager;
    private GameServer gameServer;
    private CardManager cardManager=new CardManager();
    private ObjectMapper objectMapper =new ObjectMapper();

    public LogicHandler(GameServer gameServer,ClientHandler clientHandler) {
        this.gameServer=gameServer;
        playerManager=new PlayerManager(this.gameServer);
        this.clientHandler=clientHandler;
    }



// due to its only for clientHandler we set it package level;
     void handleBuyable(Request request){
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
     void handleAllCard(Request request){
        ArrayList<String> allCards =getCollectionManager().allCards();
        String allCardString = null;
        try {
            allCardString = objectMapper.writeValueAsString(allCards);
            request.setBody(allCardString);
            request.setResult(true);
            clientHandler.send(clientHandler.convertRequest(request));
           myLogger(clientHandler.getTxtAddress(),"you clicked to see allCards cards  "+ utilities.time()+"\n",true);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

     void handleKnocked(Request request){
         ArrayList<String> knocked =getCollectionManager().knocked();
         String knockedString = null;
         try {
             knockedString = objectMapper.writeValueAsString(knocked);
             request.setBody(knockedString);
             request.setResult(true);
             clientHandler.send(clientHandler.convertRequest(request));
             myLogger(clientHandler.getTxtAddress(),"you clicked to see knocked cards  "+ utilities.time()+"\n",true);

         } catch (JsonProcessingException e) {
             e.printStackTrace();
         }
     }
     void handleShowCost(Request request){
         ArrayList<String> showCost =getCollectionManager().findCost(Integer.parseInt(request.getBody()));
         String costString = null;
         try {
             costString = objectMapper.writeValueAsString(showCost);
             request.setBody(costString);
             request.setResult(true);
             clientHandler.send(clientHandler.convertRequest(request));
             myLogger(clientHandler.getTxtAddress(),"you clicked to see cost of  cards  "+ utilities.time()+"\n",true);

         } catch (JsonProcessingException e) {
             e.printStackTrace();
         }
     }



    public  void myLogger(String fileName, String write, boolean append) {
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

package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import swing.Collection;
import swing.Menu;
import swing.Rank;
import swing.Shop;

import javax.swing.*;
import java.util.ArrayList;

public class ClientLogic {

    private ObjectMapper objectMapper;

    public ClientLogic() {
        objectMapper = new ObjectMapper();
    }

    void receiveDeckButton(Request request) {
        Deck deck = getObject(Deck.class, request.getBody());
        Controller.getInstance().getCollection().getEast().setThings(deck);
        Controller.getInstance().getCollection().setShowButton(Controller.getInstance().getCollection().initButton(request.getParameters()));
        Controller.getInstance().getCollection().getCenter().setShowButton(Controller.getInstance().getCollection().getShowButton());
        Controller.getInstance().getCollection().repaint();
        Controller.getInstance().getCollection().revalidate();
        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "we successfully create DeckButton ", JOptionPane.INFORMATION_MESSAGE);

    }

    void receiveCards(Request request) {
        ArrayList<String> allCards = new ArrayList<>();
        try {
            allCards = objectMapper.readValue(request.getBody(), new TypeReference<ArrayList<String>>() {
            });
            Controller.getInstance().getCollection().setShowButton(Controller.getInstance().getCollection().initButton(allCards));
            Controller.getInstance().getCollection().getCenter().setShowButton(Controller.getInstance().getCollection().getShowButton());
            Controller.getInstance().getCollection().repaint();
            Controller.getInstance().getCollection().revalidate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void receiveLogin(Request request) {
        try {
            GameState gameState = objectMapper.readValue(request.getBody(), GameState.class);
            Controller.getInstance().setGameState(gameState);
            Controller.getInstance().setTxtAddress(String.format("src/main/userText/%s.txt", gameState.getPlayer().getUsername() + gameState.getPlayer().getPassword()));
            setThings();


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void setThings(){
        Menu menu = new Menu(Controller.getInstance().getGameState().getPlayer());
        Shop shop = new Shop();
        Collection collection = new Collection();
        Rank rank = new Rank();
        Controller.getInstance().setMenu(menu);
        Controller.getInstance().setRank(rank);
        Controller.getInstance().setShop(shop);
        Controller.getInstance().setCollection(collection);
        Controller.getInstance().getMyFrame().add(rank,"rank");
        Controller.getInstance().getMyFrame().add(menu, "menu");
        Controller.getInstance().getMyFrame().add(shop, "shop");
        Controller.getInstance().getMyFrame().add(collection, "collection");
        Controller.getInstance().getMyFrame().setPanel("menu");
    }

    void receiveSellable(Request request) {
        ArrayList<String> sellable = new ArrayList<>();
        try {
            sellable = objectMapper.readValue(request.getBody(), new TypeReference<ArrayList<String>>() {
            });
            for (int i = 0; i < Controller.getInstance().getShop().getCenter().getCardPanels().size(); i++) {
                // center.getCardPanels().get(i).remove();
                Controller.getInstance().getShop().getCenter().remove(Controller.getInstance().getShop().getCenter().getCardPanels().get(i));
            }
            Controller.getInstance().getShop().setShowButton(Controller.getInstance().getShop().initButton(sellable));
            Controller.getInstance().getShop().getCenter().setShowButton(Controller.getInstance().getShop().getShowButton());
            Controller.getInstance().getShop().repaint();
            Controller.getInstance().getShop().revalidate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void receiveBuyable(Request request) {
        ArrayList<String> buyable = new ArrayList<>();
        System.out.println("we show buyable");
        try {
            buyable = objectMapper.readValue(request.getBody(), new TypeReference<ArrayList<String>>() {
            });
            for (int i = 0; i < Controller.getInstance().getShop().getCenter().getCardPanels().size(); i++) {
                // center.getCardPanels().get(i).remove();
                Controller.getInstance().getShop().getCenter().remove(Controller.getInstance().getShop().getCenter().getCardPanels().get(i));
            }
            Controller.getInstance().getShop().setShowButton(Controller.getInstance().getShop().initButton(buyable));
            Controller.getInstance().getShop().getCenter().setShowButton(Controller.getInstance().getShop().getShowButton());
            Controller.getInstance().getShop().repaint();
            Controller.getInstance().getShop().revalidate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    void receiveMe(Request request){
        ArrayList<Score> scores=new ArrayList<>();
        try {
            scores=  objectMapper.readValue(request.getBody(),new TypeReference<ArrayList<Score>>(){} );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Controller.getInstance().getRank().setScoresMe(scores);
        Controller.getInstance().getRank().initTableMe();
        Controller.getInstance().getRank().repaint();
        Controller.getInstance().getRank().revalidate();
    }
    void receiveTop10(Request request){
        ArrayList<Score> scores=new ArrayList<>();
        try {
            scores=  objectMapper.readValue(request.getBody(),new TypeReference<ArrayList<Score>>(){} );
            Controller.getInstance().getRank().setScoresTop10(scores);
            Controller.getInstance().getRank().initTop10Table();
            Controller.getInstance().getRank().repaint();
            Controller.getInstance().getRank().revalidate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void receiveDelete(Request request) {
        if (request.getBody().equalsIgnoreCase("true")) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "Error", "wrong password", JOptionPane.ERROR_MESSAGE);
        }
    }

    void recieveBuy(Request request) {
        if (request.getParameters().size() == 0) {
            Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "we successfully buy ", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not buy this card ", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void receiveSell(Request request) {
        if (request.getParameters().size() > 0) {
            Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "we successfully sell ", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not sell this card ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void receiveAdd(Request request) {
        if (request.getBody().equals("false")) {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not add it to this deck", "add to deck error", JOptionPane.ERROR_MESSAGE);
        } else {
            Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "we successfully add to Deck  ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    void receiveRemove(Request request) {
        Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "you remove sth from  Deck maybe it doesnt exist  ", JOptionPane.INFORMATION_MESSAGE);
    }

    void recieveChangeName(Request request) {
        Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
        Controller.getInstance().getCollection().setGraphicOfDeck(request.getParameters().get(0), request.getParameters().get(1));
    }

    void receiveDeckNames(Request request){
Controller.getInstance().getCollection().innerInitDecks(listToArray(request.getParameters()));
    }
    void receiveChangeHero(Request request) {
        if (request.getBody().equals("false")) {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not change its hero ", "error", JOptionPane.ERROR_MESSAGE);
        } else {
            Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
            Controller.getInstance().getCollection().setDecks(new JComboBox(listToArray(request.getParameters())));
            Controller.getInstance().getCollection().repaint();
            Controller.getInstance().getCollection().revalidate();
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "we successfully  change Hero of the Deck  ", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    void receiveBuyCollection(Request request){
        if (request.getParameters().size() >= 1 && request.getParameters().contains("you can not buy")) {
            Controller.getInstance().getBuy().setVisible(false);
            Controller.getInstance().getMyFrame().setPanel("shop");
        } else {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not buy this card ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    void receiveInfoCollection(Request request){
        Controller.getInstance().getCollection().getCenter().getOnclick().getCardPanelCollection().createLabels(request.getParameters().get(0), request.getParameters().get(3), request.getParameters().get(2), Integer.parseInt(request.getParameters().get(1)));
    }
    void receiveInformation(Request request){
        Controller.getInstance().getShop().getCenter().getOnclick().getCardPanel().createLabels(request.getParameters().get(0), request.getParameters().get(3), request.getParameters().get(2), Integer.parseInt(request.getParameters().get(1)));
    }

    void recieveMakeDeck(Request request) {
        Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
        Controller.getInstance().getCollection().setGraphicOfMakeDeck(request.getParameters().get(0));
        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "we make deck " + request.getParameters().get(0), JOptionPane.INFORMATION_MESSAGE);
    }

    void receiveRemoveDeck(Request request) {
        Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
        Controller.getInstance().getCollection().setGraphicRemoveDeck(request.getParameters().get(0));
        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "we remove deck", JOptionPane.INFORMATION_MESSAGE);
    }


    private <A> A getObject(Class<A> aClass, String jsonValue) {
        try {
            return objectMapper.readValue(jsonValue, aClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private ArrayList<String> arratToList(String[]strings){
        ArrayList<String> list=new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
        return list;
    }
    private String[] listToArray(ArrayList<String>list){
        String[] ar= new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ar[i]=list.get(i);
        }
        return ar;
    }
}

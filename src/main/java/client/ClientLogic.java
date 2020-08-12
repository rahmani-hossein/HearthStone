package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Deck;
import model.GameState;
import model.Player;
import model.Request;
import swing.Collection;
import swing.Menu;
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
            Menu menu = new Menu(Controller.getInstance().getGameState().getPlayer());
            Controller.getInstance().setMenu(menu);
            Controller.getInstance().getMyFrame().add(menu, "menu");
            Shop shop = new Shop();
            Controller.getInstance().setShop(shop);
            Collection collection = new Collection();
            Controller.getInstance().setCollection(collection);
            Controller.getInstance().getMyFrame().add(shop, "shop");
            Controller.getInstance().getMyFrame().add(collection, "collection");
            Controller.getInstance().getMyFrame().setPanel("menu");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
Controller.getInstance().getCollection().innerInitDecks(request.getParameters());
    }
    void receiveChangeHero(Request request) {
        if (request.getBody().equals("false")) {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not change its hero ", "error", JOptionPane.ERROR_MESSAGE);
        } else {
            Controller.getInstance().getGameState().setPlayer(getObject(Player.class, request.getBody()));
            Controller.getInstance().getCollection().setDecks(new JComboBox((ComboBoxModel) request.getParameters()));
            Controller.getInstance().getCollection().repaint();
            Controller.getInstance().getCollection().revalidate();
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "info", "we successfully  change Hero of the Deck  ", JOptionPane.INFORMATION_MESSAGE);
        }

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
}

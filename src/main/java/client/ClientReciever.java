package client;

import CLI.utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GameState;
import model.Player;
import model.Request;
import swing.Collection;
import swing.GamePanel;
import swing.Menu;
import swing.Shop;


import javax.swing.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientReciever extends Thread {

    private InputStream inputStream;
    private PrintStream printStream;
    private Client client;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ClientLogic clientLogic;

    ClientReciever(InputStream inputStream, PrintStream printStream, Client client) {
        this.inputStream = inputStream;
        this.printStream = printStream;
        this.client = client;
        clientLogic = new ClientLogic();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(inputStream);

        while (!isInterrupted()) {

            String message = scanner.nextLine();

            try {
                Request request = objectMapper.readValue(message, Request.class);
                executeRequest(request);
                System.out.println("Received: " + message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //  printStream.println(message);

        }
    }

    public void executeRequest(Request request) {
        if (request.isResult()) {
            switch (request.getName()) {
                case "token":
                    client.setToken(request.getToken());
                    System.out.println("we execute successfully token request");
                    break;
                case "login":
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
                    break;

                case "logout":
                    System.exit(0);
                    break;
                case "delete":
                    if (request.getBody().equalsIgnoreCase("true")) {
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "Error", "wrong password", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "move":
                    GameState myGameState = getObject(GameState.class, request.getBody());

                    Controller.getInstance().getGamePanel().repaint();
                    Controller.getInstance().getGamePanel().revalidate();
                    System.out.println("after repainting");

                    break;
                case "waiting":
                    Controller.getInstance().setHaveOpponent(true);
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "wait for enother client ", "play waiting", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "play":
                    GameState gameState = getObject(GameState.class, request.getBody());
                    if (gameState != null) {
                        Controller.getInstance().setGameState(gameState);
                        // Controller.getInstance().getMyFrame().getLock().notify();
                        GamePanel gamePanel = new GamePanel(Controller.getInstance().getClientConstants().getPanelWidth(), Controller.getInstance().getClientConstants().getPanelHeight(), Controller.getInstance().getGameState());
                        Controller.getInstance().setGamePanel(gamePanel);
                        Controller.getInstance().getMyFrame().getMainpanel().add(gamePanel, "gamepanel");
                        Controller.getInstance().getMyFrame().setPanel("gamepanel");
                    } else {
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "error getting gamestate", "networkError", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "sellable":
                    // System.out.println(request.getBody());
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
                    break;
                case "buyable":
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
                    break;
                case "allCard":
                case "knocked":
                    ArrayList<String> allCards = new ArrayList<>();
                    System.out.println("we show allCards");
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
                    break;
                case
                case "buy":
                    clientLogic.recieveBuy(request);
                    break;
                case "sell":
                    clientLogic.receiveSell(request);
                    break;
                case "information":

                    Controller.getInstance().getShop().getCenter().getOnclick().getCardPanel().createLabels(request.getParameters().get(0), request.getParameters().get(3), request.getParameters().get(2), Integer.parseInt(request.getParameters().get(1)));
                    break;
                case "infoCollection":
                    Controller.getInstance().getCollection().getCenter().getOnclick().getCardPanelCollection().createLabels(request.getParameters().get(0), request.getParameters().get(3), request.getParameters().get(2), Integer.parseInt(request.getParameters().get(1)));
                    break;
                case "buyCollection":
                    if (request.getParameters().size() >= 1 && request.getParameters().contains("you can not buy")) {
                        Controller.getInstance().getBuy().setVisible(false);
                        Controller.getInstance().getMyFrame().setPanel("shop");
                    } else {
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "you can not buy this card ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

            }
        } else {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "error of recieved request result is false", "result", JOptionPane.ERROR_MESSAGE);
        }

    }

    private <A> A getObject(Class<A> aClass, String jsonValue) {
        try {
            return objectMapper.readValue(jsonValue, aClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

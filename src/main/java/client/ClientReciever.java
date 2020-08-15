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
import swing.Listener.OnlineListener;
import swing.Listener.TrainingListener;
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
                    clientLogic.receiveLogin(request);
                    break;
                case "logout":
                    Controller.getInstance().exitDirectly();
                    System.exit(0);
                    break;
                case "delete":
                    clientLogic.receiveDelete(request);
                    break;

                case "waiting":

                        Controller.getInstance().setHaveOpponent(true);
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "wait for another client ", "play waiting", JOptionPane.INFORMATION_MESSAGE);

                    break;
                case "notValid":
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), " you are not allowed", "error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "online":
                    GameState gameState = getObject(GameState.class, request.getBody());
                    if (gameState != null) {
                        Controller.getInstance().setGameState(gameState);
                        OnlineListener onlineListener=new OnlineListener();
                        GamePanel gamePanel = new GamePanel(Controller.getInstance().getClientConstants().getPanelWidth(), Controller.getInstance().getClientConstants().getPanelHeight(), Controller.getInstance().getGameState(),onlineListener);
                        onlineListener.setGamePanel(gamePanel);
                        Controller.getInstance().setGamePanel(gamePanel);
                        Controller.getInstance().getMyFrame().getMainpanel().add(gamePanel, "gamepanel");
                        Controller.getInstance().getMyFrame().setPanel("gamepanel");
                    } else {
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "error getting gamestate", "networkError", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "training":
                    GameState gameState1 = getObject(GameState.class, request.getBody());
                    if (gameState1 != null) {
                        Controller.getInstance().setGameState(gameState1);
                       TrainingListener trainingListener= new TrainingListener();
                        GamePanel gamePanel = new GamePanel(Controller.getInstance().getClientConstants().getPanelWidth(), Controller.getInstance().getClientConstants().getPanelHeight(), Controller.getInstance().getGameState(),trainingListener);
                        trainingListener.setGamePanel(gamePanel);
                        Controller.getInstance().setGamePanel(gamePanel);
                        Controller.getInstance().getMyFrame().getMainpanel().add(gamePanel, "gamepanel");
                        Controller.getInstance().getMyFrame().setPanel("gamepanel");
                    } else {
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "error getting gamestate", "networkError", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "drawHand":
                case "attackWithMinion":
                case "attackWeapen":
                case "turn":
                    GameState gameState2= getObject(GameState.class, request.getBody());
                    if (gameState2 != null) {
                        Controller.getInstance().setGameState(gameState2);
                       Controller.getInstance().getGamePanel().exclusiveRepaint();
                    } else {
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "error getting gamestate", "networkError", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "sellable":
                    clientLogic.receiveSellable(request);
                    break;
                case "buyable":
                    clientLogic.receiveBuyable(request);
                    break;
                case "allCard":
                case "knocked":
                case "showCost":
                case "showClass":
                case "search":
                    clientLogic.receiveCards(request);
                    break;
                case "buy":
                    clientLogic.recieveBuy(request);
                    break;
                case "me":
                    clientLogic.receiveMe(request);
                    break;
                case "top10":
                    clientLogic.receiveTop10(request);
                    break;
                case "sell":
                    clientLogic.receiveSell(request);
                    break;
                case "add":
                    clientLogic.receiveAdd(request);
                    break;
                case "remove":
                    clientLogic.receiveRemove(request);
                    break;
                case "changeName":
                    clientLogic.recieveChangeName(request);
                    break;
                case "changeHero":
                    clientLogic.receiveChangeHero(request);
                    break;
                case "makeDeck":
                    clientLogic.recieveMakeDeck(request);
                    break;
                case "removeDeck":
                    clientLogic.receiveRemoveDeck(request);
                    break;
                case "deckButton":
                    clientLogic.receiveDeckButton(request);
                    break;
                case "deckNames":
                    clientLogic.receiveDeckNames(request);
                    break;
                case "information":
                    clientLogic.receiveInformation(request);
                    break;
                case "infoCollection":
                    clientLogic.receiveInfoCollection(request);
                    break;
                case "buyCollection":
                    clientLogic.receiveBuyCollection(request);
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

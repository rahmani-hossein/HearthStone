package server;

import CLI.utilities;
import client.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.CollectionManager;
import logic.PlayerManager;
import logic.ShopManager;
import model.GameState;
import model.Player;
import model.Request;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private boolean alive = true;
    private Socket socket;
    private GameServer gameServer;
    private String clientName;
    private PrintStream printer;
    private ObjectMapper objectMapper = new ObjectMapper();
    private LogicHandler logicHandler;
    private GameState gameState;
    private String txtAddress;

    ClientHandler(GameServer gameServer, Socket socket) {
        this.gameServer = gameServer;
        this.socket = socket;
        clientName = socket.getRemoteSocketAddress().toString();
        logicHandler = new LogicHandler(this.gameServer,this);
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            printer = new PrintStream(socket.getOutputStream());

            while (alive) {
                String message = scanner.nextLine();
                Request request = getRequest(message);
                executeRequestServer(request);
                System.out.println("we receive a request");
                //  System.out.println("Client at " + socket.getRemoteSocketAddress().toString() + " sent " + message + ".");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Request getRequest(String message) {
        try {
            return objectMapper.readValue(message, Request.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void send(String message) {
        printer.println(message);
    }

    private void executeRequestServer(Request request) {

            switch (request.getName()) {

                case "token":
                    int token = logicHandler.getPlayerManager().createToken();
                    request.setToken(token);
                    request.setResult(true);
                    send(convertRequest(request));
                    System.out.println("we send token");
                    break;
                case "login":
                    String username = request.getParameters().get(0);
                    String password = request.getParameters().get(1);
                    boolean isAccount =false;
                    if(request.getParameters().get(2).equals("true")){
                        isAccount=true;
                }
                    System.out.println(isAccount);
                    if (logicHandler.getPlayerManager().login(username, password, isAccount)) {
                        GameState gameState = logicHandler.getPlayerManager().getCurrentGameState();
                        this.gameState = gameState;
                        if (!isAccount) {
                            gameServer.getPlayers().add(gameState.getPlayer());
                        }
                        gameServer.changeState(gameState.getPlayer());
                        ShopManager shopManager = new ShopManager(this.gameState.getPlayer());
                        CollectionManager collectionManager = new CollectionManager(this.gameState.getPlayer());
                        logicHandler.setShopManager(shopManager);
                        logicHandler.setCollectionManager(collectionManager);
                        txtAddress = String.format("src/main/userText/%s.txt", gameState.getPlayer().getUsername() + gameState.getPlayer().getPassword());

                        try {
                            String gameStateString = objectMapper.writeValueAsString(this.gameState);
                            Request request2 = new Request(request.getToken(), "login", new ArrayList<>(), gameStateString);
                            request2.setResult(true);
                            send(convertRequest(request2));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        System.out.println("we login player");
                    } else {
                        Request request2 = new Request(request.getToken(), "login", new ArrayList<>(), "");
                        request2.setResult(false);
                        send(convertRequest(request));
                    }
                    break;
                case "delete":
                    try {
                        String givenPassword = request.getParameters().get(0);
                        Player player = objectMapper.readValue(request.getBody(), Player.class);
                        gameServer.changeState(player);
                        if (logicHandler.getPlayerManager().delete(givenPassword,player)){
                            request.setBody("true");
                            alive = false;
                        }
                        else {
                            request.setBody("false");
                        }
                        request.setResult(true);
                        send(convertRequest(request));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "logout":
                    try {
                        Player player = objectMapper.readValue(request.getBody(), Player.class);
                        gameServer.changeState(player);
                        logicHandler.getPlayerManager().exit(player);
                        request.setResult(true);
                        alive = false;
                        send(convertRequest(request));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "sellable":
                    ArrayList<String> listSell = logicHandler.getShopManager().showSellable();
                    String scoreBoardString = null;
                    try {
                        scoreBoardString = objectMapper.writeValueAsString(listSell);
                        request.setBody(scoreBoardString);
                        request.setResult(true);
                        send(convertRequest(request));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    break;
                case "buyable":
                   logicHandler.handleBuyable(request);
                    break;
                case "allCard":
                  logicHandler.handleAllCard(request);
                    break;
                case "knocked":
                    logicHandler.handleKnocked(request);
                    break;
                case "showCost":
                    logicHandler.handleShowCost(request);
                    break;
                case "showClass":
                    logicHandler.handleShowClass(request);
                    break;
                case "search":
                    logicHandler.handleSearch(request);
                    break;
                case "add":
                    logicHandler.handleAdd(request);
                    break;
                case "remove":
                    logicHandler.handleRemove(request);
                    break;
                case "changeName":
                    logicHandler.handleChangeName(request);
                    break;
                case "changeHero":
                    logicHandler.handleChangeHero(request);
                    break;
                case "makeDeck":
                    logicHandler.handleMakeDeck(request);
                    break;
                case "removeDeck":
                    logicHandler.handleRemoveDeck(request);
                    break;
                case "deckButton":
                    logicHandler.handleDeckButton(request);
                    break;
                case "deckNames":
                    logicHandler.handleDeckNames(request);
                    break;
                case "buy":
                    String buyName = request.getBody();
                    if (logicHandler.getShopManager().canBuy(buyName)) {

                        logicHandler.getShopManager().buy(buyName);
                        try {
                            String playerString = objectMapper.writeValueAsString(gameState.getPlayer());
                            request.setBody(playerString);
                            request.setResult(true);
                            send(convertRequest(request));
                            logicHandler.myLogger(txtAddress, "you buy " + buyName + " " + utilities.time() + "\n", true);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ArrayList<String> parameters = new ArrayList<>();
                        parameters.add("you can not buy");
                        request.setParameters(parameters);
                        request.setResult(true);
                        send(convertRequest(request));
                        logicHandler.myLogger(txtAddress, "you can not buy " + buyName + " " + utilities.time() + "\n", true);
                    }
                    break;
                case "sell":
                    String name = request.getBody();
                    if (logicHandler.getShopManager().canSell(name)) {

                        logicHandler.getShopManager().sell(name);
                        try {
                            String playerString = objectMapper.writeValueAsString(gameState.getPlayer());
                            request.setBody(playerString);
                            request.setResult(true);
                            send(convertRequest(request));
                            logicHandler.myLogger(txtAddress, "you sell " + name + " " + utilities.time() + "\n", true);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ArrayList<String> parameters = new ArrayList<>();
                        parameters.add("you can not sell");
                        request.setParameters(parameters);
                        request.setResult(true);
                        send(convertRequest(request));
                        logicHandler.myLogger(txtAddress, "you can not sell " + name + " " + utilities.time() + "\n", true);
                    }
                    break;
                case "information":
                    ArrayList<String> infoForHandle = new ArrayList<>();
                    String cardName = request.getBody();
                    infoForHandle.add(cardName);
                    infoForHandle.add("" + gameServer.getServerConstants().getCostsMap().get(cardName));
                    infoForHandle.add(gameServer.getServerConstants().getTypes().get(cardName));
                    infoForHandle.add(logicHandler.getCardManager().tellRarity(cardName));
                    request.setParameters(infoForHandle);
                    request.setResult(true);
                    send(convertRequest(request));
                    break;

                case "infoCollection":
                    ArrayList<String> info = new ArrayList<>();
                    cardName = request.getBody();
                    info.add(cardName);
                    info.add("" + gameServer.getServerConstants().getCostsMap().get(cardName));
                    info.add(gameServer.getServerConstants().getTypes().get(cardName));
                    info.add(logicHandler.getCardManager().tellRarity(cardName));
                    request.setParameters(info);
                    request.setResult(true);
                    send(convertRequest(request));
                    break;
                case "buyCollection":
                    String buyNameCollection = request.getBody();
                    if (logicHandler.getShopManager().canBuy(buyNameCollection)) {
                        request.setBody("true");
                        request.setResult(true);
                        send(convertRequest(request));
                        logicHandler.myLogger(txtAddress, "you can  buy " + buyNameCollection + "  go to shop " + utilities.time() + "\n", true);

                    } else {
                        ArrayList<String> parameters = new ArrayList<>();
                        parameters.add("you can not buy");
                        request.setParameters(parameters);
                        request.setResult(true);
                        send(convertRequest(request));
                        logicHandler.myLogger(txtAddress, "you can not buy " + buyNameCollection + " " + utilities.time() + "\n", true);
                    }
                    break;
                case "top10":
                    logicHandler.handleTop10(request);
                    break;
                case "me":
                    logicHandler.handleMe(request);
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

    // package level for using inside of logic handler for separate network layer with logic layer;
    String convertRequest(Request request) {
        try {
            String message = objectMapper.writeValueAsString(request);
            return message;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public GameServer getGameServer() {
        return gameServer;
    }

    public void setGameServer(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public PrintStream getPrinter() {
        return printer;
    }

    public void setPrinter(PrintStream printer) {
        this.printer = printer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }
}

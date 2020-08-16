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
    private Game game;
    private String clientName;
    private PrintStream printer;
    private ObjectMapper objectMapper = new ObjectMapper();
    private LogicHandler logicHandler;
    private GameState gameState;
    private boolean acceptPlay;
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
                   logicHandler.handleToken(request);
                    break;
                case "login":
                 logicHandler.handleLogin(request);
                    break;
                case "delete":
                  logicHandler.handleDelete(request);
                    break;
                case "logout":
                   logicHandler.handleLogout(request);
                    break;
                case "online":
                    if (game!=null){
                        //nothing to do
                    }
                    else {
                        acceptPlay=true;
                        checkGame(request);
                    }
                    break;
                case "training":
                    logicHandler.handleTraining(request);
                    System.out.println("after training");
                    break;

                case "playPanel":
                    logicHandler.getGameManager().setGameState(getObject(GameState.class,request.getBody()));
                    logicHandler.setBody(request,logicHandler.getGameManager().getGameState());
                    request.setResult(true);
                    send(convertRequest(request));

                    break;
                case "turn":
                    if (game!=null){
                        game.handleTurn(request);
                    }
                    else {//training
                        logicHandler.handleTurn(request);
                    }
                    break;
                case "drawHand":
                    if (game!=null){
                        game.handleDrawHand(request);
                    }
                    else {// training
                        logicHandler.handleDrawHand(request);
                    }
                    break;
                case "attackWithMinion":
                    if (game!=null){
                        game.handleAttackWithMinion(request);
                    }
                    else {//training
                        logicHandler.handleAttackWithMinion(request);
                    }
                    break;
                case "attackWeapen":
                    if (game!=null){
                        game.handleAttackWeapen(request);
                    }
                    else {
                        logicHandler.handleAttackWeapen(request);
                    }
                    break;
                case "sellable":
                  logicHandler.handleSellable(request);
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
                  logicHandler.handleBuy(request);
                    break;
                case "sell":
                   logicHandler.handleSell(request);
                    break;
                case "information":
                   logicHandler.handleInformation(request);
                    break;

                case "infoCollection":
                   logicHandler.handleInfoCollection(request);
                    break;
                case "buyCollection":
                   logicHandler.handleBuyCollection(request);
                    break;
                case "top10":
                    logicHandler.handleTop10(request);
                    break;
                case "me":
                    logicHandler.handleMe(request);
            }


    }
    private void checkGame(Request request) {
        try {
            gameServer.getSemaphore().acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (logicHandler.validForGame(request)) {
            gameServer.getAcceptForPlay().add(this);
            if (gameServer.getAcceptForPlay().size() == 2) {
                ClientHandler waited = gameServer.getAcceptForPlay().remove(0);
                request.setResult(true);
                Game game = new Game(waited, gameServer.getAcceptForPlay().remove(0), request, gameServer.getUnhandled(), gameServer);
                this.game = game;
                waited.setGame(game);
                gameServer.getAvailableGames().add(game);
            }
            if (gameServer.getAcceptForPlay().size() == 1) {

                Request request1 = new Request(request.getToken(), "waiting", request.getParameters(), request.getBody());
                request1.setResult(true);
                gameServer.setUnhandled(request);
                send(convertRequest(request1));
            }
        }
        else {
            Request request1 = new Request(request.getToken(), "notValid", null, request.getBody());
            request1.setResult(true);
            send(convertRequest(request1));
        }
        gameServer.getSemaphore().release();
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAcceptPlay() {
        return acceptPlay;
    }

    public void setAcceptPlay(boolean acceptPlay) {
        this.acceptPlay = acceptPlay;
    }

    public LogicHandler getLogicHandler() {
        return logicHandler;
    }

    public void setLogicHandler(LogicHandler logicHandler) {
        this.logicHandler = logicHandler;
    }
}

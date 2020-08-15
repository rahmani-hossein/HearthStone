package server;

import CLI.GameMaker;
import CLI.utilities;
import client.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.GameManager;
import model.GamePacket;
import model.GameState;
import model.Minion;
import model.Request;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game {

    private FileWriter fileWriter;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ClientHandler clientHandler1;
    private ClientHandler clientHandler2;
    private GameManager gameManager1;
    private GameManager gameManager2;
    private GameServer gameServer;
    private int token1 = 0;
    private int token2 = 0;


    public Game(ClientHandler clientHandler1, ClientHandler clientHandler2, Request num2, Request unhandled, GameServer gameServer) {
        this.clientHandler1 = clientHandler1;
        this.clientHandler2 = clientHandler2;
        this.gameServer = gameServer;

        init(num2, unhandled);
        sendStart();
    }

    private void init(Request num2, Request unhandled) {
        clientHandler1.setGameState(getObject(GameState.class, unhandled.getBody()));
        clientHandler2.setGameState(getObject(GameState.class, num2.getBody()));

        if (clientHandler1.getGameState() != null && clientHandler2.getGameState() != null) {
            GameMaker gameMaker = new GameMaker(clientHandler1.getGameState().getPlayer(), unhandled.getParameters().get(0), unhandled.getParameters().get(1), clientHandler1.getGameState(), unhandled.getParameters().get(2),
                    clientHandler2.getGameState().getPlayer(), num2.getParameters().get(0), num2.getParameters().get(1), clientHandler2.getGameState(), num2.getParameters().get(2));
            gameMaker.buildOnlineGameState();
            gameManager1 = new GameManager(clientHandler1.getGameState());
            gameManager2 = new GameManager(clientHandler2.getGameState());
            clientHandler1.getGameState().setTurn(false);
            clientHandler2.getGameState().setTurn(true);
            token1 = unhandled.getToken();
            token2 = num2.getToken();
        } else {
            System.out.println("bad thing happend");
        }

    }

    private void sendStart() {
        Request request1 = new Request(token1, "online", null, jsonString(clientHandler1.getGameState()));
        request1.setResult(true);
        Request request2 = new Request(token2, "online", null, jsonString(clientHandler2.getGameState()));
        request2.setResult(true);
        sendToClients(request1, request2);
    }

    void handleTurn(Request request){
        if (request.getToken()==token1){
            gameManager1.nextRound();
            updateGameStates(clientHandler1, clientHandler2);

        }
        else if (request.getToken()==token2){
            gameManager2.nextRound();
            updateGameStates(clientHandler2, clientHandler1);
        }
        createRequests("turn");
    }

    void handleDrawHand(Request request) {
        if (request.getToken() == token1) {
            handlerDrawHand(gameManager1, clientHandler1, request);
            updateGameStates(clientHandler1, clientHandler2);
        } else if (request.getToken() == token2) {
            handlerDrawHand(gameManager2, clientHandler2, request);
            updateGameStates(clientHandler2, clientHandler1);
        }
        createRequests("drawHand");

    }

    void handleAttackWithMinion(Request request){
        if (request.getToken() == token1) {
            handlerAttackWithMinion(gameManager1, clientHandler1, request);
            updateGameStates(clientHandler1, clientHandler2);
        } else if (request.getToken() == token2) {
            handlerAttackWithMinion(gameManager2, clientHandler2, request);
            updateGameStates(clientHandler2, clientHandler1);
        }
        createRequests("attackWithMinion");
    }
    void handleAttackWeapen(Request request){
        if (request.getToken() == token1) {
            handlerAttackWeapen(gameManager1, clientHandler1, request);
            updateGameStates(clientHandler1, clientHandler2);
        } else if (request.getToken() == token2) {
            handlerAttackWeapen(gameManager2, clientHandler2, request);
            updateGameStates(clientHandler2, clientHandler1);
        }
        createRequests("attackWeapen");
    }

    private void handlerAttackWithMinion(GameManager gameManager, ClientHandler clientHandler, Request request) {
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());
        if (gamePacket != null) {
            gameManager.attackWithMinion((Minion) gamePacket.getCard(), gamePacket.getHasTurn(), gamePacket.getNoTurn(), gamePacket.getTarget());
            myLogger(clientHandler.getTxtAddress(), "you attackWithMinion" + " " + utilities.time() + "\n", true);

        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);

        }
    }
    private void handlerAttackWeapen(GameManager gameManager, ClientHandler clientHandler, Request request) {
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());
        if (gamePacket != null) {
            gameManager.attackWithWeapen(gamePacket.getHasTurn(), gamePacket.getNoTurn(), gamePacket.getTarget());
            myLogger(clientHandler.getTxtAddress(), "you attack with weapen" + " " + utilities.time() + "\n", true);

        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);

        }
    }
    private void handlerDrawHand(GameManager gameManager, ClientHandler clientHandler, Request request) {
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());
        if (gamePacket != null) {
            gameManager.drawCardFromHand(gamePacket.getHasTurn(), gamePacket.getNoTurn(), gamePacket.getCard(), gamePacket.getTarget());
            myLogger(clientHandler.getTxtAddress(), "you draw  a card from hand" + " " + utilities.time() + "\n", true);

        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);

        }
    }

    private void updateGameStates(ClientHandler hasRequest, ClientHandler noRequest) {
        noRequest.getGameState().setFreind(hasRequest.getGameState().getEnemy());
        noRequest.getGameState().setEnemy(hasRequest.getGameState().getFreind());
    }

    private void createRequests(String name) {
        Request request1 = new Request(token1, name, null, jsonString(clientHandler1.getGameState()));
        Request request2 = new Request(token2, name, null, jsonString(clientHandler2.getGameState()));
        request1.setResult(true);
        request2.setResult(true);
        sendToClients(request1, request2);
    }

    private void sendToClients(Request request1, Request request2) {
        clientHandler1.send(convertRequest(request1));
        clientHandler2.send(convertRequest(request2));
    }

    private <A> String jsonString(A object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
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

    private String convertRequest(Request request) {
        try {
            String message = objectMapper.writeValueAsString(request);
            return message;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void myLogger(String fileName, String write, boolean append) {
        //lazy
        if (fileWriter != null) {

        } else {
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

    public ClientHandler getClientHandler1() {
        return clientHandler1;
    }

    public void setClientHandler1(ClientHandler clientHandler1) {
        this.clientHandler1 = clientHandler1;
    }

    public ClientHandler getClientHandler2() {
        return clientHandler2;
    }

    public void setClientHandler2(ClientHandler clientHandler2) {
        this.clientHandler2 = clientHandler2;
    }

    public GameServer getGameServer() {
        return gameServer;
    }

    public void setGameServer(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    public GameManager getGameManager1() {
        return gameManager1;
    }

    public void setGameManager1(GameManager gameManager1) {
        this.gameManager1 = gameManager1;
    }

    public GameManager getGameManager2() {
        return gameManager2;
    }

    public void setGameManager2(GameManager gameManager2) {
        this.gameManager2 = gameManager2;
    }
}



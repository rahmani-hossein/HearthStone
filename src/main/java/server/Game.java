package server;

import CLI.GameMaker;
import CLI.utilities;
import Interfaces.Attackable;
import client.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.GameManager;
import model.*;

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
            gameManager1.setTxtAddress(clientHandler1.getTxtAddress());
            gameManager2 = new GameManager(clientHandler2.getGameState());
            gameManager2.setTxtAddress(clientHandler2.getTxtAddress());
            gameManager1.getGameState().setTurn(false);
            gameManager2.getGameState().setTurn(true);
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

    void handleTurn(Request request) {
        if (request.getToken() == token1) {
            gameManager1.nextRound();
            updateGameStates(gameManager1, gameManager2);
            gameManager2.getGameState().setTurn(!gameManager2.getGameState().isTurn());
        } else if (request.getToken() == token2) {
            gameManager2.nextRound();
            updateGameStates(gameManager2, gameManager1);
            gameManager1.getGameState().setTurn(!gameManager1.getGameState().isTurn());
        }
        createRequests("turn");
    }

    void handleDrawHand(Request request) {
        if (request.getToken() == token1) {
            handlerDrawHand(gameManager1, clientHandler1, request);
            updateGameStates(gameManager1, gameManager2);
        } else if (request.getToken() == token2) {
            handlerDrawHand(gameManager2, clientHandler2, request);
            updateGameStates(gameManager2, gameManager1);
        }
        createRequests("drawHand");

    }

    void handleAttackWithMinion(Request request) {
        if (request.getToken() == token1) {
            handlerAttackWithMinion(gameManager1, clientHandler1, request);
            updateGameStates(gameManager1, gameManager2);
        } else if (request.getToken() == token2) {
            handlerAttackWithMinion(gameManager2, clientHandler2, request);
            updateGameStates(gameManager2, gameManager1);
        }
        createRequests("attackWithMinion");
    }

    void handleAttackWeapen(Request request) {
        if (request.getToken() == token1) {
            handlerAttackWeapen(gameManager1, clientHandler1, request);
            updateGameStates(gameManager1, gameManager2);
        } else if (request.getToken() == token2) {
            handlerAttackWeapen(gameManager2, clientHandler2, request);
            updateGameStates(gameManager2, gameManager1);
        }
        createRequests("attackWeapen");
    }


    private void handlerAttackWithMinion(GameManager gameManager, ClientHandler clientHandler, Request request) {
        gameManager.setGameState(getObject(GameState.class, request.getParameters().get(0)));
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());
        if (gamePacket != null) {
            int nobat=getNobat(gamePacket.getHasTurn(),gameManager);
            gameManager.attackWithMinion((Minion) getCard((Minion) gamePacket.getCard(),nobat,gameManager), getGamePlayer(gamePacket.getHasTurn(),gameManager),getGamePlayer( gamePacket.getNoTurn(),gameManager), getTarget(gamePacket.getTarget(),nobat,gameManager));

            myLogger(clientHandler.getTxtAddress(), "you attackWithMinion" + " " + utilities.time() + "\n", true);

        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);

        }
    }

    private void handlerAttackWeapen(GameManager gameManager, ClientHandler clientHandler, Request request) {
        gameManager.setGameState(getObject(GameState.class, request.getParameters().get(0)));
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());

        if (gamePacket != null) {
            int nobat=getNobat(gamePacket.getHasTurn(),gameManager);
            gameManager.attackWithWeapen(getGamePlayer(gamePacket.getHasTurn(),gameManager), getGamePlayer(gamePacket.getNoTurn(),gameManager), getTarget(gamePacket.getTarget(),nobat,gameManager));
            myLogger(clientHandler.getTxtAddress(), "you attack with weapen" + " " + utilities.time() + "\n", true);

        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);

        }
    }

    private void handlerDrawHand(GameManager gameManager, ClientHandler clientHandler, Request request) {
        gameManager.setGameState(getObject(GameState.class, request.getParameters().get(0)));
        GamePacket gamePacket = getObject(GamePacket.class, request.getBody());
        if (gamePacket != null) {
            int nobat=getNobat(gamePacket.getHasTurn(),gameManager);
            gameManager.drawCardFromHand(getGamePlayer(gamePacket.getHasTurn(),gameManager), getGamePlayer(gamePacket.getNoTurn(),gameManager), getCard(gamePacket.getCard(),nobat,gameManager), getTarget(gamePacket.getTarget(),nobat,gameManager));
            myLogger(clientHandler.getTxtAddress(), "you draw  a card from hand" + " " + utilities.time() + "\n", true);

        } else {
            myLogger(clientHandler.getTxtAddress(), "null pointer in receiving " + " " + utilities.time() + "\n", true);

        }
    }

    private void updateGameStates(GameManager hasRequest, GameManager noRequest) {
        noRequest.getGameState().setFreind(hasRequest.getGameState().getEnemy());
        noRequest.getGameState().setEnemy(hasRequest.getGameState().getFreind());
    }

    private void createRequests(String name) {
        Request request1 = new Request(token1, name, null, jsonString(gameManager1.getGameState()));
        Request request2 = new Request(token2, name, null, jsonString(gameManager2.getGameState()));
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

            try {
                fileWriter = new FileWriter(fileName, append);
                fileWriter.write(write);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    GamePlayer getGamePlayer(GamePlayer gamePlayer,GameManager gameManager) {
        if (gamePlayer.getNameOfPlayer().equalsIgnoreCase(gameManager.getGameState().getFreind().getNameOfPlayer())) {
            return gameManager.getGameState().getFreind();
        }
        if (gamePlayer.getNameOfPlayer().equalsIgnoreCase(gameManager.getGameState().getEnemy().getNameOfPlayer())) {
            return gameManager.getGameState().getEnemy();
        }
        return null;
    }

    int getNobat(GamePlayer gamePlayer,GameManager gameManager) {
        if (gamePlayer.getNameOfPlayer().equalsIgnoreCase(gameManager.getGameState().getFreind().getNameOfPlayer())) {
            return 1;
        }
//        else {
//            (gamePlayer.getNameOfPlayer().equalsIgnoreCase(gameManager.getGameState().getEnemy().getNameOfPlayer()))
//        }
        return 2;

    }
    Attackable getTarget(Attackable target, int  nobat,GameManager gameManager){
        if (target!=null&&nobat>0){
            if (nobat==1){//nobat friend hast
                if (target instanceof Hero){
                    return gameManager.getGameState().getEnemy().getHero();
                }
                else if (target instanceof Minion) {
                    if (gameManager.getGameState().getEnemy().getGround()!=null) {
                        for (Minion minion : gameManager.getGameState().getEnemy().getGround()) {
                            if (((Minion) target).getName().equalsIgnoreCase(minion.getName()) && ((Minion) target).getManaCost() == minion.getManaCost() &&
                                    ((Minion) target).getLiveInRound() == minion.getLiveInRound() && ((Minion) target).getDamage() == minion.getDamage() &&
                                    (((Minion) target).getHealth() == minion.getHealth())) {
                                return minion;
                            }
                        }
                    }
                    if (gameManager.getGameState().getFreind().getGround()!=null) {
                        for (Minion minion : gameManager.getGameState().getFreind().getGround()) {
                            if (((Minion) target).getName().equalsIgnoreCase(minion.getName()) && ((Minion) target).getManaCost() == minion.getManaCost() &&
                                    ((Minion) target).getLiveInRound() == minion.getLiveInRound() && ((Minion) target).getDamage() == minion.getDamage() &&
                                    (((Minion) target).getHealth() == minion.getHealth())) {
                                return minion;
                            }
                        }
                    }
                    if(gameManager.getGameState().getEnemy().getHand()!=null) {
                        for (card card : gameManager.getGameState().getEnemy().getHand()) {
                            if (card instanceof Minion) {
                                if (((Minion) target).getName().equalsIgnoreCase(card.getName()) && ((Minion) target).getManaCost() == card.getManaCost() &&
                                        ((Minion) target).getLiveInRound() == ((Minion) card).getLiveInRound() && ((Minion) target).getDamage() == ((Minion) card).getDamage() &&
                                        (((Minion) target).getHealth() == ((Minion) card).getHealth())) {
                                    return (Attackable) card;
                                }
                            }
                        }
                    }
                }
            }
            else {//nobat enemy
                if (target instanceof Hero){
                    return gameManager.getGameState().getFreind().getHero();
                }
                else if (target instanceof Minion) {
                    if (gameManager.getGameState().getFreind().getGround()!=null) {
                        for (Minion minion : gameManager.getGameState().getFreind().getGround()) {
                            if (((Minion) target).getName().equalsIgnoreCase(minion.getName()) && ((Minion) target).getManaCost() == minion.getManaCost() &&
                                    ((Minion) target).getLiveInRound() == minion.getLiveInRound() && ((Minion) target).getDamage() == minion.getDamage() &&
                                    (((Minion) target).getHealth() == minion.getHealth())) {
                                return minion;
                            }
                        }
                    }
                    if (gameManager.getGameState().getEnemy().getGround()!=null) {
                        for (Minion minion : gameManager.getGameState().getEnemy().getGround()) {
                            if (((Minion) target).getName().equalsIgnoreCase(minion.getName()) && ((Minion) target).getManaCost() == minion.getManaCost() &&
                                    ((Minion) target).getLiveInRound() == minion.getLiveInRound() && ((Minion) target).getDamage() == minion.getDamage() &&
                                    (((Minion) target).getHealth() == minion.getHealth())) {
                                return minion;
                            }
                        }
                    }
                    if (gameManager.getGameState().getFreind().getHand()!=null) {
                        for (card card : gameManager.getGameState().getFreind().getHand()) {
                            if (card instanceof Minion) {
                                if (((Minion) target).getName().equalsIgnoreCase(card.getName()) && ((Minion) target).getManaCost() == card.getManaCost() &&
                                        ((Minion) target).getLiveInRound() == ((Minion) card).getLiveInRound() && ((Minion) target).getDamage() == ((Minion) card).getDamage() &&
                                        (((Minion) target).getHealth() == ((Minion) card).getHealth())) {
                                    return (Attackable) card;
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            return null;
        }
        return null;
    }

    card getCard(card card, int nobat,GameManager gameManager) {
        if (nobat==1) {
            return checkFriend(card,gameManager);
        }
        else {
            return checkEnemy(card,gameManager);
        }
    }

    card checkEnemy(card card,GameManager gameManager) {
        if (gameManager.getGameState().getEnemy().getHand()!=null) {
            for (card cardMoredNazar : gameManager.getGameState().getEnemy().getHand()) {
                if (card.getName().equalsIgnoreCase(cardMoredNazar.getName()) && card.getManaCost() == cardMoredNazar.getManaCost()) {
                    if (card instanceof Minion && cardMoredNazar instanceof Minion) {
                        if (((Minion) card).getDamage() == ((Minion) cardMoredNazar).getDamage() && ((Minion) card).getHealth() == ((Minion) cardMoredNazar).getHealth() && ((Minion) card).getLiveInRound() == ((Minion) cardMoredNazar).getLiveInRound()) {
                            return cardMoredNazar;
                        }
                    }
                    if (card instanceof weapen && cardMoredNazar instanceof weapen) {
                        if (((weapen) card).getDamage() == ((weapen) cardMoredNazar).getDamage() && ((weapen) card).getDurability() == ((weapen) cardMoredNazar).getDurability()) {
                            return cardMoredNazar;
                        }
                    }
                    if (card instanceof spell && cardMoredNazar instanceof spell) {
                        return cardMoredNazar;
                    }
                }
            }
        }
        if (card instanceof Minion&&gameManager.getGameState().getEnemy().getGround()!=null) {
            for (Minion minion : gameManager.getGameState().getEnemy().getGround()) {
                if (card.getName().equalsIgnoreCase(minion.getName()) && card.getManaCost() == minion.getManaCost() &&
                        ((Minion) card).getLiveInRound() == minion.getLiveInRound() && ((Minion) card).getDamage() == minion.getDamage() &&
                        ((Minion) card).getHealth() == minion.getHealth()) {
                    return minion;
                }
            }
        }
        return null;
    }

    card checkFriend(card card,GameManager gameManager) {
        System.out.println(card);
        if (gameManager.getGameState().getFreind().getHand()!=null) {
            for (card cardMoredNazar : gameManager.getGameState().getFreind().getHand()) {
                if (card.getName().equalsIgnoreCase(cardMoredNazar.getName()) && card.getManaCost() == cardMoredNazar.getManaCost()) {
                    if (card instanceof Minion && cardMoredNazar instanceof Minion) {
                        if (((Minion) card).getDamage() == ((Minion) cardMoredNazar).getDamage() && ((Minion) card).getHealth() == ((Minion) cardMoredNazar).getHealth() && ((Minion) card).getLiveInRound() == ((Minion) cardMoredNazar).getLiveInRound()) {
                            System.out.println("success");
                            return cardMoredNazar;

                        }
                    }
                    if (card instanceof weapen && cardMoredNazar instanceof weapen) {
                        if (((weapen) card).getDamage() == ((weapen) cardMoredNazar).getDamage() && ((weapen) card).getDurability() == ((weapen) cardMoredNazar).getDurability()) {
                            return cardMoredNazar;
                        }
                    }
                    if (card instanceof spell && cardMoredNazar instanceof spell) {
                        return cardMoredNazar;
                    }
                }

            }
        }
        if (card instanceof Minion&&gameManager.getGameState().getFreind().getGround()!=null) {
            for (Minion minion : gameManager.getGameState().getFreind().getGround()) {
                if (card.getName().equalsIgnoreCase(minion.getName()) && card.getManaCost() == minion.getManaCost() &&
                        ((Minion) card).getLiveInRound() == minion.getLiveInRound() && ((Minion) card).getDamage() == minion.getDamage() &&
                        ((Minion) card).getHealth() == minion.getHealth()) {
                    return minion;
                }
            }
        }
        return null;
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



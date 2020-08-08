package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.CollectionManager;
import logic.PlayerManager;
import logic.ShopManager;
import model.GameState;
import model.Player;
import model.Request;

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

    ClientHandler(GameServer gameServer, Socket socket) {
        this.gameServer = gameServer;
        this.socket = socket;
        clientName = socket.getRemoteSocketAddress().toString();
        logicHandler = new LogicHandler(this.gameServer);
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
                boolean isAccount = request.getParameters().get(2).equals("true");
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
            case "logout":
                try {
                    Player player = objectMapper.readValue(request.getBody(), Player.class);
                    gameServer.changeState(player);
                    logicHandler.getPlayerManager().exit(player);
                    request.setResult(true);
                    send(convertRequest(request));
                    alive = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
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
}

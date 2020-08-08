package server;

import model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class GameServer extends Thread{

    private ServerConstants serverConstants;
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients;




    private ArrayList<Player> players ;
    private ArrayList<ClientHandler> acceptForPlay=new ArrayList<>();
   // private ArrayList<Game> availableGames =new ArrayList<>();
    private ObjectMapper objectMapper= new ObjectMapper();
    private Semaphore semaphore=new Semaphore(1);
    private Request unhandled=null;



    public GameServer(int serverPort){
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            System.out.println(" there is error in running server");
            e.printStackTrace();
        }
        clients = new ArrayList<>();
        serverConstants.initFill();
        loadPlayers();
        System.out.println("Server Started at: " + serverPort);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                System.out.println("before getting socket");
                Socket socket = serverSocket.accept();
                System.out.println("after getting soket");
                ClientHandler clientHandler = new ClientHandler(this, socket);
                clients.add(clientHandler);
                //game
                System.out.println("Client at: " + socket.getRemoteSocketAddress().toString() + " is connected.");

                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadPlayers(){
        players=new ArrayList<>();
                String st = " src/main/resources/userJson";
                File file = new File(st);
        for (File directoryFile:file.listFiles()) {
            try {
                Player player=objectMapper.readValue(file,Player.class);
                players.add(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeState(Player player){
        int i= getIndex(players,player);
        if (i!=-1) {
            players.get(i).setState(player.getState());
            players.get(i).getAvailableDecks().get(getDeck(player)).setCup(player.getCurrentDeck().getCup());
            players.set(getIndex(players,player),player);
        }
        else {
            System.out.println("unvalid index");
        }
    }

    private int getDeck(Player player){
        for (int i = 0; i < player.getAvailableDecks().size(); i++) {
            if (player.getAvailableDecks().get(i).getName().equalsIgnoreCase(player.getCurrentDeck().getName())){
                return i;
            }
        }
        return -1;
    }

    private int getIndex(ArrayList<Player>players,Player player){
        int i=-1;
        for (int j = 0; j < players.size(); j++) {
            if (players.get(j).getUsername().equalsIgnoreCase(player.getUsername())){
                return j;
            }
        }
        return i;
    }

    public ServerConstants getServerConstants() {
        return serverConstants;
    }

    public void setServerConstants(ServerConstants serverConstants) {
        this.serverConstants = serverConstants;
    }

    public ArrayList<ClientHandler> getClients() {
        return clients;
    }

    public void setClients(ArrayList<ClientHandler> clients) {
        this.clients = clients;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Request getUnhandled() {
        return unhandled;
    }

    public void setUnhandled(Request unhandled) {
        this.unhandled = unhandled;
    }
}

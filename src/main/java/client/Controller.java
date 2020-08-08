package client;

import CLI.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.Constans;
import model.GameState;
import model.Request;
import swing.*;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {

    private static Controller instance = new Controller();

    public static Controller getInstance() {
        return instance;
    }

    private MyFrame myFrame;
    private GameState gameState;
    private Shop shop;
    private Menu menu;
    private Login login;
    private Collection collection;
    private Converter converter = new Converter();
    private Constans constants;
    private ClientConstants clientConstants;
    private LogicMapper logicMapper;
    private GamePanel gamePanel;
    private String txtAddress;
    private ObjectMapper objectMapper;
    private Client client;
    private boolean haveOpponent=false;

    //constructor
    private Controller() {
        clientConstants = new ClientConstants();
        objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/configFiles/config.json");
        try {
            constants = objectMapper.readValue(file, Constans.class);
        } catch (IOException e) {
            System.out.println(" cant load constans");
            e.printStackTrace();
        }
        // constants =new Constans();


    }


    public String getStringValueOfGameState(GameState gameState) {
        try {
            String message = objectMapper.writeValueAsString(gameState);
            return message;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void exitGame() {
        int action = JOptionPane.showConfirmDialog(this.getMyFrame(), "do you really want to exit?", "Exit Title", JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {
            ArrayList<String> parameters = new ArrayList<>();
            String playerString = null;
            try {
                playerString = objectMapper.writeValueAsString(gameState.getPlayer());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            Request request = new Request(client.getToken(), "logout", parameters, playerString);
            client.getSender().send(request);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }


    public  void myLogger(String fileName, String write, boolean append) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName, append);
            fileWriter.write(write);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void delete(String text) {
        System.out.println("you will delete your account  if you want type your password else type something");
        ArrayList<String>parameters= new ArrayList<>();
        parameters.add(text);
        String gameStateString = getStringValueOfGameState(Controller.getInstance().getGameState());
        Request request = new Request(Controller.getInstance().getClient().getToken(),"delete",parameters,gameStateString);
        client.getSender().send(request);

    }



    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public LogicMapper getLogicMapper() {
        return logicMapper;
    }

    public void setLogicMapper(LogicMapper logicMapper) {
        this.logicMapper = logicMapper;
    }

    public Constans getConstants() {
        return constants;
    }

    public void setConstants(Constans constants) {
        this.constants = constants;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Shop getShop() {
        return shop;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public MyFrame getMyFrame() {
        return myFrame;
    }

    public void setMyFrame(MyFrame myFrame) {
        this.myFrame = myFrame;
    }

    public void setShope(Shop shop) {
        this.shop = shop;
    }

    public ClientConstants getClientConstants() {
        return clientConstants;
    }

    public void setClientConstants(ClientConstants clientConstants) {
        this.clientConstants = clientConstants;
    }

    public boolean isHaveOpponent() {
        return haveOpponent;
    }

    public void setHaveOpponent(boolean haveOpponent) {
        this.haveOpponent = haveOpponent;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}

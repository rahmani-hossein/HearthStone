package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GameState;
import model.Request;
import swing.GamePanel;
import swing.Menu;


import javax.swing.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientReciever extends Thread {

     private InputStream inputStream;
   private PrintStream printStream;
   private Client client;
   private ObjectMapper objectMapper= new ObjectMapper();

    ClientReciever(InputStream inputStream, PrintStream printStream, Client client) {
        this.inputStream = inputStream;
        this.printStream = printStream;
        this.client = client;
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
                        Controller.getInstance().setTxtAddress(String.format("src/main/userText/%s.txt",gameState.getPlayer().getUsername()+gameState.getPlayer().getPassword()));
                        Menu menu= new Menu(Controller.getInstance().getGameState().getPlayer());
                        Controller.getInstance().setMenu(menu);
                        Controller.getInstance().getMyFrame().add(menu,"menu");
                        Controller.getInstance().getMyFrame().setPanel("menu");

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    break;

                case "logout":
                    System.exit(0);
                    break;
                case "move":
                    GameState myGameState=getObject(request.getBody());

                        Controller.getInstance().getGamePanel().repaint();
                        Controller.getInstance().getGamePanel().revalidate();
                        System.out.println("after repainting");

                    break;
                case "waiting":
                    Controller.getInstance().setHaveOpponent(true);
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"wait for enother client ","play waiting",JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "play":
                    GameState gameState=getObject(request.getBody());
                    if (gameState!=null){
                        Controller.getInstance().setGameState(gameState);
                       // Controller.getInstance().getMyFrame().getLock().notify();
                        GamePanel gamePanel = new GamePanel(Controller.getInstance().getClientConstants().getPanelWidth(),Controller.getInstance().getClientConstants().getPanelHeight(),Controller.getInstance().getGameState());
                        Controller.getInstance().setGamePanel(gamePanel);
                        Controller.getInstance().getMyFrame().getMainpanel().add(gamePanel, "gamepanel");
                        Controller.getInstance().getMyFrame().setPanel("gamepanel");
                    }
                    else {
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"error getting gamestate","networkError",JOptionPane.ERROR_MESSAGE);
                    }
                    break;

            }
        }
        else {
            JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"error of recieved request result is false","result",JOptionPane.ERROR_MESSAGE);
        }
    }

private GameState getObject(String jsonValue){
    try {
        return objectMapper.readValue(jsonValue,GameState.class);
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

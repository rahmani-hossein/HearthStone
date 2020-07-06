package swing;

import CLI.Administer;
import CLI.GameState;
import CLI.Player;
import CLI.utilities;
import logic.Constans;
import logic.ShopManager;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.SystemColor.menu;


public class Controller {




    private static Controller instance = new Controller();

    public static Controller getInstance() {
        return instance;
    }

    private MyFrame myFrame;
    private GameState gameState;
    private Administer administer;
    private Shop shop;
    private Menu menu;
    private Collection collection;
    private Converter converter=new Converter();
    private Constans constants=new Constans();


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

    public void exitGame(){
        int action= JOptionPane.showConfirmDialog(this.getMyFrame(),"do you really want to exit?","Exit Title",JOptionPane.OK_CANCEL_OPTION);
        if (action==JOptionPane.OK_OPTION) {
            try {
                administer.exit(gameState.getPlayer());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void myLogger(String fileName, String write, boolean append)  {
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
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
   //constructor
    private Controller(){
        administer= new Administer();

//        ShopManager shopManager=new ShopManager(this.getGameState().getPlayer());
//        shop=new Shop(shopManager);
//        myFrame.getMainpanel().add(shop, SHOP_PANEL);

    }

    public MyFrame getMyFrame() {
        return myFrame;
    }

    public Administer getAdminister() {
        return administer;
    }

    public void setAdminister(Administer administer) {
        this.administer = administer;
    }

    public void setMyFrame(MyFrame myFrame) {
        this.myFrame = myFrame;
    }

    public void delete(String text ) {
        System.out.println("you will delete your account  if you want type your password else type something");
        if (text.equals(gameState.getPlayer().getPassword())) {
            String info=gameState.getPlayer().getUsername()+gameState.getPlayer().getPassword();
            String st = String.format("userJson\\%s.json", info);
            String st1 = String.format("%s.txt", info);
            String st2 = String.format("trash users\\%s.txt", info);
            File file = new File(st);
            File file1 = new File(st1);
            Scanner scanner = null;
            try {
                scanner = new Scanner(file1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                String temp = scanner.nextLine();
                myLogger(st2, temp + "\n", true);
            }
            myLogger(st2, "DELETED_AT:" + utilities.time() + "\n" + "\n", true);
            while (scanner.hasNext()) {
                String temp = scanner.nextLine();
                myLogger(st2, temp + "\n", true);
            }
            file.delete();
            scanner.close();
            file1.delete();
            System.exit(0);
        } else {
            System.out.println("something went wrong or you dont want to delete your account");
        }
    }

    public void setShope(Shop shop) {
        this.shop=shop;
    }
}

package CLI;

import client.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.*;
import model.*;
import swing.Collection;
import swing.Menu;
import swing.Shop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static CLI.utilities.time;
import static swing.MyFrame.MENU_PANEL;

public class Administer {
    public static final String SHOP_PANEL = "shop";
    public static final String COLLECTION_PANEL = "collection";

    private ObjectMapper objectMapper = new ObjectMapper();
 private DeckManager deckManager =new DeckManager();
    private GameState gameState;
    private HeroCreator heroCreator=new HeroCreator();

//    public static Administer getAdministerInstance() {
//        return AdministerInstance;
//    }

    public Administer() {

    }

    public boolean login(String username, String password, boolean isaccount) {
        if (!isaccount) {
            String user = username;
            String pass = password;

            // create txt file for user
            String st1 = String.format("src/main/userText/%s.txt", user + pass);
            File file = new File(st1);
//            if (file.exists()){
//                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"you clicked wrong on create button","Login Error",JOptionPane.OK_CANCEL_OPTION);
//                return false;
//            }

            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(st1, true);

                fileWriter.flush();
                fileWriter.write("username: " + user + "\n");
                fileWriter.write("Created_at: " + time() + "\n");
                fileWriter.write("password: " + pass + "\n");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //json file for users


            String st = String.format("src/main/userJson/%s.json", user + pass);
            FileWriter fileWriter1 = null;
            try {
                fileWriter1 = new FileWriter(st);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // inicialize player
            ArrayList<Minion> availableCardsM = new ArrayList<>();
            ArrayList<spell> availableCardsS = new ArrayList<>();
            ArrayList<weapen> availableCardsW = new ArrayList<>();
            ArrayList<Hero> availableHeros = new ArrayList<>();

            System.out.println("we randomly  choose your hero ");

            Random random = new Random();
            int rand = random.nextInt(5);
            Player player = initialPlayer(rand, user, pass);
            gameState = new GameState(player);
            ShopManager shopManager = new ShopManager(player);
            Controller.getInstance().setGameState(gameState);
            Controller.getInstance().setAdminister(this);
            Menu menu=new Menu(Controller.getInstance().getGameState().getPlayer());
            Controller.getInstance().setMenu(menu);
            Controller.getInstance().getMyFrame().getMainpanel().add(menu, MENU_PANEL);
            Shop shop = new Shop(shopManager);
            Controller.getInstance().setShope(shop);
            Controller.getInstance().getMyFrame().getMainpanel().add(shop, SHOP_PANEL);
            CollectionManager collectionManager = new CollectionManager(player);
            Collection collection = new Collection(collectionManager);
            Controller.getInstance().setCollection(collection);
            Controller.getInstance().getMyFrame().getMainpanel().add(collection, COLLECTION_PANEL);
            LogicMapper logicMapper =new LogicMapper(Controller.getInstance());
            Controller.getInstance().setLogicMapper(logicMapper);


            try {
                objectMapper.writeValue(fileWriter1, player);

                fileWriter1.close();


                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;

        } else if (isaccount) {
            String user = username;
            String pass = password;
            String st = String.format("src/main/userJson/%s.json", user + pass);
            File file = new File(st);

            if (file.exists()) {
                String account = String.format("src/main/userText/%s.txt", user + pass);
                try {
                    FileWriter fileWriter = new FileWriter(account, true);
                    fileWriter.write("signed in at:" + time() + "\n");
                    fileWriter.flush();
                    fileWriter.close();
                    //  objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                    Player player = objectMapper.readValue(file, Player.class);
                    gameState = new GameState(player);
                    Controller.getInstance().setGameState(gameState);
                    Controller.getInstance().setAdminister(this);
                   Menu menu=new Menu(Controller.getInstance().getGameState().getPlayer());
                    Controller.getInstance().setMenu(menu);
                    Controller.getInstance().getMyFrame().getMainpanel().add(menu, MENU_PANEL);
                    ShopManager shopManager = new ShopManager(player);
                    Shop shop = new Shop(shopManager);
                    //   Controller.getInstance().setShope(shop);
                   // Controller.getInstance().setGameState(gameState);
                    Controller.getInstance().getMyFrame().getMainpanel().add(shop, SHOP_PANEL);
                    CollectionManager collectionManager = new CollectionManager(player);
                    Collection collection = new Collection(collectionManager);
                    Controller.getInstance().setCollection(collection);
                    Controller.getInstance().getMyFrame().getMainpanel().add(collection, COLLECTION_PANEL);
                    LogicMapper logicMapper =new LogicMapper(Controller.getInstance());
                    Controller.getInstance().setLogicMapper(logicMapper);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                System.out.println("you dont have account please try again and  build an account ");
                return false;

            }
        }
        return false;

    }
}

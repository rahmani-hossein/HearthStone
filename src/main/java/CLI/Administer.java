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
    public static final String GAME_PANEL ="game";
    private final static Administer AdministerInstance = new Administer();

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

    private Player initialPlayer(int rand, String user, String pass) {
        Hero hero = null;
        Player player = null;
        Deck currentDeck = null;
        CardManager cardManager = new CardManager();
        ArrayList<Minion> availableCardsM = new ArrayList<>();
        ArrayList<spell> availableCardsS = new ArrayList<>();
        ArrayList<weapen> availableCardsW = new ArrayList<>();
        ArrayList<Deck> availableDecks = new ArrayList<>();

        availableCardsM.add(cardManager.createM("hotAirballon"));
        availableCardsM.add(cardManager.createM("blazingBattlemage"));
        availableCardsM.add(cardManager.createM("evasiveChimaera"));
        availableCardsS.add(cardManager.createS("malygosFireball"));
        availableCardsW.add(cardManager.createW("arcaniteReaper"));
        availableCardsW.add(cardManager.createW("bloodRazor"));
        availableCardsW.add(cardManager.createW("assassinBlade"));
        availableCardsW.add(cardManager.createW("dragonClaw"));


        if (rand == 0) {
            //mage
            availableCardsS.add(cardManager.createS("polymorph"));
            availableCardsS.add(cardManager.createS("fireBlast"));
            hero=heroCreator.createHero("mage");

        } else if (rand == 1) {
            //rouge
            availableCardsS.add(cardManager.createS("friendlySmith"));
            availableCardsW.add(cardManager.createW("fierywaraxe"));
            hero=heroCreator.createHero("rouge");
        } else if (rand == 2) {
            //warlock
            availableCardsM.add(cardManager.createM("fireHawk"));
            availableCardsM.add(cardManager.createM("dreadScale"));
            hero=heroCreator.createHero("warlock");

        } else if (rand == 3) {
            //hunter
            availableCardsS.add(cardManager.createS("arcaneShot"));
            availableCardsM.add(cardManager.createM("swampKingDred"));
            hero=heroCreator.createHero("hunter");
        } else if (rand == 4) {
            //priest
            availableCardsS.add(cardManager.createS("flamestrike"));
            availableCardsM.add(cardManager.createM("highPriestAmet"));
            hero=heroCreator.createHero("priest");
        }
        currentDeck = deckManager.buildDeck("default",hero, availableCardsM, availableCardsS, availableCardsW);
        availableDecks.add(currentDeck);

        player = new Player(user, pass, 80, null, availableCardsS, availableCardsM, availableCardsW, availableDecks);

        return player;
    }



    public boolean contains(String[] st, String name) {
        for (int i = 0; i < st.length; i++) {
            if (st[i].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    //    public static void remove(ArrayList<card> myCard, String name) {
//
//    }
//


    public void exit(Player player) throws IOException {
        System.out.println("wait for update");
        ObjectMapper objectMapper = new ObjectMapper();
        String account = String.format("src/main/userJson/%s.json", player.username + player.password);
        FileWriter fileWriter = new FileWriter(account, false);
        objectMapper.writeValue(fileWriter, player);
        fileWriter.close();
        String account2 = String.format("src/main/userText/%s.txt", player.username + player.password);
        FileWriter file2 = new FileWriter(account2, true);
        file2.write("signed up at:" + time());
        file2.flush();
        file2.close();
        System.out.println("see you soon");
        System.exit(0);
    }


}

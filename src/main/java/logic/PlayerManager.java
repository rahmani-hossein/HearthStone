package logic;

import model.GameState;
import model.Player;
import CLI.utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import server.GameServer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import static CLI.utilities.time;

public class PlayerManager {

    private ObjectMapper objectMapper = new ObjectMapper();
    private DeckManager deckManager =new DeckManager();
    private GameState gameState;
    private HeroCreator heroCreator=new HeroCreator();
    private GameState currentGameState=null;
    private SecureRandom secureRandom =new SecureRandom();
    private GameServer gameServer;

    public PlayerManager(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    public boolean login(String username, String password, boolean isaccount) {
        if (!isaccount) {
            String user = username;
            String pass = password;

            // create txt file for user
            String st1 = String.format("src/main/userText/%s.txt", user + pass);
            File file = new File(st1);

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
            currentGameState = gameState;




//            Controller.getInstance().getMyFrame().getMainpanel().add(shop, SHOP_PANEL);
//            Collection collection = new Collection(collectionManager);
//            Controller.getInstance().setCollection(collection);
//            Controller.getInstance().getMyFrame().getMainpanel().add(collection, COLLECTION_PANEL);
//            LogicMapper logicMapper =new LogicMapper(Controller.getInstance());
//            Controller.getInstance().setLogicMapper(logicMapper);


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
                    Player player = objectMapper.readValue(file, Player.class);
                    gameState = new GameState(player);
                   currentGameState=gameState;

//                    Controller.getInstance().getMyFrame().getMainpanel().add(shop, SHOP_PANEL);
//                    CollectionManager collectionManager = new CollectionManager(player);
//                    Collection collection = new Collection(collectionManager);
//                    Controller.getInstance().setCollection(collection);
//                    Controller.getInstance().getMyFrame().getMainpanel().add(collection, COLLECTION_PANEL);
//                    LogicMapper logicMapper =new LogicMapper(Controller.getInstance());
//                    Controller.getInstance().setLogicMapper(logicMapper);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                System.out.println("you dont have account please try again and  build an account ");
                return false;

            }
        }
        System.out.println("asdfghj");
        return false;

    }

    private Player initialPlayer(int rand, String user, String pass) {

        Player player = null;
        Deck currentDeck = null;
        CardManager cardManager = new CardManager();
        ArrayList<Minion> availableCardsM = new ArrayList<>();
        ArrayList<spell> availableCardsS = new ArrayList<>();
        ArrayList<weapen> availableCardsW = new ArrayList<>();
        ArrayList<Deck> availableDecks = new ArrayList<>();

      addPrimaryCards(cardManager,availableCardsS,availableCardsM,availableCardsW);

        Hero hero = setHero(rand,cardManager,availableCardsS,availableCardsM,availableCardsW);

        currentDeck = deckManager.buildDeck("default",hero, availableCardsM, availableCardsS, availableCardsW);
        availableDecks.add(currentDeck);

        player = new Player(user, pass, 80, null, availableCardsS, availableCardsM, availableCardsW, availableDecks);

        return player;
    }

    private Hero setHero(int  rand,CardManager cardManager,ArrayList<spell> availableCardsS,ArrayList<Minion> availableCardsM,ArrayList<weapen> availableCardsW){
        Hero hero=null;
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
        System.out.println(hero.getName()+ hero.getHP()+hero.getShowHeroPower());
        return hero;
    }

    private void addPrimaryCards(CardManager cardManager,ArrayList<spell> availableCardsS,ArrayList<Minion> availableCardsM,ArrayList<weapen> availableCardsW){
        availableCardsM.add(cardManager.createM("hotAirballon"));
        availableCardsM.add(cardManager.createM("blazingBattlemage"));
        availableCardsM.add(cardManager.createM("evasiveChimaera"));
        availableCardsS.add(cardManager.createS("malygosFireball"));
        availableCardsW.add(cardManager.createW("arcaniteReaper"));
        availableCardsW.add(cardManager.createW("bloodRazor"));
        availableCardsW.add(cardManager.createW("assassinBlade"));
        availableCardsW.add(cardManager.createW("dragonClaw"));
    }

    public boolean contains(String[] st, String name) {
        for (int i = 0; i < st.length; i++) {
            if (st[i].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public int createToken() {
        return secureRandom.nextInt();
    }

    public void exit(Player player) throws IOException {
        System.out.println("wait for update");
        ObjectMapper objectMapper = new ObjectMapper();
        String account = String.format("src/main/userJson/%s.json", player.getUsername() + player.getPassword());
        FileWriter fileWriter = new FileWriter(account, false);
        objectMapper.writeValue(fileWriter, player);
        fileWriter.close();
        String account2 = String.format("src/main/userText/%s.txt", player.getUsername() + player.getPassword());
        FileWriter file2 = new FileWriter(account2, true);
        file2.write("signed up at:" + time());
        file2.flush();
        file2.close();
        System.out.println("see you soon");

    }

    public boolean delete(String pass, Player player){
        if (pass.equals(player.getPassword())) {
            String info = player.getUsername() + player.getPassword();
            String st = String.format("src/main/userJson/%s.json", info);
            String st1 = String.format("src/main/userText/%s.txt", info);
            String st2 = String.format("src/main/trash users/%s.txt", info);
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
                log(st2, temp + "\n", true);
            }
            log(st2, "DELETED_AT:" + utilities.time() + "\n" + "\n", true);
            while (scanner.hasNext()) {
                String temp = scanner.nextLine();
                log(st2, temp + "\n", true);
            }
            file.delete();
            scanner.close();
            file1.delete();
            //  System.exit(0);
            return true;
        } else {
            System.out.println("something went wrong or you dont want to delete your account");
            return false;
        }
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    private void log(String fileName, String write, boolean append){
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
    public ArrayList<Score> getMe(Player player){
        ArrayList<Score> scores = new ArrayList<>();
        Collections.sort(gameServer.getPlayers());
        int index = gameServer.getIndex(gameServer.getPlayers(),player);
        for (int i = Math.max(0,index-5); i <Math.min(gameServer.getPlayers().size(),index+5) ; i++) {
            Player player1=gameServer.getPlayers().get(i);
            scores.add(new Score(player1.getUsername(),player1.getState(),player1.sumCup()));
        }
        return scores;
    }

    public ArrayList<Score> getTop10(){
        ArrayList<Score> scores = new ArrayList<>();
        Collections.sort(gameServer.getPlayers());

        for (int i = Math.max(0,gameServer.getPlayers().size()-10); i <gameServer.getPlayers().size() ; i++) {
            Player player1=gameServer.getPlayers().get(i);
            scores.add(new Score(player1.getUsername(),player1.getState(),player1.sumCup()));
        }
        return scores;
    }
}

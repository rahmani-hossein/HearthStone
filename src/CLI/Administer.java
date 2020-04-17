package CLI;

import charactor.*;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Administer {

    private final static Administer AdministerInstance = new Administer();

    private ObjectMapper objectMapper=new ObjectMapper();


    public  static Administer getAdministerInstance(){
        return getAdministerInstance();
    }
    private Administer(){

    }
    private void login(String username,String password,boolean isaccount){
        if (!isaccount) {
            String user = username;
            String pass = password;

            // create txt file for user
                String st1 = String.format("%s.txt", user + pass);
                File file = new File(st1);
                FileWriter fileWriter=null;
            try {
                fileWriter = new FileWriter(st1, true);

                fileWriter.flush();
                fileWriter.write("username: " + user + "\n");
                fileWriter.write("Created_at: " + utilities.time() + "\n");
                fileWriter.write("password: " + pass + "\n");
                fileWriter.flush();
                fileWriter.close();
            }
                catch (IOException e) {
                e.printStackTrace();
            }
                //json file for users



            String st = String.format("userJson\\%s.json", user + pass);
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
            String[] warlock = {"fireHawk", "dreadScale"};
            String[] mage = {"fireBlast", "polymorph"};
            String[] rouge = {"friendlySmith", "fierryWaraxe"};
            String[] heros = {"mage", "rouge", "warlock"};
            System.out.println("we randomly  choose your hero ");
            ArrayList<Minion> availableCardsMHero = new ArrayList<>();
            ArrayList<spell> availableCardsSHero = new ArrayList<>();
            ArrayList<weapen> availableCardsWHero = new ArrayList<>();
            Random random = new Random();
            int rand = random.nextInt(3);
            Hero hero;
            if (rand == 0) {
//                availableCardsS.add((spell) administer.create("fireBlast"));
//                availableCardsS.add((spell) administer.create("polymorph"));
//                availableCardsSHero.add((spell) administer.create("fireBlast"));
//                availableCardsSHero.add((spell) administer.create("polymorph"));
                hero = new Hero(30, "mage", "spend 2 mana and can deal 1 damage from a chosen enemy ", "2 mana less than usual for spells", availableCardsMHero, availableCardsSHero, availableCardsWHero);
            } else if (rand == 1) {
//                availableCardsS.add((spell) administer.create("friendlySmith"));
//                availableCardsW.add((weapen) administer.create("fierryWaraxe"));
//                availableCardsSHero.add((spell) administer.create("friendlySmith"));
//                availableCardsWHero.add((weapen) administer.create("fierryWaraxe"));
                hero = new Hero(30, "rouge", "spend 3 mana and can get 1 card from enemy deck and add this to her hand.", "2 mana less than usual for cards that are not neutral or vip of herself ", availableCardsMHero, availableCardsSHero, availableCardsWHero);
            } else {
//                availableCardsM.add((Minion) administer.create("firehawk"));
//                availableCardsM.add((Minion) administer.create("dreadScale"));
//                availableCardsMHero.add((Minion) administer.create("firehawk"));
//                availableCardsMHero.add((Minion) administer.create("dreadScale"));
                hero = new Hero(35, "warlock", "cost 2hp and randomly do one of these 2 works  if we have minion in ground plus+1 attack and hp to it or get randomly one card from deck and add it to its hand", "he has 35 hp", availableCardsMHero, availableCardsSHero, availableCardsWHero);
            }

            availableHeros.add(hero);
            Player player = new Player(user, pass, 80, hero, availableCardsS, availableCardsM, availableCardsW, availableHeros);
            try {
                objectMapper.writeValue(fileWriter1, player);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            callMenu(player);
        } else if (isaccount) {
            String user = username;
            String pass = password;
            String st = String.format("userJson\\%s.json", user + pass);
            File file = new File(st);

            if (file.exists()) {
                String account = String.format("%s.txt", user + pass);
                try {
                    FileWriter fileWriter = new FileWriter(account, true);
                    fileWriter.write("signed in at:" + utilities.time() + "\n");
                    fileWriter.flush();
                    fileWriter.close();
                    //  objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                    Player player = objectMapper.readValue(file, Player.class);
                }catch (IOException e) {
                    e.printStackTrace();
                }
//                callMenu(player);
            } else {
                System.out.println("you dont have account please try again and  build an account ");

            }
        } else {
            System.out.println("please type suitable answer");
        }
    }




  public String[] minions= new String[]{"blazingBattlemage", "hotAirballoon", "FireHawk ", "evasiveChimaera", "amberWatcher", "dreadScale","veranus"};
    public String[] spells= new String[]{"decimation","malygosFireball","malygosFlamestrike","friendlySmith","fireBlast"};
    public String[]weapens= new String[]{"bloodRazor","fieryWaraxe","bloodClaw","assassinBlade","arcaniteReaper","cursedBlade"};
    public String[]cardNames=new String[]{"blazingBattlemage", "hotAirballoon", "FireHawk ", "evasiveChimaera", "amberWatcher", "dreadScale","veranus","decimation","malygosFireball","malygosFlamestrike","friendlySmith","fireBlast","bloodRazor","fieryWaraxe","bloodClaw","assassinBlade","arcaniteReaper","cursedBlade"};
    public HashMap<String,Integer>myMap=new HashMap<>();
    public boolean contains(String[] st,String name){
       for (int i = 0; i <st.length ; i++) {
           if (st[i].equalsIgnoreCase(name)){
               return true;
           }
       }
       return false;
   }
   public int number(Player player,String name){
       int k=0;
       if (contains(minions,name)) {
           for (card card : player.availableCardsM) {
               if (card.getName().equalsIgnoreCase(name)){
                   k++;
               }
           }
       }
       else if (contains(spells,name)){
           for (card card : player.availableCardsS) {
               if (card.getName().equalsIgnoreCase(name)){
                   k++;
               }
           }
       }
       else if (contains(weapens,name)){
           for (card card : player.availableCardsW) {
                if (card.getName().equalsIgnoreCase(name)){
                    k++;
                }
           }
       }
       else {
           k=-1;
       }
       return k;
   }
    public card create(String name) throws IOException {
        String st=String.format("Json\\%s.json",name);
        FileReader fileReader=new FileReader(st);
        ObjectMapper objectMapper=new ObjectMapper();
        if (contains(minions,name)) {
            Minion minion=new Minion();
            minion= objectMapper.readValue(fileReader, Minion.class);
            return minion;
        }else if (contains(spells,name)){
            spell spell=new spell();
           spell= objectMapper.readValue(fileReader, spell.class);
           return spell;
        }else if (contains(weapens,name)){
            weapen weapen=new weapen();
           // objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            weapen= objectMapper.readValue(fileReader, weapen.class);
            return weapen;
        }
        return null;
    }
    public static void remove(ArrayList<card> myCard,String name){

    }
    public int number(Hero hero, String name){
        int k=0;
        if (contains(minions,name)) {
            for (card card : hero.availableCardsM) {
                if (card.getName().equalsIgnoreCase(name)){
                    k++;
                }
            }
        }
        else if (contains(spells,name)){
            for (card card : hero.availableCardsS) {
                if (card.getName().equalsIgnoreCase(name)){
                    k++;
                }
            }
        }
        else if (contains(weapens,name)){
            for (card card : hero.availableCardsW) {
                if (card.getName().equalsIgnoreCase(name)){
                    k++;
                }
            }
        }
        else {
            k=-1;
        }
        return k;
    }
}

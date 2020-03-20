package CLI;

import charactor.*;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.text.Utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

import static CLI.utilities.*;

public class Main {
   static Administer administer;
   static Player currentPlayer;
   public static void myLogger(String fileName,String write,boolean append) throws IOException {
       FileWriter fileWriter=new FileWriter(fileName,append);
       fileWriter.write(write);
       fileWriter.flush();
       fileWriter.close();
   }
    public static void begin() throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();

        Scanner scanner=new Scanner(System.in);
        System.out.println("welcome");
        System.out.println("already have an account?");
        String ans=scanner.next();
        if ("no".equalsIgnoreCase(ans)){
            System.out.println("enter a  new user name");
            String user=scanner.next();
            System.out.println("enter a new password");
            String pass=scanner.next();

            // create txt file for user
            String st1=String.format("%s.txt",user+pass);
            File file=new File(st1);
            FileWriter fileWriter=new FileWriter(st1,true);
            fileWriter.flush();
           fileWriter.write("username: "+user+"\n");
           fileWriter.write("Created_at: "+utilities.time()+"\n");
            fileWriter.write("password: "+pass+"\n");
           fileWriter.flush();
            fileWriter.close();
           //json file for users
            String st=String.format("userJson\\%s.json",user+pass);
            FileWriter fileWriter1=new FileWriter(st);
            // inicialize player
            ArrayList<Minion> availableCardsM=new ArrayList<>();
            ArrayList<spell> availableCardsS=new ArrayList<>();
            ArrayList<weapen> availableCardsW=new ArrayList<>();
            ArrayList<Hero>availableHeros=new ArrayList<>();
            String[]warlock={"fireHawk","dreadScale"};
            String[]mage={"fireBlast","polymorph"};
            String[]rouge={"friendlySmith","fierryWaraxe"};
            String []heros={"mage","rouge","warlock"};
            System.out.println("we randomly  choose your hero ");
            ArrayList<Minion> availableCardsMHero=new ArrayList<>();
            ArrayList<spell> availableCardsSHero=new ArrayList<>();
            ArrayList<weapen> availableCardsWHero=new ArrayList<>();
            Random random=new Random();
            int rand=random.nextInt(3);
            Hero hero;
            if (rand==0){
//                availableCardsS.add((spell) administer.create("fireBlast"));
//                availableCardsS.add((spell) administer.create("polymorph"));
//                availableCardsSHero.add((spell) administer.create("fireBlast"));
//                availableCardsSHero.add((spell) administer.create("polymorph"));
                 hero=new Hero(30,"mage","spend 2 mana and can deal 1 damage from a chosen enemy ","2 mana less than usual for spells",availableCardsMHero,availableCardsSHero,availableCardsWHero);
            }
            else if(rand==1){
//                availableCardsS.add((spell) administer.create("friendlySmith"));
//                availableCardsW.add((weapen) administer.create("fierryWaraxe"));
//                availableCardsSHero.add((spell) administer.create("friendlySmith"));
//                availableCardsWHero.add((weapen) administer.create("fierryWaraxe"));
                 hero=new Hero(30,"rouge","spend 3 mana and can get 1 card from enemy deck and add this to her hand.","2 mana less than usual for cards that are not neutral or vip of herself ",availableCardsMHero,availableCardsSHero,availableCardsWHero);
            }
            else{
//                availableCardsM.add((Minion) administer.create("firehawk"));
//                availableCardsM.add((Minion) administer.create("dreadScale"));
//                availableCardsMHero.add((Minion) administer.create("firehawk"));
//                availableCardsMHero.add((Minion) administer.create("dreadScale"));
                hero=new Hero(35,"warlock","cost 2hp and randomly do one of these 2 works  if we have minion in ground plus+1 attack and hp to it or get randomly one card from deck and add it to its hand","he has 35 hp",availableCardsMHero,availableCardsSHero,availableCardsWHero);
            }

            availableHeros.add(hero);
            Player player=new Player(user,pass,80,hero, availableCardsS,availableCardsM, availableCardsW,availableHeros);
            objectMapper.writeValue(fileWriter1,player);
            fileWriter1.close();
            fileWriter.close();
            callMenu(player);
        }else if ("yes".equalsIgnoreCase(ans)){
            System.out.println("enter your user name");
            String user=scanner.next();
            System.out.println("enter your password");
            String pass=scanner.next();
            String st=String.format("userJson\\%s.json",user+pass);
            File file= new File(st);

            if (file.exists()){
                String account=String.format("%s.txt",user+pass);
                FileWriter fileWriter=new FileWriter(account,true);
                fileWriter.write("signed in at:"+ utilities.time() +"\n");
                fileWriter.flush();
                fileWriter.close();
              //  objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                Player player=objectMapper.readValue(file,Player.class);
                callMenu(player);
            }else {
                System.out.println("you dont have account please try again and  build an account ");
                begin();

            }
        }else{
            System.out.println("please type suitable answer");
            begin();
        }
    }
    public static void  callCollection(Player player) throws IOException {
        String st1 = String.format("%s.txt", player.username + player.password);
        Scanner commond = new Scanner(System.in);
        System.out.println("COLLECTION          for go to menu only type it");
        System.out.println("ls-a-heroes");
        System.out.println("ls-m-hero");
        System.out.println("ls-n-cards");
        System.out.println("menu");
        System.out.println("ls-m-cards");
        System.out.println("ls-a-cards");
        System.out.println("select[hero]");
        System.out.println("add[cardName]");
        System.out.println("remove[cardName]");
        System.out.println("for help type help");
        String in=commond.next();
        switch (in.toLowerCase()) {
            case "ls-a-heroes":
                try {
                    myLogger(st1, "commond:ls-a-heroes" + "    " + utilities.time() + "\n", true);
                } catch (Exception e) {
                    System.out.println("i cant find your log file:))))) ");
                    e.printStackTrace();
                }
                myLogger(st1, "your heroes are:    " + utilities.time() + "\n", true);
                for (Hero hero : player.availableHeroes) {
                    System.out.println(hero.toString());
                    myLogger(st1, hero.getName() + " ,", true);
                }
                myLogger(st1, "\n", true);
                callCollection(player);
                break;
            case "ls-m-hero":
                System.out.println(player.currentHero.toString());
                try {
                    myLogger(st1, "commond:ls-m-hero" + "    " + utilities.time() + "\n", true);
                    myLogger(st1, "your hero is " + player.currentHero.getName() + "\n", true);
                } catch (Exception e) {
                    System.out.println("i cant find your log file:))))) ");
                    e.printStackTrace();
                }
                callCollection(player);
                break;
            case "menu":
                callMenu(player);
                break;
            case "ls-n-cards":
                boolean is=false;
                myLogger(st1,"ls-n-cards command"+"\n",true);

                for (String string :administer.cardNames){
                    int numBig=administer.number(player,string);
                    int numSmall=administer.number(player.currentHero,string);
                    if (numBig!=numSmall){
                        is=true;
                        System.out.println(numBig-numSmall+"of "+string);
                        myLogger(st1,numBig-numSmall+"of"+string+"\n",true);
                    }
                    if (!is){
                        System.out.println("no card");
                        myLogger(st1,"no card"+"\n",true);
                    }

                }
                callCollection(player);
                break;
            case "ls-m-cards":
                Set<Minion> M = toSetM(player.currentHero.availableCardsM);
                Set<weapen> W = toSetW(player.currentHero.availableCardsW);
                Set<spell> S = toSetS(player.currentHero.availableCardsS);
                Set<card >my=new HashSet<>();
                myLogger(st1,"ls-m-cards"+"\n",true);

                if (M.size() > 0&&!(M==null)) {
                        for (Minion minion : M) {
                            String print=administer.number(player.currentHero, minion.getName()) + "of card:" + minion.toString();
                            System.out.println(print);
                            myLogger(st1,print+"\n",true);

                        }
                    }


                    if (S.size() > 0&&!(S==null)) {
                        for (spell spell : S) {
                            String print=administer.number(player.currentHero, spell.getName()) + "of card:" + spell.toString();
                            System.out.println(print);
                            myLogger(st1,print+"\n",true);

                        }
                    }

                    if (W.size() > 0&&!(W==null)) {
                        for (weapen weapen : W) {
                            String print=administer.number(player.currentHero, weapen.getName()) + "of card:" + weapen.toString();
                            System.out.println(print);
                            myLogger(st1,print+"\n",true);

                        }
                    }
                callCollection(player);
                break;
            case"ls-a-cards":
                for (Minion minion:player.availableCardsM) {
                    if (player.availableCardsM.size()>0) {
                        System.out.println(minion.toString());
                    }
                }
                for (spell spell:player.availableCardsS) {
                    if (player.availableCardsS.size()>0) {
                        System.out.println(spell.toString());
                    }
                }
                for (weapen weapen:player.availableCardsW) {
                    if (player.availableCardsW.size()>0) {
                        System.out.println(weapen.toString());
                    }
                }
                myLogger(st1,"ls-a-cards"+utilities.time()+"\n",true);
                callCollection(player);
                break;
            case "select[hero]":
                System.out.println("enter your hero name");
                 String  wanted=commond.next();
                for (Hero hero: player.availableHeroes) {
                    if (hero.getName().equalsIgnoreCase(wanted)){
                        player.setCurrentHero(hero);
                        myLogger(st1,"selected hero:"+wanted+"\n",true);
                        callCollection(player);
                    }
                    System.out.println("i cant recognize this hero or it is not in your unlock heroes");
                    callCollection(player);
                }
                break;
            case "add[cardName]":
                String cardName=commond.next();
                int num=administer.number(player,cardName);
                if (num>=0&&num<2&&(player.currentHero.availableCardsW.size()+player.currentHero.availableCardsM.size()+player.currentHero.availableCardsS.size()>15)) {
                    if (administer.contains(administer.minions, cardName)) {
                        Minion minion = (Minion) administer.create(cardName);
                        player.currentHero.availableCardsM.add(minion);
                    }
                    if (administer.contains(administer.spells, cardName)) {
                        spell spell = (spell) administer.create(cardName);
                        player.currentHero.availableCardsS.add(spell);
                    }
                    if (administer.contains(administer.weapens, cardName)) {
                        weapen weapen = (weapen) administer.create(cardName);
                        player.currentHero.availableCardsW.add(weapen);
                    }
                    myLogger(st1,"add:"+cardName+"to your current hand of hero"+utilities.time()+"\n",true);
                }
                else{
                    if ((player.currentHero.availableCardsW.size()+player.currentHero.availableCardsM.size()+player.currentHero.availableCardsS.size()>15)){
                        System.out.println("your hand is maximum your deck and now is full");
                        myLogger(st1,"your hand is full"+utilities.time()+"\n",true);
                    }
                    else {
                        System.out.println("this card is not in your deck or you have 2 or more card of this in your deck");
                        myLogger(st1,"2 more than a card or you hadnt this card in your deck"+utilities.time()+"\n",true);
                    }
                }
                callCollection(player);
                break;
            case "remove[cardName]":
                String removeCard=commond.next();
                if (administer.number(player,removeCard)>=1){
                    card card =administer.create(removeCard);
                    if (administer.contains(administer.minions,removeCard)){
                        player.currentHero.availableCardsM.remove(card);
                    }
                    else   if (administer.contains(administer.spells,removeCard)) {
                        player.currentHero.availableCardsS.remove(card);
                           }
                        else{
                            if (administer.contains(administer.weapens,removeCard)){
                                player.currentHero.availableCardsW.remove(card);
                        }
                    }
                    myLogger(st1,"you remove"+removeCard+"from your deck"+utilities.time()+"\n",true);

                }
                else {
                    System.out.println("your action is not impossible");
                    myLogger(st1,"you had not this card"+utilities.time()+"\n",true);
                }
                callCollection(player);
                break;
            case "help":
                System.out.println("pay attention about syntax its my danger system !!:)))");
                System.out.println("remove[cardName] from your hero hand  ");
                System.out.println("ls-a-heroes show all heroes and to string method of them");
                System.out.println("ls-m-hero show my hero and to string method of it");
                System.out.println("select[hero] is for selecting hero :)))   please dont write the name of hero in the beraket");
                System.out.println("add[cardName]  to your hero   please notice write the name of card in the beraket");
                callCollection(player);
                break;
            default:
                System.out.println("your commond was not recognized");
                callCollection(player);
        }
    }
    public static void callMenu(Player player) throws IOException {
        Date date=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        Scanner commond=new Scanner(System.in);
        System.out.println("\u2630"+"MENU");
        System.out.println("STORE   ");
        System.out.println("PLAY    ");
        System.out.println("COLLECTION  ");
        System.out.println("exit");
        System.out.println("delete");
        System.out.println("for go to each link type the name ");
        System.out.println("for help type help");
        switch (commond.next().toLowerCase()) {
            case "help":
                System.out.println(" for manage cards and heroes go to collections ");
                System.out.println("for sale and buy cards go to store");
                System.out.println(" begin is for go  to username and password page");
                System.out.println("also i have  exit commond for save your information ");
                System.out.println("you can delete your account by type delete");
                callMenu(player);
            case "store":
                callStore(player);
            case "play":
                callGround();
                break;

            case "collection":
                callCollection(player);
                break;
            case "delete":
                Scanner scan=new Scanner(System.in);
                System.out.println("you will delete your account  if you want type your password else type something");
                String sc=scan.next();
                if (sc.equals(player.password)){
                    String st=String.format("userJson\\%s.json",player.username+player.password);
                    String st1=String.format("%s.txt",player.username+player.password);
                    String st2=String.format("trash users\\%s.txt",player.username+player.password);
                    File file=new File(st);
                    File file1=new File(st1);
                    Scanner scanner=new Scanner(file1);
                    for (int i = 0; i < 3; i++) {
                        String temp=scanner.nextLine();
                        myLogger(st2,temp+"\n",true);
                    }
                    myLogger(st2,"DELETED_AT:"+utilities.time()+"\n"+"\n",true);
                    while (scanner.hasNext()){
                        String temp=scanner.nextLine();
                        myLogger(st2,temp+"\n",true);
                    }
                    file.delete();
                    scanner.close();
                    file1.delete();
                    System.exit(0);
                }
                else{
                System.out.println("something went wrong or you dont want to delete your account");
                callMenu(player);
            }
            break;

            case "begin":
                begin();
                break;
            case "exit":
               utilities.exit(player);
                break;
            default:
                System.out.println("your commond was not recognized");
                callMenu(player);
        }
        }

    public static void callStore(Player player) throws IOException {
        String st1=String.format("%s.txt",player.username+player.password);
        Scanner commond=new Scanner(System.in);
        System.out.println("STORE");
        System.out.println("menu");
        System.out.println("wallet");
        System.out.println("ls-s");
        System.out.println("ls-b");
        System.out.println("buy");
        System.out.println("sell");
        System.out.println("exit-a");
        System.out.println("exit");
        System.out.println("help");
        switch (commond.next()){
            case "menu":
               callMenu(player);
            case"wallet":
                System.out.println(player.diamond);
                myLogger(st1,"your wallet: "+player.diamond+ time()+"\n",true);
                callStore(player);
            case "ls-b":
                boolean is=false;
                myLogger(st1,"you use ls-b command  "+utilities.time()+"\n",true);
                for (String string:administer.cardNames){
                   if(administer.number(player,string)<=1){
                       is=true;
                       System.out.println(string);
                       myLogger(st1,string+",",true);

                   }
                    myLogger(st1,"\n",true);
                }
                if (!is){
                    System.out.println("you dont have any card to buy");
                    myLogger(st1," SORRY,you dont have any card to buy"+utilities.time()+"\n",true);

                }
                callStore(player);
                break;
            case"ls-s":
                ArrayList<card>canSell=new ArrayList<>();
                for (String name:administer.cardNames) {
                    for (Hero hero1:player.availableHeroes) {
                        for (Hero hero2 : player.availableHeroes) {
                            if (administer.number(hero1, name) >= 1 && administer.number(hero2, name) >= 1) {
                                canSell.add(administer.create(name));
                            }
                        }
                     }
                }
                if (canSell.size()>=1){
                    myLogger(st1,"ls-s command from store   "+utilities.time()+" \n",true);
                    for (card card :canSell) {
                        System.out.println(card.toString());
                    }
                }
                else {
                    System.out.println("we dont have any card to sell");
                    myLogger(st1,"ls-s command from store but there is nothing to sell   "+utilities.time()+" \n",true);
                }
                callStore(player);
                break;

            case "sell":
                System.out.println("enter the name of your card");
                String sell=commond.next();
                    for (Hero hero1:player.availableHeroes) {
                        for (Hero hero2:player.availableHeroes){
                            if (administer.number(hero1,sell)>=1&&administer.number(hero2,sell)>=1){
                                player.diamond+=administer.create(sell).getCost();
                                player.availableCardsW.remove(administer.create(sell));
                                player.availableCardsS.remove(administer.create(sell));
                                player.availableCardsM.remove(administer.create(sell));
                                myLogger(st1,"sell card: "+sell+"  "+utilities.time()+"\n",true);
                            }
                        }
                    }

                callStore(player);
                break;
            case "buy":
                System.out.println("please type of your card that you want to buy");
                int money=player.diamond;
                String buy=commond.next();
                if ( ((contains(administer.minions,buy))||(contains(administer.spells,buy)||( contains(administer.weapens,buy))) )&& administer.number(player, buy) <= 1) {
                if (administer.contains(administer.minions,buy)) {
                        Minion minion = (Minion) administer.create(buy);
                        int cost=minion.getCost();
                        if (player.diamond>=cost) {
//                            player.currentHero.availableCardsM.add(minion);
                            player.availableCardsM.add(minion);
                           player.diamond=money-cost;
                            myLogger(st1,"you buy "+minion.getName()+"\n",true);
                        }else{
                            System.out.println("you have not enough money");
                            myLogger(st1,"your money is not enough "+"\n",true);
                        }
                    } else if (administer.contains(administer.spells, buy)) {
                        spell spell = (spell) administer.create(buy);
                        int cost=spell.getCost();
                        if (player.getDiamond()>=cost) {
//                            player.currentHero.availableCardsS.add(spell);
                            player.availableCardsS.add(spell);
                            player.diamond=money-cost;
                            myLogger(st1,"you buy "+spell.getName()+"\n",true);
                        }
                        else {
                            System.out.println("you have not enough money");
                            myLogger(st1,"your money is not enough "+"\n",true);
                        }
                    } else if (administer.contains(administer.weapens, buy)) {
                        weapen weapen = (weapen) administer.create(buy);
                        int cost=weapen.getCost();
                        if (player.getDiamond()>=cost) {
//                            player.currentHero.availableCardsW.add(weapen);
                            player.availableCardsW.add(weapen);
                            player.diamond=money-cost;
                            myLogger(st1,"you buy "+weapen.getName()+"\n",true);
                        }
                        else {
                            System.out.println("you have not enough money");
                            myLogger(st1,"your money is not enough "+"\n",true);
                        }
                    }
                }
                else {
                    System.out.println("your command is not true  pay attention about syntax  or you have more than 2 cards from this");
                }
                callStore(player);
                break;
            case "exit-a":
                exit(player);

            case "exit":
                callMenu(player);
                break;
            case "help":
                System.out.println("menu  for go to menu");
                System.out.println("wallet for see your diamonds");
                System.out.println("ls-s cards that can be sold");
                System.out.println("ls-b  cards that can be bought");
                System.out.println("buy  a card");
                System.out.println("sell a card ");
                System.out.println("exit-a  from program with update ");
                System.out.println("exit for go to menu");
                System.out.println();
                callStore(player);
                break;
            default:
                System.out.println("your command was not recognized");
                callStore(player);
        }
    }
    public static void callGround(){

    }
    public static void main(String[] args) throws IOException {
        administer=new Administer();

        begin();


    }
}



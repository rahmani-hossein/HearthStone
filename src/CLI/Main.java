package CLI;

import charactor.Hero;
import charactor.Minion;
import charactor.card;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
   static Administer administer;
   static Player currentPlayer;
    public static void begin() throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        Date date=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
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
            String st1=String.format("%s.txt",user);
            File file=new File(st1);
            FileWriter fileWriter=new FileWriter(st1,true);
            fileWriter.flush();
           fileWriter.write("username: "+user+"\n");
           fileWriter.write("Created_at: "+ft.format(date)+"\n");
            fileWriter.write("password: "+pass+"\n");
           fileWriter.flush();
            fileWriter.close();
           //json file for users
            String st=String.format("userJson\\%s.json",user+pass);
            FileWriter fileWriter1=new FileWriter(st);
            // inicialize player
            ArrayList<card> availableCards=new ArrayList<>();
            ArrayList<Hero>availableHeros=new ArrayList<>();
            availableCards.add(administer.create("blazingBattlemage"));
            availableCards.add(administer.create("fireBlast"));
            Player player=new Player(user,pass,50,null,availableCards,availableHeros);

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
                String account=String.format("%s.txt",user);
                FileWriter fileWriter=new FileWriter(account,true);
                fileWriter.write("signed in at:"+ft.format(date)+"\n");
                fileWriter.flush();
                objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                Player player=objectMapper.readValue(file,Player.class);
                FileWriter fileWriter1=new FileWriter(st);
                fileWriter1.flush();

               player=currentPlayer;
                fileWriter.close();
                fileWriter1.flush();
                fileWriter1.close();
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
    public static void  callCollection(){

    }
    public static void callMenu(Player player) throws IOException {
        Date date=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        Scanner commond=new Scanner(System.in);
        System.out.println("\u2630"+"MENU");
        System.out.println("STORE   ");
        System.out.println("PLAY    ");
        System.out.println("COLLECTION  ");
        System.out.println("for go to each link type the name ");
        System.out.println();
        switch (commond.next().toLowerCase()){
            case "store":
               callStore();
            case "play":
                callGround();
                break;

            case "collection":
                callCollection();
                break;
            case "exit":
                System.out.println("wait for update");
                ObjectMapper objectMapper=new ObjectMapper();
                String account=String.format("userJson\\%s.json",player.username+player.password);
                FileWriter fileWriter1=new FileWriter(account);
                Player temp=currentPlayer;
                objectMapper.writeValue(fileWriter1,temp);
                fileWriter1.close();
                String account2=String.format("%s.txt",player.username);
                FileWriter file2=new FileWriter(account2,true);

                file2.write("signed up at:"+ft.format(date));
                file2.flush();
                file2.close();
                System.out.println("see you soon");
                System.exit(0);
            default:
                System.out.println("your commond was not recognized");
                callMenu(player);
        }
    }
    public static void callStore() throws FileNotFoundException {
        Date date=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        Scanner commond=new Scanner(System.in);
        System.out.println("you are in store");
        switch (commond.next()){
            case "menu":
               return;
            case "exit-a":
                System.exit(0);
            case  "ls -a -heros":
                System.out.println("all");
                System.out.println(ft.format(date));
                break;
            case "ali":
                System.out.println(ft.format(date));
                break;
        }
    }
    public static void callGround(){

    }
    public static void main(String[] args) throws IOException {
        administer=new Administer();
       String sc="blazingBattlemage";
        System.out.println( administer.create(sc).toString());
        begin();


    }
}



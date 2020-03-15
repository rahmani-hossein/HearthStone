package CLI;

import charactor.Hero;
import charactor.Minion;
import charactor.card;
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
            // inicialize player
            ArrayList<card> availableCards=new ArrayList<>();
            ArrayList<Hero>availableHeros=new ArrayList<>();
            availableCards.add(administer.create("blazingBattlemage"));
            availableCards.add(administer.create("fireHawk"));
            Player player=new Player(user,pass,50,null,availableCards,availableHeros);

            // create txt file for user
            String st=String.format("%s.txt",user);
            FileWriter fileWriter=new FileWriter(st,true);
            fileWriter.flush();
            PrintWriter printWriter=new PrintWriter(fileWriter);
            printWriter.write("username: "+user+"\n");
            printWriter.write("Created_at"+ft.format(date)+"\n");
            printWriter.write("password: "+pass);
            printWriter.flush();
           //json file for users
            String stjson=String.format("userJson\\%s.json",user+pass);
            FileWriter fileWriterj=new FileWriter(stjson,true);
            fileWriterj.flush();
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValue(fileWriterj,player);


            callMenu();
        }else{
            System.out.println("enter your user name");
            String user=scanner.next();
            System.out.println("enter your password");
            String pass=scanner.next();
            String st=String.format("userJson\\%s.json",user+pass);
            File file= new File(st);
            if (file.exists()){
                String account=String.format("%s.txt",user);
                PrintWriter printWriter=new PrintWriter(account);
                printWriter.write("signed in at:"+ft.format(date));
                printWriter.flush();
                ObjectMapper objectMapper=new ObjectMapper();
                Player player=objectMapper.readValue(file,Player.class);
                currentPlayer=player;
                callMenu();
            }else {
                System.out.println("you dont have account please try again and  build an account ");
                begin();

            }
        }
    }
    public static void  callCollection(){

    }
    public static void callMenu() throws IOException {
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
                String account=String.format("userJson\\%s.json",currentPlayer.getUsername()+currentPlayer.getPassword());
                File file=new File(account);
                Player temp=currentPlayer;
                objectMapper.writeValue(file,temp);
                PrintWriter printWriter=new PrintWriter(file);
                printWriter.write("signed up at:"+ft.format(date));
                printWriter.flush();
                currentPlayer=null;
                System.out.println("see you soon");
                System.exit(0);
            default:
                System.out.println("your commond was not recognized");
                callMenu();
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
//       String sc="fireBlast";
//        System.out.println( administer.create(sc).toString());
        begin();


    }
}



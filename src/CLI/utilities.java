package CLI;

import model.Minion;
import model.card;
import model.spell;
import model.weapen;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class utilities {
    public static String time(){
        Date date=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        return ft.format(date);
    }
    public static int know(ArrayList<card> me, card cardgiven){
        int k=0;
        for (card card :me) {
            if (card.getName().equalsIgnoreCase(cardgiven.getName())){
                k++;
            }
        }
        return k;
    }
    public static Set toSetM(ArrayList<Minion> me){
        Set<Minion> hSet = new HashSet<Minion>();
        for (card card:me) {
            hSet.add((Minion) card);
        }
        return hSet;
    }
    public static Set toSetS(ArrayList<spell> me){
        Set<spell> hSet = new HashSet<spell>();
        for (card card:me) {
            hSet.add((spell) card);
        }
        return hSet;
    }
    public static Set toSetW(ArrayList<weapen> me){
        Set<weapen> hSet = new HashSet<weapen>();
        for (card card:me) {
            hSet.add((weapen) card);
        }
        return hSet;
    }

    public  static boolean contains(String[] st,String name){
        for (int i = 0; i <st.length ; i++) {
            if (st[i].equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static int firstIndex(String[] names,String name){
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])){
                return i;
            }
        }
        return -1;
    }
//    public static void exit(Player player) throws IOException {
////        System.out.println("wait for update");
////        ObjectMapper objectMapper = new ObjectMapper();
////        String account = String.format("userJson\\%s.json", player.username + player.password);
////        FileWriter fileWriter = new FileWriter(account, false);
////        objectMapper.writeValue(fileWriter, player);
////        fileWriter.close();
////        String account2 = String.format("%s.tt", player.username + player.password);
////        FileWriter file2 = new FileWriter(account2, true);
////        file2.write("signed up at:" + time());
////        file2.flush();
////        file2.close();
////        System.out.println("see you soon");
////        System.exit(0);
////}
}

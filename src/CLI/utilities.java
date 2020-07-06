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
//    public static int know(ArrayList<card> me, card cardgiven){
//        int k=0;
//        for (card card :me) {
//            if (card.getName().equalsIgnoreCase(cardgiven.getName())){
//                k++;
//            }
//        }
//        return k;
//    }
//    public static Set toSetM(ArrayList<Minion> me){
//        Set<Minion> hSet = new HashSet<Minion>();
//        for (card card:me) {
//            hSet.add((Minion) card);
//        }
//        return hSet;
//    }
//    public static Set toSetS(ArrayList<spell> me){
//        Set<spell> hSet = new HashSet<spell>();
//        for (card card:me) {
//            hSet.add((spell) card);
//        }
//        return hSet;
//    }
//    public static Set toSetW(ArrayList<weapen> me){
//        Set<weapen> hSet = new HashSet<weapen>();
//        for (card card:me) {
//            hSet.add((weapen) card);
//        }
//        return hSet;
//    }

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

}

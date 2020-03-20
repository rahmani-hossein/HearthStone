package CLI;

import charactor.*;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Administer {

    public Administer(){

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

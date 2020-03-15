package CLI;

import charactor.Minion;
import charactor.card;
import charactor.spell;
import charactor.weapen;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Administer {

    public Administer(){

    }
    public



   String[] minions= new String[]{"blazingBattlemage", "hotAirballoon", "fireHawk", "evasiveChimaera", "amberWatcher", "dreadScale","veranus"};
    String[] spells= new String[]{"decimation","malygosFireball","malygosFlamestrike","friendlySmith","fireBlast"};
    String[]weapens= new String[]{"bloodRazor","fieryWaraxe","bloodClaw","assassinBlade","arcaniteReaper","cursedBlade"};
   public boolean contains(String[] st,String name){
       for (int i = 0; i <st.length ; i++) {
           if (st[i].equalsIgnoreCase(name)){
               return true;
           }
       }
       return false;
   }
    public card create(String name) throws IOException {
        String st=String.format("Json\\%s.json",name);
        FileReader fileReader=new FileReader(st);
        ObjectMapper objectMapper=new ObjectMapper();
        if (contains(minions,name)) {
            return objectMapper.readValue(fileReader, Minion.class);
        }else if (contains(spells,name)){
            return objectMapper.readValue(fileReader, spell.class);
        }else if (contains(weapens,name)){
            return objectMapper.readValue(fileReader, weapen.class);
        }
        return null;
    }
}

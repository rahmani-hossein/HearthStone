package charactor;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
//@JsonIgnoreProperties({})
public class Hero {
    int HP;
    String name;
    String showHeroPower;
    String showSpecialpower;
   public ArrayList<Minion>availableCardsM=new ArrayList<>();
   public ArrayList<weapen>availableCardsW=new ArrayList<>();
    public ArrayList<spell>availableCardsS=new ArrayList<>();
    public final static int vipCards=2;
    static String[] namesHero={"mage","rouge","warlock"};
    static String [] namesVipCards;
    public static String[] getNamesVipCards() {
        return namesVipCards;
    }

    public static void setNamesVipCards(String[] namesVipCards) {
        Hero.namesVipCards = namesVipCards;
    }


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowHeroPower() {
        return showHeroPower;
    }

    public void setShowHeroPower(String showHeroPower) {
        this.showHeroPower = showHeroPower;
    }

    public String getShowSpecialpower() {
        return showSpecialpower;
    }

    public void setShowSpecialpower(String showSpecialpower) {
        this.showSpecialpower = showSpecialpower;
    }

    public Hero(int HP, String name, String showHeroPower, String showSpecialpower,ArrayList availableCardsM,ArrayList availableCardsS,ArrayList availableCardsW) {
        this.HP = HP;
        this.name = name;
        this.showHeroPower = showHeroPower;
        this.showSpecialpower = showSpecialpower;
        this.availableCardsM=availableCardsM;
        this.availableCardsS=availableCardsS;
        this.availableCardsW=availableCardsW;
        if (this.name.equals("mage")){
            this.namesVipCards= new String[]{"fireBlast", "polymorph"};
        }
        else  if (this.name.equals("warlock")){
            this.namesVipCards= new String[]{"dreadScale", "fireHawk"};
        }
        else {
            this.namesVipCards= new String[]{"friendlySmith", "fierryWaraxe"};
        }
    }

    public ArrayList<Minion> getAvailableCardsM() {
        return availableCardsM;
    }

    public void setAvailableCardsM(ArrayList<Minion> availableCardsM) {
        this.availableCardsM = availableCardsM;
    }

    public ArrayList<weapen> getAvailableCardsW() {
        return availableCardsW;
    }

    public void setAvailableCardsW(ArrayList<weapen> availableCardsW) {
        this.availableCardsW = availableCardsW;
    }

    public ArrayList<spell> getAvailableCardsS() {
        return availableCardsS;
    }

    public void setAvailableCardsS(ArrayList<spell> availableCardsS) {
        this.availableCardsS = availableCardsS;
    }

    @Override
    public String toString() {
        return "("+this.getName()+","+this.getHP()+","+this.showHeroPower+","+this.showSpecialpower+")";
    }

    public Hero(){

    }
    public void  heroPower(){

    }
    public void specialPower(){

    }
}

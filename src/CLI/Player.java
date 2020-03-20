package CLI;

import charactor.*;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.ArrayList;

public class Player {
    String username;
    String password;
   public int diamond=80;
    Hero currentHero;
   public ArrayList<Minion>availableCardsM=new ArrayList<>();
    public ArrayList<weapen>availableCardsW=new ArrayList<>();
   public ArrayList<spell>availableCardsS=new ArrayList<>();
    public ArrayList<Hero>availableHeroes=new ArrayList<>();
    public Player(String username, String password, int diamond, Hero currentHero, ArrayList<spell> availableCardsS, ArrayList<Minion>availableCardsM, ArrayList<weapen>availableCardsW, ArrayList<Hero> availableHeroes) {
        this.username = username;
        this.password = password;
        this.diamond = 80;
        this.currentHero = currentHero;
        this.availableCardsW = availableCardsW;
        this.availableCardsM = availableCardsM;
        this.availableCardsS = availableCardsS;
        this.availableHeroes = availableHeroes;
    }
    public Player(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(Hero currentHero) {
        this.currentHero = currentHero;
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

    public ArrayList<Hero> getAvailableHeroes() {
        return availableHeroes;
    }

    public void setAvailableHeroes(ArrayList<Hero> availableHeroes) {
        this.availableHeroes = availableHeroes;
    }


}

package CLI;

import charactor.Hero;
import charactor.card;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Player {
    String username;
    String password;
    int diamond=60;
    Hero currentHero;
    ArrayList<card>availableCards=new ArrayList<>();
    ArrayList<Hero>availableHeroes=new ArrayList<>();
    public Player(String username, String password, int diamond, Hero currentHero, ArrayList<card> availableCards, ArrayList<Hero> availableHeroes) {
        this.username = username;
        this.password = password;
        this.diamond = diamond;
        this.currentHero = currentHero;
        this.availableCards = availableCards;
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

    public ArrayList<card> getAvailableCards() {
        return availableCards;
    }

    public void setAvailableCards(ArrayList<card> availableCards) {
        this.availableCards = availableCards;
    }

    public ArrayList<Hero> getAvailableHeroes() {
        return availableHeroes;
    }

    public void setAvailableHeroes(ArrayList<Hero> availableHeroes) {
        this.availableHeroes = availableHeroes;
    }


}

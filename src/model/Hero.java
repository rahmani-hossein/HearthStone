package model;

import java.util.ArrayList;
import java.util.Map;

//@JsonIgnoreProperties({})
public abstract class Hero {
    private int HP;
    private String name;
    private String showHeroPower;
    private String showSpecialpower;
    private int maxHp;
    private boolean defence = false;
    public final static int vipCards = 2;
    static String[] namesHero = {"mage", "rouge", "warlock", "hunter", "priest"};
    String[] namesVipCards;

    public Hero(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.HP= (int) map.get("HP");
        this.maxHp= (int) map.get("HP");
        this.showSpecialpower= (String) map.get("showSpecialPower");
        this.showHeroPower= (String) map.get("showHeroPower");

    }

    public Hero(int HP, String name, String showHeroPower, String showSpecialpower) {
        this.HP = HP;
        this.name = name;
        this.showHeroPower = showHeroPower;
        this.showSpecialpower = showSpecialpower;
        if (this.name.equals("mage")) {
            this.namesVipCards = new String[]{"fireBlast", "polymorph"};
        } else if (this.name.equals("warlock")) {
            this.namesVipCards = new String[]{"dreadScale", "fireHawk"};
        } else if (this.name.equals("hunter")) {
            this.namesVipCards = new String[]{"swampKingDred", "arcaneShot"};
        } else if (this.name.equals("priest")) {
            this.namesVipCards = new String[]{"hightPriestAmet", "flamestrike"};

        } else if (this.name.equals("rouge")) {
            this.namesVipCards = new String[]{"friendlySmith", "fierryWaraxe"};
        }
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

    @Override
    public String toString() {
        return "(" + this.getName() + "," + this.getHP() + "," + this.showHeroPower + "," + this.showSpecialpower + ")";
    }


    public abstract void heroPower();

    public void specialPower() {

    }
}

package model;


import Interfaces.Attackable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.heroPackage.*;

import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = hunter.class, name = "hunter"),
        @JsonSubTypes.Type(value = mage.class, name = "mage"),
        @JsonSubTypes.Type(value = warlock.class, name = "warlock"),
        @JsonSubTypes.Type(value = priest.class, name = "priest"),
        @JsonSubTypes.Type(value = rouge.class, name = "rouge")

})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Hero implements Attackable {
    protected int HP;
    protected String name;
    protected String showHeroPower;
    protected String showSpecialpower;
    protected int maxHp;
    protected boolean defence = false;
    public final static int vipCards = 2;

    static String[] namesHero = {"mage", "rouge", "warlock", "hunter", "priest"};
    String[] namesVipCards;

    public Hero(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.HP = (int) map.get("HP");
        this.maxHp = (int) map.get("HP");
        this.showSpecialpower = (String) map.get("showSpecialPower");
        this.showHeroPower = (String) map.get("showHeroPower");

    }

    public Hero() {

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

    @Override
    public void minusHealth(int minus) {
        if (this.HP < minus) {
            this.HP = 0;
        } else {
            this.HP -= minus;
        }
    }

    @Override
    public void plusHealth(int plus) {
        if (this.HP + plus > maxHp) {
            this.HP = maxHp;
        } else {
            this.HP += plus;
        }
    }


    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public boolean isDefence() {
        return defence;
    }

    public void setDefence(boolean defence) {
        this.defence = defence;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }


    @Override
    public String BedeName() {
        return this.name;
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

    public  abstract void specialPower() ;
}

package model;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.minionPackage.*;

import java.util.ArrayList;
import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = amberWatcher.class, name = "amberWatcher"),
        @JsonSubTypes.Type(value = blazingBattlemage.class, name = "blazingBattlemage"),
        @JsonSubTypes.Type(value = curioCollector.class, name = "curioCollector"),
        @JsonSubTypes.Type(value = dreadScale.class, name = "dreadScale"),
        @JsonSubTypes.Type(value = evasiveChimaera.class, name = "evasiveChimaera"),
        @JsonSubTypes.Type(value = fireHawk.class, name = "fireHawk"),
        @JsonSubTypes.Type(value = highPriestAmet.class, name = "highPriestAmet"),
        @JsonSubTypes.Type(value = hotAirballon.class, name = "hotAirballon"),
        @JsonSubTypes.Type(value = sathrovarr.class, name = "sathrovarr"),
        @JsonSubTypes.Type(value = securityRover.class, name = "securityRover"),
        @JsonSubTypes.Type(value = swampKingDred.class, name = "swampKingDred"),
        @JsonSubTypes.Type(value = tombWarden.class, name = "tombWarden"),
        @JsonSubTypes.Type(value = veranus.class, name = "veranus"),
        @JsonSubTypes.Type(value = loctus.class,name = "loctus")
})
@JsonTypeName("Minion")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Minion extends card implements Attackable {
    protected int damage;
    protected int health;
    protected int liveInRound = 0;
    protected int maxHp;
    protected boolean divineSheild=false;
    protected int attackInRound=0;

    public Minion(int damage, int health, int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle, boolean turny, int cost, boolean poisonous, boolean discover, boolean rush, boolean taunt) {
        super(manaCost, name, description, type, heroClass, rarity, battleCry, summon, deathRattle, turny, cost, poisonous, discover, rush, taunt);
        this.damage = damage;
        this.health = health;
    }

    public Minion() {
        super();
    }

    public Minion(Map<String, Object> map) {
        super(map);
        this.damage = (int) map.get("damage");
        this.health = (int) map.get("health");
        this.maxHp= (int) map.get("health");
       this.liveInRound= (int) map.get("liveInRound");
       if (rush){
           this.liveInRound++;
       }
    }

    @Override
    public abstract void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target);


    @Override
    public String toString() {
        return "(name:" + this.name + "," + "manaCost:" + this.manaCost + ",health:" + this.health + ",damage:" + this.damage + "description:" + this.description + ")";
    }

    public void increaseRound() {
        this.liveInRound++;
    }

    @Override
    public void minusHealth(int minus) {
            if (this.health < minus) {
                this.health = 0;
            } else {
                this.health -= minus;
            }
    }

    @Override
    public void plusHealth(int plus) {
        if (this.health + plus > maxHp) {
            this.health = maxHp;
        } else {
            this.health += plus;
        }
    }

    @Override
    public String BedeName() {
        return name;
    }





    //getter& setter


    public int getAttackInRound() {
        return attackInRound;
    }

    public void setAttackInRound(int attackInRound) {
        this.attackInRound = attackInRound;
    }

    public boolean isDivineSheild() {
        return divineSheild;
    }

    public void setDivineSheild(boolean divineSheild) {
        this.divineSheild = divineSheild;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getLiveInRound() {
        return liveInRound;
    }

    public void setLiveInRound(int liveInRound) {
        this.liveInRound = liveInRound;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {

            return health;
    }

    public void setHealth(int health) {
            this.health = health;

    }
}

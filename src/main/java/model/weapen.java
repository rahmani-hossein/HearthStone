package model;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.weapenPackage.*;

import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
       visible = true)
@JsonSubTypes({

                @JsonSubTypes.Type(value = arcaniteReaper.class,name = "arcaniteReaper"),
                @JsonSubTypes.Type(value = ashBringer.class,name = "ashBringer"),
                @JsonSubTypes.Type(value = assassinBlade.class,name = "assassinBlade"),
                @JsonSubTypes.Type(value = bloodClaw.class,name = "bloodClaw"),
                @JsonSubTypes.Type(value = bloodFury.class,name = "bloodFury"),
                @JsonSubTypes.Type(value = bloodRazor.class,name = "bloodRazor"),
                @JsonSubTypes.Type(value = battleAxe.class,name = "battleAxe"),
                @JsonSubTypes.Type(value = dragonClaw.class,name = "dragonClaw"),
                @JsonSubTypes.Type(value = fierywaraxe.class,name = "fierywaraxe"),
                @JsonSubTypes.Type(value = gearBlade.class,name = "gearBlade")

})
@JsonTypeName("weapen")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class weapen extends card {
  protected   int durability;
   protected int damage;

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public weapen() {
        super();
    }
    public weapen(Map<String,Object>map){
        super(map);
        this.durability= (int) map.get("durability");
        this.damage= (int) map.get("damage");
    }

    @Override
    public  void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }


    public weapen(int durability, int damage, int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle,  boolean turny, int cost, boolean poisonous, boolean discover, boolean rush, boolean taunt) {
        super(manaCost, name, description, type, heroClass, rarity, battleCry, summon, deathRattle, turny, cost, poisonous, discover, rush, taunt);
        this.durability = durability;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "(name:" + this.name + "," + "manaCost:" + this.manaCost + ",durability:" + this.durability + ",damage:" + this.damage + ",description:" + this.description + ")";
    }
}

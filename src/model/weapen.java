package model;

import Interfaces.Visitor;

import java.util.ArrayList;
import java.util.Map;

public abstract class weapen extends card {
    int durability;
    int damage;

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
    public abstract void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) ;


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

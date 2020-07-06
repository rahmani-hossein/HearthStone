package model;

import Interfaces.Visitor;

import java.util.ArrayList;
import java.util.Map;

public abstract class Minion extends card {
    int damage;
    int health;
    int liveInRound=0;

    public Minion(int damage, int health, int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle, boolean turny, int cost, boolean poisonous, boolean discover, boolean rush, boolean taunt) {
        super(manaCost, name, description, type, heroClass, rarity, battleCry, summon, deathRattle, turny, cost, poisonous, discover, rush, taunt);
        this.damage = damage;
        this.health = health;
    }

    public Minion() {
        super();
    }

    public Minion(Map<String,Object> map){
        super(map);
        this.damage= (int) map.get("damage");
        this.health= (int) map.get("health");
    }

    @Override
    public abstract void accept(Visitor visitor,  GamePlayer freind, GamePlayer enemy, card target) ;


    @Override
    public String toString() {
        return "(name:" + this.name + "," + "manaCost:" + this.manaCost + ",health:" + this.health + ",damage:" + this.damage + "description:" + this.description + ")";
    }

    public void increaseRound(){
        this.liveInRound++;
    }

    //getter& setter


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

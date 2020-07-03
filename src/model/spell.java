package model;

import Interfaces.Visitor;

import java.util.ArrayList;
import java.util.Map;

public abstract class spell extends card {
    public spell(int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle, boolean turny, int cost, boolean poisonous, boolean discover, boolean rush, boolean taunt) {
        super(manaCost, name, description, type, heroClass, rarity, battleCry, summon, deathRattle, turny, cost, poisonous, discover, rush, taunt);

    }

    public spell() {
        super();
    }

    public spell(Map<String,Object> map){
        super(map);
    }

    @Override
    public abstract void accept(Visitor visitor, ArrayList<card> friendlyDeck, ArrayList<card> friendlyHand, ArrayList<card> enemyDeck, ArrayList<card> enemyHand, ArrayList<card> friendlyGround, ArrayList<card> enemyGround, card target);


    @Override
    public String toString() {
        return "(name:" + this.name + "," + "manaCost:" + this.manaCost + ",description:" + this.description + ")";
    }
}

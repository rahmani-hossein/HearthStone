package model;

import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.MINIMAL_CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({

})
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
    public abstract void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) ;


    @Override
    public String toString() {
        return "(name:" + this.name + "," + "manaCost:" + this.manaCost + ",description:" + this.description + ")";
    }
}

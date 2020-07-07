package model;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.spellPackage.*;
import model.weapenPackage.*;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
       visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = arcaneShot.class,name = "arcaneShot"),
        @JsonSubTypes.Type(value = bookOfSpecters.class,name = "bookOfSpecters"),
        @JsonSubTypes.Type(value = decimation.class,name = "decimation"),
        @JsonSubTypes.Type(value = divineHymn.class,name = "divineHymn"),
        @JsonSubTypes.Type(value = fireBlast.class,name = "fireBlast"),
        @JsonSubTypes.Type(value = flamestrike.class,name = "flamestrike"),
        @JsonSubTypes.Type(value = friendlySmith.class,name = "friendlySmith"),
        @JsonSubTypes.Type(value = learnDraconic.class,name = "learnDraconic"),
        @JsonSubTypes.Type(value = malygosFireball.class,name = "malygosFireball"),
        @JsonSubTypes.Type(value = malygosFlamestrike.class,name = "malygosFlamestrike"),
        @JsonSubTypes.Type(value = pharaohsBlessing.class,name = "pharaohsBlessing"),
        @JsonSubTypes.Type(value = polymorph.class,name = "polymorph"),
        @JsonSubTypes.Type(value = sprint.class,name = "sprint"),
        @JsonSubTypes.Type(value = strengthInNumbers.class,name = "strengthInNumbers"),
        @JsonSubTypes.Type(value = swarmOfLocusts.class,name = "swarmOfLocusts")
})
@JsonTypeName("spell")
@JsonIgnoreProperties(ignoreUnknown = true)
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
    public abstract void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) ;


    @Override
    public String toString() {
        return "(name:" + this.name + "," + "manaCost:" + this.manaCost + ",description:" + this.description + ")";
    }
}

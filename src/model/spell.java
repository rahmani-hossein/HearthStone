package model;

public class spell extends card {
    public spell(int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle, boolean turny, int cost, boolean poisonous, boolean discover, boolean rush, boolean taunt) {
        super(manaCost, name, description, type, heroClass, rarity, battleCry, summon, deathRattle, turny, cost, poisonous, discover, rush, taunt);

    }

    public spell() {
        super();
    }

    @Override
    public String toString() {
        return "(name:" + this.name + "," + "manaCost:" + this.manaCost + ",description:" + this.description + ")";
    }
}

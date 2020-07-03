package model;

import Interfaces.Visitable;
import Interfaces.Visitor;

import java.util.ArrayList;
import java.util.Map;

public abstract class card implements Visitable {
    int manaCost;
    String name;
    String description;
    String type;
    String heroClass;
    String rarity;
    boolean battleCry;
    boolean summon;
    boolean deathRattle;
    boolean turny;
    int cost;
    boolean poisonous;
    boolean discover;
    boolean rush;
    boolean taunt;

    public boolean isTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isTurny() {
        return turny;
    }

    public boolean isRush() {
        return rush;
    }

    public void setRush(boolean rush) {
        this.rush = rush;
    }

    public card(int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle, boolean turny, int cost, boolean poisonous, boolean discover, boolean rush, boolean taunt) {
        this.manaCost = manaCost;
        this.name = name;
        this.description = description;
        this.type = type;
        this.heroClass = heroClass;
        this.rarity = rarity;
        this.battleCry = battleCry;
        this.summon = summon;
        this.deathRattle = deathRattle;
        this.turny = turny;
        this.cost = cost;
        this.poisonous = poisonous;
        this.discover = discover;
        this.rush = rush;
        this.taunt = taunt;
    }

    public card() {
    }

    public card(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.cost = (int) map.get("cost");
        this.manaCost = (int) map.get("manaCost");
        this.type = (String) map.get("type");
        this.description = (String) map.get("description");
        this.rarity = (String) map.get("rarity");
        this.battleCry = (boolean) map.get("battleCry");
        this.summon = (boolean) map.get("summon");
        this.turny = (boolean) map.get("turny");
        this.deathRattle = (boolean) map.get("deathRattle");
        this.heroClass = (String) map.get("heroClass");
        this.poisonous = (boolean) map.get("poisonous");
        this.discover = (boolean) map.get("discover");
        this.rush = (boolean) map.get("rush");
        this.taunt= (boolean) map.get("taunt");
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public boolean isBattleCry() {
        return battleCry;
    }

    public void setBattleCry(boolean battleCry) {
        this.battleCry = battleCry;
    }

    public boolean isSummon() {
        return summon;
    }

    public void setSummon(boolean summon) {
        this.summon = summon;
    }

    public boolean isDeathRattle() {
        return deathRattle;
    }

    public void setDeathRattle(boolean deathRattle) {
        this.deathRattle = deathRattle;
    }

    public void setTurny(boolean turny) {
        this.turny = turny;
    }

    public boolean getTurny() {
        return turny;
    }

    public boolean isPoisonous() {
        return poisonous;
    }

    public void setPoisonous(boolean poisonous1) {
        this.poisonous = poisonous1;
    }

    public boolean isDiscover() {
        return discover;
    }

    public void setDiscover(boolean discover) {
        this.discover = discover;
    }


    public abstract void accept(Visitor visitor, ArrayList<card> friendlyDeck, ArrayList<card> friendlyHand, ArrayList<card> enemyDeck, ArrayList<card> enemyHand, ArrayList<card> friendlyGround, ArrayList<card> enemyGround, card target) ;


//    @Override
//    public int compareTo(card o) {
//        return 0;
//    }
}

package charactor;

public class weapen  extends card{
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

    public weapen(){
        super();

    }
    public weapen(int durability,int damage,int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle, boolean isDescription,boolean turny,int cost,boolean poisonous,boolean discover) {
        super( manaCost,  name,  description,  type,  heroClass,  rarity,  battleCry, summon,  deathRattle,  isDescription,turny,cost,poisonous,discover);
        this.durability=durability;
        this.damage=damage;
    }
    @Override
    public String toString(){
        return "(name:"+this.name+","+"manaCost:"+this.manaCost+",durability:"+this.durability+",damage:"+this.damage+",description:"+this.description+")";
    }
}

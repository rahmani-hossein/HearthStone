package charactor;

public class spell extends card{
    public spell(int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle, boolean isDescription,boolean turny,int cost,boolean poisonous,boolean discover) {
        super( manaCost,  name,  description,  type,  heroClass,  rarity,  battleCry, summon,  deathRattle,  isDescription,turny,cost,poisonous,discover);

    }
    public spell(){
        super();
    }
    @Override
    public String toString(){
        return  "(name:"+this.name+","+"manaCost:"+this.manaCost+",description:"+this.description+")";
    }
}

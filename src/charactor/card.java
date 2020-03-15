package charactor;

public  class card {
    int manaCost;
    String name;
    String description;
    String type;
    String heroClass;
    String rarity;
    boolean battleCry;
    boolean summon;
    boolean deathRattle;
    boolean isDescription;
    boolean turny;
    int cost;
    boolean poisonous;
    boolean discover;



    public card(int manaCost, String name, String description, String type, String heroClass, String rarity, boolean battleCry, boolean summon, boolean deathRattle, boolean isDescription, boolean turny, int cost, boolean poisonous, boolean discover) {
        this.manaCost = manaCost;
        this.name = name;
        this.description = description;
        this.type = type;
        this.heroClass = heroClass;
        this.rarity = rarity;
        this.battleCry = battleCry;
        this.summon = summon;
        this.deathRattle = deathRattle;
        this.isDescription = isDescription;
        this.turny=turny;
        this.cost=cost;
        this.poisonous=poisonous;
        this.discover=discover;
    }

    public card() {
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

    public boolean getIsDescription() {
        return isDescription;
    }
    public void setIsDescription(boolean isDescription){
        this.isDescription=isDescription;
    }
    public void setTurny(boolean turny){
        this.turny=turny;
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



}

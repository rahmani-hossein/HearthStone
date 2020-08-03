package logic;

import java.util.HashMap;

public class Constans {

     //we want to loadConfig; and then we dont design this class singelton;


    public Constans(){

    }

    private int cardWidth ;
    private int cardHeigth ;
    private int sizeX ;
    private int sizeY ;
    private int space ;
    private int panelHeight ;
    private int panelWidth ;
    private int handSize ;
    private int groundSize;
    private int groundSpace;
    private int handSpace ;
    private int maxYGame ;
    private int minYGame ;
    private int friendHeroY;
    private int GroundX;
    private int friendGroundY ;
    private int freindHeroX ;
    private int enemyHeroY;
    private int enemyHeroX;
    private int enenmyGroundY;
    private int manaX;
    private int friendManaY;
    private int enemyManaY;
    private int noteSize;

    private String[] minionEkhtiari = new String[6];
    private String[] spellEkhtiari = new String[6];
    private String[] weapenEkhtiari = new String[9];
    private String[] minions = new String[13];
    private String[] spells = new String[14];
    private String[] weapens = new String[10];
    private String[] cardNames =new String[37];
    private int[] cost = new int[37];
    private HashMap<String, Integer> costsMap = new HashMap<>();
    private HashMap<String, String> types = new HashMap<>();
    private HashMap<String,Integer> target = new HashMap<>();
    private String[] rarity = new String[4];
    private String[] HeroClass = new String[6];
    private String[] warlock = new String[2];
    private String[] mage = new String[2];
    private String[] rouge = new String[2];
    private String[] hunter = new String[2];
    private String[] priest =new String[2];
    private String[] heros = new String[2];
    private String[] passives = new String[5];

    public void initFill() {
       fillCostMap();
       fillTargetMap();
    }
    public void fillCostMap(){
        for (int i = 0; i < cardNames.length; i++) {
            costsMap.put(cardNames[i], cost[i]);
        }
        for (int i = 0; i < cardNames.length; i++) {
            if (i <= 12) {
                types.put(cardNames[i], "minion");
            } else if (i <= 26) {
                types.put(cardNames[i], "spell");

            } else {
                types.put(cardNames[i], "weapen");

            }
        }
    }

    public void fillTargetMap(){
        target.put("blazingBattlemage",0);
        target.put("hotAirballon",0);
        target.put("fireHawk",0);
        target.put("evasiveChimaera",0);
        target.put("amberWatcher",0);
        target.put("dreadScale",0);
        target.put("veranus",0);
        target.put("curioCollector",0);
        target.put("highPriestAmet",0);
        target.put("sathrovarr",1);
        target.put("securityRover",0);
        target.put("swampKingDred",0);
        target.put("tombWarden",0);
        target.put("decimation",0);
        target.put("malygosFireball",2);
        target.put("malygosFlamestrike",0);
        target.put("friendlySmith",0);
        target.put("fireBlast",2);
        target.put("arcaneShot",2);
        target.put("bookOfSpecters",0);
        target.put("flamestrike",0);
        target.put("learnDraconic",0);
        target.put("pharaohsBlessing",1);
        target.put("polymorph",2);
        target.put("sprint",0);
        target.put("strengthInNumbers",0);
        target.put("swarmOfLocusts",0);
        target.put("divineHymn",0);
        target.put("bloodRazor",0);
        target.put("fierywaraxe",0);
        target.put("bloodClaw",0);
        target.put("assassinBlade",0);
        target.put("arcaniteReaper",0);
        target.put("battleAxe",0);
        target.put("gearBlade",0);
        target.put("bloodFury",0);
        target.put("dragonClaw",0);
        target.put("ashBringer",0);
    }


    public int getNoteSize() {
        return noteSize;
    }

    public void setNoteSize(int noteSize) {
        this.noteSize = noteSize;
    }

    public int getManaX() {
        return manaX;
    }

    public void setManaX(int manaX) {
        this.manaX = manaX;
    }

    public int getFriendManaY() {
        return friendManaY;
    }

    public void setFriendManaY(int friendManaY) {
        this.friendManaY = friendManaY;
    }

    public int getEnemyManaY() {
        return enemyManaY;
    }

    public void setEnemyManaY(int enemyManaY) {
        this.enemyManaY = enemyManaY;
    }

    public HashMap<String, Integer> getTarget() {
        return target;
    }

    public void setTarget(HashMap<String, Integer> target) {
        this.target = target;
    }

    public int getFreindHeroX() {
        return freindHeroX;
    }

    public void setFreindHeroX(int freindHeroX) {
        this.freindHeroX = freindHeroX;
    }

    public int getEnemyHeroY() {
        return enemyHeroY;
    }

    public void setEnemyHeroY(int enemyHeroY) {
        this.enemyHeroY = enemyHeroY;
    }

    public int getEnemyHeroX() {
        return enemyHeroX;
    }

    public void setEnemyHeroX(int enemyHeroX) {
        this.enemyHeroX = enemyHeroX;
    }

    public int getFriendHeroY() {
        return friendHeroY;
    }

    public void setFriendHeroY(int friendHeroY) {
        this.friendHeroY = friendHeroY;
    }

    public int getGroundX() {
        return GroundX;
    }

    public void setGroundX(int groundX) {
        GroundX = groundX;
    }

    public int getFriendGroundY() {
        return friendGroundY;
    }

    public void setFriendGroundY(int friendGroundY) {
        this.friendGroundY = friendGroundY;
    }

    public int getEnenmyGroundY() {
        return enenmyGroundY;
    }

    public void setEnenmyGroundY(int enenmyGroundY) {
        this.enenmyGroundY = enenmyGroundY;
    }

    public int getMaxYGame() {
        return maxYGame;
    }

    public void setMaxYGame(int maxYGame) {
        this.maxYGame = maxYGame;
    }

    public int getMinYGame() {
        return minYGame;
    }

    public void setMinYGame(int minYGame) {
        this.minYGame = minYGame;
    }

    public int getGroundSpace() {
        return groundSpace;
    }

    public void setGroundSpace(int groundSpace) {
        this.groundSpace = groundSpace;
    }

    public int getHandSpace() {
        return handSpace;
    }

    public void setHandSpace(int handSpace) {
        this.handSpace = handSpace;
    }

    public void setCardWidth(int cardWidth) {
        this.cardWidth = cardWidth;
    }

    public void setCardHeigth(int cardHeigth) {
        this.cardHeigth = cardHeigth;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public void setGroundSize(int groundSize) {
        this.groundSize = groundSize;
    }

    public int getHandSize() {
        return handSize;
    }

    public int getGroundSize() {
        return groundSize;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public String[] getMinionEkhtiari() {
        return minionEkhtiari;
    }

    public void setMinionEkhtiari(String[] minionEkhtiari) {
        this.minionEkhtiari = minionEkhtiari;
    }

    public String[] getSpellEkhtiari() {
        return spellEkhtiari;
    }

    public void setSpellEkhtiari(String[] spellEkhtiari) {
        this.spellEkhtiari = spellEkhtiari;
    }

    public String[] getWeapenEkhtiari() {
        return weapenEkhtiari;
    }

    public void setWeapenEkhtiari(String[] weapenEkhtiari) {
        this.weapenEkhtiari = weapenEkhtiari;
    }

    public int getCardWidth() {
        return cardWidth;
    }

    public int getCardHeigth() {
        return cardHeigth;
    }

    public int getSpace() {
        return space;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    public String[] getMinions() {
        return minions;
    }

    public void setMinions(String[] minions) {
        this.minions = minions;
    }

    public String[] getSpells() {
        return spells;
    }

    public void setSpells(String[] spells) {
        this.spells = spells;
    }

    public String[] getWeapens() {
        return weapens;
    }

    public void setWeapens(String[] weapens) {
        this.weapens = weapens;
    }

    public String[] getCardNames() {
        return cardNames;
    }

    public void setCardNames(String[] cardNames) {
        this.cardNames = cardNames;
    }

    public int[] getCost() {
        return cost;
    }

    public void setCost(int[] cost) {
        this.cost = cost;
    }

    public HashMap<String, Integer> getCostsMap() {
        return costsMap;
    }

    public void setCostsMap(HashMap<String, Integer> costsMap) {
        this.costsMap = costsMap;
    }

    public HashMap<String, String> getTypes() {
        return types;
    }

    public void setTypes(HashMap<String, String> types) {
        this.types = types;
    }

    public String[] getRarity() {
        return rarity;
    }

    public void setRarity(String[] rarity) {
        this.rarity = rarity;
    }

    public String[] getHeroClass() {
        return HeroClass;
    }

    public void setHeroClass(String[] heroClass) {
        HeroClass = heroClass;
    }

    public String[] getWarlock() {
        return warlock;
    }

    public void setWarlock(String[] warlock) {
        this.warlock = warlock;
    }

    public String[] getMage() {
        return mage;
    }

    public void setMage(String[] mage) {
        this.mage = mage;
    }

    public String[] getRouge() {
        return rouge;
    }

    public void setRouge(String[] rouge) {
        this.rouge = rouge;
    }

    public String[] getHunter() {
        return hunter;
    }

    public void setHunter(String[] hunter) {
        this.hunter = hunter;
    }

    public String[] getPriest() {
        return priest;
    }

    public void setPriest(String[] priest) {
        this.priest = priest;
    }

    public String[] getHeros() {
        return heros;
    }

    public void setHeros(String[] heros) {
        this.heros = heros;
    }

    public String[] getPassives() {
        return passives;
    }

    public void setPassives(String[] passives) {
        this.passives = passives;
    }

}

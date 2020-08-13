package server;

import java.util.HashMap;

public class ServerConstants {

    private int port=8000;

    public ServerConstants(){

    }

    private int handSize = 12;
    private int groundSize = 7;
    private String[] minionEkhtiari = new String[]{"blazingBattlemage", "hotAirballon", "evasiveChimaera", "amberWatcher", "veranus", "swampKingDred"};
    private String[] spellEkhtiari = new String[]{"decimation", "malygosFireball", "malygosFlamestrike", "learnDraconic", "strengthInNumbers", "divineHymn"};
    private String[] weapenEkhtiari = new String[]{"bloodRazor", "bloodClaw", "assassinBlade", "arcaniteReaper", "battleAxe", "gearBlade", "bloodFury", "dragonClaw", "ashBringer"};
    private String[] minions = new String[]{"blazingBattlemage", "hotAirballon", "fireHawk ", "evasiveChimaera", "amberWatcher", "dreadScale", "veranus", "curioCollector", "highPriestAmet", "sathrovarr", "securityRover", "swampKingDred", "tombWarden"};
    private String[] spells = new String[]{"decimation", "malygosFireball", "malygosFlamestrike", "friendlySmith", "fireBlast", "arcaneShot", "bookOfSpecters", "flamestrike", "learnDraconic", "pharaohsBlessing", "sprint", "strengthInNumbers", "swarmOfLocusts", "divineHymn"};
    private String[] weapens = new String[]{"bloodRazor", "fierywaraxe", "bloodClaw", "assassinBlade", "arcaniteReaper", "battleAxe", "gearBlade", "bloodFury", "dragonClaw", "ashBringer"};
    private String[] cardNames = new String[]{"blazingBattlemage", "hotAirballon", "fireHawk", "evasiveChimaera", "amberWatcher", "dreadScale", "veranus", "curioCollector", "highPriestAmet", "sathrovarr", "securityRover", "swampKingDred", "tombWarden", "decimation", "malygosFireball", "malygosFlamestrike", "friendlySmith", "fireBlast", "arcaneShot", "bookOfSpecters", "flamestrike", "learnDraconic", "pharaohsBlessing", "sprint", "strengthInNumbers", "swarmOfLocusts", "divineHymn", "bloodRazor", "fierywaraxe", "bloodClaw", "assassinBlade", "arcaniteReaper", "battleAxe", "gearBlade", "bloodFury", "dragonClaw", "ashBringer"};
    private int[] cost = new int[]{4, 5, 5, 6, 9, 6, 10, 8, 9, 10, 6, 6, 8, 10, 7, 10, 6, 6, 5, 6, 10, 6, 10, 9, 6, 9, 7, 8, 8, 6, 8, 8, 5, 6, 10, 8, 8};
    private HashMap<String, Integer> costsMap = new HashMap<>();
    private HashMap<String, String> types = new HashMap<>();
    private HashMap<String,Integer> target = new HashMap<>();
    private String[] rarity = {"common", "rare", "epic", "legendary"};
    private String[] HeroClass = new String[]{"mage", "rouge", "warlock", "hunter", "priest", "neutral"};
    private String[] warlock = {"fireHawk", "dreadScale"};
    private String[] mage = {"fireBlast", "polymorph"};
    private String[] rouge = {"friendlySmith", "fierrywaraxe"};
    private String[] hunter = {"arcaneShot", "swampKingDred"};
    private String[] priest = {"hightPriestAmet", "flamestrike"};
    private String[] heros = {"mage", "rouge", "warlock", "hunter", "priest"};
    private String[] passives = {"twiceDraw", "offCard", "freePower", "manaJump", "nurse"};

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
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public HashMap<String, Integer> getTarget() {
        return target;
    }

    public void setTarget(HashMap<String, Integer> target) {
        this.target = target;
    }

    public int getHandSize() {
        return handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public int getGroundSize() {
        return groundSize;
    }

    public void setGroundSize(int groundSize) {
        this.groundSize = groundSize;
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
